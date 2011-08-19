package rds.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/18/11
 * Time: 9:43 PM
 */
@Configurable
public class SpringConfigurable {
    @Autowired @Qualifier("one")
    private SomeDependency one;
    @Autowired @Qualifier("two")
    private SomeDependency two;
    @Autowired
    private List<SomeDependency> all;

    private void printStuff() {
        System.out.println("Here are my dependencies:");
        System.out.println("one=" + one);
        System.out.println("two=" + two);
        System.out.println("all=" + all);
    }

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("applicationContext.xml");
        new SpringConfigurable().printStuff();
    }
}
