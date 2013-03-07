package rds.hibernatedeletingcollections;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 3/5/13
 * Time: 9:42 PM
 */
public class Initializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext rootContext = new XmlWebApplicationContext();
        servletContext.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(Config.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(mvcContext));
        dispatcher.addMapping("/*");
        servletContext.getServletRegistration("jsp").addMapping("/WEB-INF/jsp/*");

        FilterRegistration.Dynamic osiv = servletContext.addFilter("osiv", new OpenEntityManagerInViewFilter());
        osiv.addMappingForUrlPatterns(null, false, "/*");
    }
}
