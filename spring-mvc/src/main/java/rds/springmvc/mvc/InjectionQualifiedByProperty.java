package rds.springmvc.mvc;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 2/28/13
 * Time: 7:42 PM
 */
public class InjectionQualifiedByProperty {

    @Controller
    @Scope(WebApplicationContext.SCOPE_REQUEST)
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

    @Configuration
    public static class Beans {
        @Bean
        @Scope(WebApplicationContext.SCOPE_REQUEST)
        @Qualifier("picker")
        FactoryBean<Dependency> dependencyPicker(final RequestContext requestContext,
                                                 final BobDependency bob, final FredDependency fred) {
            return new FactoryBean<Dependency>() {
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
            };
        }
    }

    @Component
    public static class BobDependency implements Dependency {
        @Override
        public String sayHi() {
            return "Hi, I'm Bob";
        }
    }

    @Component
    public static class FredDependency implements Dependency {
        @Override
        public String sayHi() {
            return "I'm not Bob";
        }
    }

    @Component
    @Scope(WebApplicationContext.SCOPE_REQUEST)
    public static class RequestContext {
        @Autowired HttpServletRequest request;

        String getQualifierProperty() {
            return request.getParameter("which");
        }
    }
}
