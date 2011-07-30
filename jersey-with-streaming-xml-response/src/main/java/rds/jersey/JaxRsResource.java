package rds.jersey;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/30/11
 * Time: 2:49 PM
 */
@Path("/streaming")
public class JaxRsResource {
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static int fooCounter;
    private Marshaller marshaller;

    public JaxRsResource() throws JAXBException {
        marshaller = JAXBContext.newInstance(Foo.class).createMarshaller();
        marshaller.setProperty("jaxb.fragment", Boolean.TRUE);
    }

    @GET
    @Produces("application/xml")
    public StreamingOutput streamStuff() {
        System.out.println("Got request for streaming resource; starting delayed response threads");
        final List<Future<List<Foo>>> futureFoos = new ArrayList<Future<List<Foo>>>();
        futureFoos.add(executorService.submit(new DelayedFoos(2)));
        futureFoos.add(executorService.submit(new DelayedFoos(4)));
        futureFoos.add(executorService.submit(new DelayedFoos(6)));
        return new StreamingOutput() {
            public void write(OutputStream output) throws IOException {
                for (Future<List<Foo>> futureFoo : futureFoos) {
                    writePartialOutput(futureFoo, output);
                    output.write("\n".getBytes());
                    output.flush();
                }
            }
        };
    }

    private void writePartialOutput(Future<List<Foo>> futureFoo, OutputStream output) {
        try {
            List<Foo> foos = futureFoo.get();
            System.out.println("Server sending a chunk of XML");
            for (Foo foo : foos) {
                marshaller.marshal(foo, output);
            }
        } catch (JAXBException e) {
            throw new IllegalStateException("JAXB couldn't marshal. Handle it.", e);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Task was interrupted. Handle it.", e);
        } catch (ExecutionException e) {
            throw new IllegalStateException("Task failed to execute. Handle it.", e);
        }
    }

    class DelayedFoos implements Callable<List<Foo>> {
        private int delaySeconds;

        public DelayedFoos(int delaySeconds) {
            this.delaySeconds = delaySeconds;
        }

        public List<Foo> call() throws Exception {
            Thread.sleep(delaySeconds * 1000);
            return Arrays.asList(new Foo(fooCounter++), new Foo(fooCounter++), new Foo(fooCounter++));
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Starting Grizzly with the JAX-RS resource");
        final String baseUri = "http://localhost:9998/";
        final Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("com.sun.jersey.config.property.packages", "rds.jersey");
        SelectorThread threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);
        System.out.println("Grizzly started");
        System.out.println("Starting a thread to request the streamed XML");
        executorService.submit(new HttpRequester(baseUri + "streaming"));
    }
}

@XmlRootElement
class Foo {
    @XmlElement
    private int id;

    Foo() {}

    public Foo(int id) {
        this.id = id;
    }
}

class HttpRequester implements Runnable {
    private String url;

    public HttpRequester(String url) {
        this.url = url;
    }

    public void run() {
        try {
            System.out.println("Doing HTTP GET on " + url);
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Client got: " + line);
            }
            System.exit(0);
        } catch (IOException e) {
            throw new IllegalStateException("Some bad I/O happened. Handle it.", e);
        }
    }
}