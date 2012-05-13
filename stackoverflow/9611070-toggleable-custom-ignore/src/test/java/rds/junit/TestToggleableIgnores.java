package rds.junit;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 4/20/12
 * Time: 9:45 PM
 */
public class TestToggleableIgnores {
    @Rule
    public ToggleableIgnores toggleableIgnores = new ToggleableIgnores();

    @Test
    public void someTest() {
        System.out.println("I'm a test method");
    }

    @Test
    @MyIgnore
    public void ignoredTest() throws Exception {
        System.out.println("I'm ignored");
    }
}
