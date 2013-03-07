package rds.springmvc.mvc;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import rds.springmvc.InjectionQualifiedByProperty;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 3/6/13
 * Time: 10:42 PM
 */
public class InjectionQualifiedByPropertyTest {
    private static Server server;
    private static WebResource resource;

    @BeforeClass
    public static void startConfigClass() throws Exception {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(InjectionQualifiedByProperty.Beans.class);
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/");
        handler.addServlet(new ServletHolder(new DispatcherServlet(applicationContext)), "/*");
        server = new Server(8080);
        server.setHandler(handler);
        server.start();
        resource = Client.create().resource("http://localhost:8080");
    }

    @AfterClass
    public static void stopServer() throws Exception {
        if (server != null) server.stop();
    }

    @Test
    public void injectedDependencyCanBeDeterminedByRequestSpecificProperty() throws Exception {
        String noParamResponse = resource.path("dynamicallyInjected").get(String.class);
        String bobResponse = resource.path("dynamicallyInjected").queryParam("which", "bob").get(String.class);
        assertThat(noParamResponse, equalTo("I'm not Bob"));
        assertThat(bobResponse, equalTo("Hi, I'm Bob"));
    }
}
