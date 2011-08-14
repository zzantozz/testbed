package rds.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/13/11
 * Time: 10:50 PM
 */
public class AnyMapping {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Box.class)
            .addAnnotatedClass(Foo.class)
            .addAnnotatedClass(Bar.class)
            .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
            .setProperty("hibernate.hbm2ddl.auto", "create")
            .buildSessionFactory();
        System.out.println("Create and save some boxes");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Foo foo = new Foo("i'm a foo");
        Box<Foo> fooBox = new Box<Foo>(foo);
        Bar bar = new Bar("i'm a bar");
        Box<Bar> barBox = new Box<Bar>(bar);
        Serializable fooBoxId = session.save(fooBox);
        Serializable barBoxId = session.save(barBox);
        transaction.commit();
        session.close();

        System.out.println("Now load and print the boxes");
        session = sessionFactory.openSession();
        System.out.println(session.load(Box.class, fooBoxId));
        System.out.println(session.load(Box.class, barBoxId));
        session.close();
    }
}
