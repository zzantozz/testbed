package rds.springmvc;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

public class InjectionQualifiedByProperty {

    @Configuration
    @EnableWebMvc
    public static class Beans {
        @Bean @Scope(SCOPE_REQUEST) DynamicallyInjectedController controller() { return new DynamicallyInjectedController(); }
        @Bean @Scope(SCOPE_REQUEST) RequestContext context() { return new RequestContext(); }
        @Bean @Scope(SCOPE_REQUEST) @Qualifier("picker") FactoryBean<Dependency> dependencyPicker() {
            return new DependencyPickingFactoryBean(context(), bob(), fred());
        }
        @Bean BobDependency bob() { return new BobDependency(); }
        @Bean FredDependency fred() { return new FredDependency(); }
    }

    public static class DependencyPickingFactoryBean implements FactoryBean<Dependency> {
        private final RequestContext requestContext;
        private final BobDependency bob;
        private final FredDependency fred;

        public DependencyPickingFactoryBean(RequestContext requestContext, BobDependency bob, FredDependency fred) {
            this.requestContext = requestContext;
            this.bob = bob;
            this.fred = fred;
        }

        @Override
        public Dependency getObject() throws Exception {
            if ("bob".equals(requestContext.getQualifierProperty())) {
                return bob;
            } else {
                return fred;
            }
        }

        @Override
        public Class<?> getObjectType() {
            return Dependency.class;
        }

        @Override
        public boolean isSingleton() {
            return false;
        }
    }

    @Controller
    public static class DynamicallyInjectedController {
        @Autowired
        @Qualifier("picker")
        Dependency dependency;

        @RequestMapping(value = "/dynamicallyInjected", method = RequestMethod.GET)
        @ResponseBody
        public String sayHi() {
            return dependency.sayHi();
        }
    }

    public interface Dependency {
        String sayHi();
    }

    public static class BobDependency implements Dependency {
        @Override
        public String sayHi() {
            return "Hi, I'm Bob";
        }
    }

    public static class FredDependency implements Dependency {
        @Override
        public String sayHi() {
            return "I'm not Bob";
        }
    }

    public static class RequestContext {
        @Autowired HttpServletRequest request;

        String getQualifierProperty() {
            return request.getParameter("which");
        }
    }
}
