package rds.testbed.hibernateproxies;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.proxy.HibernateProxy;

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
        SessionFactory sessionFactory = new Configuration()
                                                .addAnnotatedClass(Foo.class)
                                                .addAnnotatedClass(Bar.class)
                                                .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
                                                .setProperty("hibernate.hbm2ddl.auto", "create")
                                                .buildSessionFactory();
        System.out.println("First, save a Foo");
        Serializable savedId = saveAFoo(sessionFactory);
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println("Load the Foo in a new session to avoid the first level cache");
        Foo loadedFoo = (Foo) session.load(Foo.class, savedId);
        System.out.println("About the loaded Foo--");
        System.out.println("Is a Hibernate proxy: " + (loadedFoo instanceof HibernateProxy));
        System.out.println("Is initialized: " + Hibernate.isInitialized(loadedFoo));
        System.out.println("Class: " + loadedFoo.getClass());
        System.out.print("Calling getBars: ");
        try {
            System.out.println(loadedFoo.getBars());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.err.println("Is initialized: " + Hibernate.isInitialized(loadedFoo));
        tx.commit();
    }

    private static Serializable saveAFoo(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Foo foo = new Foo("bob");
        Bar one = new Bar("one");
        one.setFoo(foo);
        Bar two = new Bar("two");
        two.setFoo(foo);
        foo.setBars(new HashSet<Bar>(Arrays.asList(one, two)));
        Serializable bobId = session.save(foo);
        tx.commit();
        session.close();
        System.out.println("Foo saved and committed: " + foo);
        return bobId;
    }
}
