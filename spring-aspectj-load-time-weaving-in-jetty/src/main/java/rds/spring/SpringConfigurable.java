package rds.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 9/18/11
 * Time: 9:32 AM
 */
@Configurable
public class SpringConfigurable {
    @Autowired
    private SomeDependency dependency;

    @Override
    public String toString() {
        return "SpringConfigurable{dependency=" + dependency + '}';
    }
}
