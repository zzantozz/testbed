package rds.springmockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 5/24/12
 * Time: 4:21 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringWithJavaConfigMockitoTest.class)
public class SpringWithJavaConfigMockitoTest {
    @Autowired
    private Foo foo;

    @Test
    public void mockedFooWorks() throws Exception {
        when(foo.getString()).thenReturn("hello world");
        assertThat(foo.getString(), equalTo("hello world"));
    }

    @Bean
    public Foo mockFoo() {
        return mock(Foo.class);
    }

    public static interface Foo {
        String getString();
    }
}
