package rds.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: Mar 15, 2010
 * Time: 5:21:47 AM
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = annotationSessionFactory();
//        SessionFactory sessionFactory = xmlSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Foo foo = new Foo("bob");
        Bar one = new Bar("one");
        one.setFoo(foo);
        Bar two = new Bar("two");
        two.setFoo(foo);
        foo.setBars(new HashSet<Bar>(Arrays.asList(one, two)));
        Serializable bobId = session.save(foo);
        tx.commit();

        session = sessionFactory.getCurrentSession();
        tx = session.beginTransaction();
        session.evict(foo);
        Foo loadedBob = (Foo) session.load(Foo.class, bobId);
        System.out.println("Bob's class: " + loadedBob.getClass());
        System.out.println("Bob's bars: " + foo.getBars());
        System.out.println("Bob:  " + loadedBob);
        tx.commit();
    }

    private static SessionFactory xmlSessionFactory() {
        return new Configuration()
                .addInputStream(Main.class.getResourceAsStream("/foo.hbm.xml"))
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
//                .setProperty("hibernate.connection.url", "jdbc:h2:tcp://localhost/~/foo")
                .setProperty("hibernate.connection.username", "sa")
                .setProperty("hibernate.connection.password", "sa")
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .setProperty("hibernate.current_session_context_class", "thread")
                .buildSessionFactory();
    }

    private static SessionFactory annotationSessionFactory() {
        return new AnnotationConfiguration()
                .addAnnotatedClass(Foo.class)
                .addAnnotatedClass(Bar.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
//                .setProperty("hibernate.connection.url", "jdbc:h2:tcp://localhost/~/foo")
                .setProperty("hibernate.connection.username", "sa")
                .setProperty("hibernate.connection.password", "sa")
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .setProperty("hibernate.current_session_context_class", "thread")
                .buildSessionFactory();
    }
}
