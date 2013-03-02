package rds.springmvc.init;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import rds.springmvc.core.CoreConfig;
import rds.springmvc.mvc.MvcConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 2/28/13
 * Time: 8:05 PM
 */
public class Initializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        setUpRootContext(servletContext);
        setUpDispatcherServlet(servletContext);
    }

    private void setUpRootContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(CoreConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
    }

    private void setUpDispatcherServlet(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(MvcConfig.class);
        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("dispatcher", new DispatcherServlet(mvcContext));
        dispatcher.addMapping("/*");
    }
}
