package rds.testbed;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/21/11
 * Time: 9:08 PM
 */
public class EmbeddedJettyWithServlet {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        System.out.println("about to start the servlets");
        context.addServlet(new ServletHolder(new HelloServlet()), "/*");
        context.addServlet(new ServletHolder(new HelloServlet("Buongiorno Mondo")), "/it/*");
        context.addServlet(new ServletHolder(new HelloServlet("Bonjour le Monde")), "/fr/*");

        server.start();
        System.out.println("started the servlets");
        server.join();
    }

    static class HelloServlet extends HttpServlet {
        private String greeting = "Hello World";
        private int count;

        public HelloServlet() {}

        public HelloServlet(String greeting) {
            this.greeting = greeting;
            System.out.println("started the server" + greeting);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>" + greeting + "</h1>");
            response.getWriter().println("session=" + request.getSession(true).getId());
            count = count + 1;
            System.out.println(count);
            System.out.println("request URI=" + request.getRequestURI());
            response.getWriter().println("count=" + count);
            response.flushBuffer();
        }
    }
}

