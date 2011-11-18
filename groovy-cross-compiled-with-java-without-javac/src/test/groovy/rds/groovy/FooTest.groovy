package rds.groovy

import org.junit.Test

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 11/17/11
 * Time: 11:30 PM
 */
class FooTest {
    @Test
    void "do groovy/java interweaving calls"() {
        String result = new JavaFooCaller().go()
        assert result == "hi there"
    }
}

class GroovyFooCaller {
    String go() {
        return new Foo().go()
    }
}