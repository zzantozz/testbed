package rds.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 3/3/13
 * Time: 10:49 PM
 */
public class CircularDependencies {
    @Component("a")
    @DependsOn("b") // Without this annotation, Spring tries to create an A first and fails when creating the B that depends on the A that's in creation.
    public static class A {
        @Autowired
        public A(B b) {
        }
    }

    @Component("b")
    public static class B {
        private A a;

        public B() {
        }

        @Autowired
        public void setA(A a) {
            this.a = a;
        }
    }

    @Configuration
    @ComponentScan
    static class Config {
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        A a = context.getBean(A.class);
        B b = context.getBean(B.class);
        System.out.println(a);
        System.out.println(b);
    }
}
