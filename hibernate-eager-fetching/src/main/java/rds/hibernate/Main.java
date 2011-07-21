package rds.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/18/11
 * Time: 9:51 PM
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                                .addAnnotatedClass(Foo.class)
                                                .addAnnotatedClass(Bar.class)
                                                .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
                                                .setProperty("hibernate.hbm2ddl.auto", "create")
                                                .setProperty("hibernate.show_sql", "true")
                                                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Foo foo = new Foo();
        Bar bar1 = new Bar(foo);
        Bar bar2 = new Bar(foo);
        foo.getBars().add(bar1);
        foo.getBars().add(bar2);
        session.save(foo);
        session.save(bar1);
        session.save(bar2);
        transaction.commit();
        session.close();

        session = sessionFactory.openSession();
        List list = session.createCriteria(Foo.class).list();
        System.out.println(list);
        System.out.println("We got two copies of the one Foo that was created because of the eager fetching.");
        session.close();
    }
}
