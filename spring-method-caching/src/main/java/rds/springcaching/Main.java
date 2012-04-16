package rds.springcaching;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 4/16/12
 * Time: 2:01 PM
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
        SomethingCacheable bean = applicationContext.getBean(SomethingCacheable.class);
        System.out.println("Basic caching of a returned value--alternating caching and non-caching method calls:");
        System.out.printf("Caching method:     %s%n", bean.getCurrentTime());
        System.out.printf("Non-caching method: %s%n", bean.getCurrentTimeWithoutCaching());
        System.out.printf("Caching method:     %s%n", bean.getCurrentTime());
        System.out.printf("Non-caching method: %s%n", bean.getCurrentTimeWithoutCaching());
        System.out.println("Call an evicting method to update the value");
        bean.someEvictingOperation();
        System.out.println("Now a fresh value is retrieved and cached again");
        System.out.printf("Caching method:     %s%n", bean.getCurrentTime());
        System.out.printf("Non-caching method: %s%n", bean.getCurrentTimeWithoutCaching());
        System.out.printf("Caching method:     %s%n", bean.getCurrentTime());
        System.out.printf("Non-caching method: %s%n", bean.getCurrentTimeWithoutCaching());
    }

}
