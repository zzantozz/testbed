package rds.spring;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 9/18/11
 * Time: 9:38 AM
 */
@Component
public class SomeDependency {
    @Override
    public String toString() {
        return "I'm a dependency!";
    }
}
