package rds.testbed;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/14/11
 * Time: 7:58 PM
 */
@Path("/foo")
public class JaxRsResource {
    @GET
    @Path("dbdetails")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDBDetails() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        GenericEntity<List<String>> entity = new GenericEntity<List<String>>(list) {};
        Response response = Response.ok(new JAXBElement<ArrayList>(new QName("foo", "bar"), ArrayList.class, list)).build();
        return response;
    }

    public static void main(String[] args) throws Exception {
        final String baseUri = "http://localhost:9998/";
        final Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("com.sun.jersey.config.property.packages", "com.foo");
        System.out.println("Starting grizzly...");
        SelectorThread threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);
        System.out.println(String.format("Jersey app started with WADL available at %sapplication.wadl\n" +
                                "Try out %shelloworld\nHit enter to stop it...", baseUri, baseUri));
        WebResource resource = Client.create().resource(baseUri);
        String response = resource.path("foo/dbdetails").get(String.class);
        System.out.println(response);
        System.in.read();
        threadSelector.stopEndpoint();
        System.exit(0);
    }
}
