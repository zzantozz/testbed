package rds.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/22/11
 * Time: 9:37 AM
 */
public class HibernateWithXmlConfiguration {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Foo bob = new Foo("bob");
        Bar bar1 = new Bar("first");
        Bar bar2 = new Bar("second");
        bob.getBars().add(bar2);
        bob.getBars().add(bar1);
        System.out.println("Saving a new Foo: " + bob);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable bobId = session.save(bob);
        transaction.commit();
        session.close();

        System.out.format("Saved Foo id was %s; loading it back in a new session: ", bobId);

        session = sessionFactory.openSession();
        Foo foo = (Foo) session.load(Foo.class, bobId);
        System.out.println(foo);
        session.close();
        sessionFactory.close();
    }
}
