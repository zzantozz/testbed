package rds.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.io.Serializable;

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
        Foo bob = new Foo("bob");
        Bar fred = new Bar("fred");
        Serializable bobId = session.save(bob);
        session.save(fred);
        bob.getBars().put(fred, 5);
        fred.getFoos().add(bob);
        tx.commit();

        session = sessionFactory.getCurrentSession();
        tx = session.beginTransaction();
        session.evict(bob);
        session.evict(fred);
        Foo loadedBob = (Foo) session.load(Foo.class, bobId);
        System.out.println("Bob's name:  " + loadedBob.getName());
        System.out.println("Bob's map:   " + loadedBob.getBars());
        if (!loadedBob.getBars().isEmpty()) {
            Bar loadedFred = loadedBob.getBars().entrySet().iterator().next().getKey();
            System.out.println("Fred's name: " + loadedFred.getName());
            System.out.println("Fred's foos: " + loadedFred.getFoos());
        }
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
