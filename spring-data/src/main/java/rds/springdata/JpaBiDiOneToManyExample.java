package rds.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 2/25/13
 * Time: 8:23 PM
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement(proxyTargetClass = true)
public class JpaBiDiOneToManyExample {
    @Autowired
    private FooRepository repository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(TypicalSpringJpaBeans.class, JpaBiDiOneToManyExample.class);
        JpaBiDiOneToManyExample example = context.getBean(JpaBiDiOneToManyExample.class);
        long fooId = example.saveAFooAndBar();
        example.showFoo(fooId);
        example.addNewBarToFoo(fooId);
        example.showFoo(fooId);
        context.close();
    }

    @Transactional
    public long saveAFooAndBar() {
        Foo foo = new Foo().setName("Foo 1");
        Bar bar = new Bar().setName("Bar 1").setFoo(foo);
        foo.getBars().add(bar);
        return repository.save(foo).getId();
    }

    @Transactional(readOnly = true)
    public void showFoo(long fooId) {
        System.out.println(repository.findOne(fooId));
    }

    @Transactional
    public void addNewBarToFoo(long fooId) {
        Foo foo = repository.findOne(fooId);
        foo.getBars().add(new Bar().setName("Bar 2").setFoo(foo));
    }
}
