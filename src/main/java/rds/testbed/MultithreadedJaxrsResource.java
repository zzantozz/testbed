package rds.testbed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/17/11
 * Time: 9:58 AM
 */
@Path("foo")
public class MultithreadedJaxrsResource {
    private ExecutorService executorService;

    public MultithreadedJaxrsResource(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public AllMyArticles getStuff() {
        List<Future<Article>> futures = new ArrayList<Future<Article>>();
        // Submit all the tasks to run
        for (int i = 0; i < 10; i++) {
            futures.add(executorService.submit(new Driver(i + 1)));
        }
        AllMyArticles articles = new AllMyArticles();
        // Wait for all tasks to finish
        // If you only care that everything is done and not about seeing
        // when each one finishes, this outer do/while can go away, and
        // you only need a single for loop to wait on each future.
        boolean allDone;
        do {
            allDone = true;
            Iterator<Future<Article>> futureIterator = futures.iterator();
            while (futureIterator.hasNext()) {
                Future<Article> future = futureIterator.next();
                if (future.isDone()) {
                    try {
                        articles.articles.add(future.get());
                        futureIterator.remove();
                    } catch (InterruptedException e) {
                        // thread was interrupted. don't do that.
                        throw new IllegalStateException("broken", e);
                    } catch (ExecutionException e) {
                        // execution of the Callable failed with an exception. check it out.
                        throw new IllegalStateException("broken", e);
                    }
                } else {
                    allDone = false;
                }
            }
        } while (!allDone);
        return articles;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AllMyArticles stuff = new MultithreadedJaxrsResource(executorService).getStuff();
        System.out.println(stuff.articles);
        executorService.shutdown();
    }
}

class Driver implements Callable<Article> {
    private int i; // Just to differentiate the instances

    public Driver(int i) {
        this.i = i;
    }

    public Article call() {
        // Simulate taking some time for each call
        try {
            Thread.sleep(1000 / i);
        } catch (InterruptedException e) {
            System.err.println("oops");
        }
        return new Article(i);
    }
}

class AllMyArticles {
    public final List<Article> articles = new ArrayList<Article>();
}

class Article {
    public final int i;

    public Article(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "Article{" +
                       "i=" + i +
                       '}';
    }
}
