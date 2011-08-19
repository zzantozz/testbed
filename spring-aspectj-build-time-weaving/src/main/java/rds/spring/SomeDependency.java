package rds.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/18/11
 * Time: 9:46 PM
 */
public interface SomeDependency {
    @Component @Qualifier("one")
    class ImplOne implements SomeDependency {}
    @Component @Qualifier("two")
    class ImplTwo implements SomeDependency {}
}
