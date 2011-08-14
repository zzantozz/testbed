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
 * Time: 10:04 PM
 */
public class UnidirectionalManyToOneJoinColumn {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Parent.class)
            .addAnnotatedClass(Child.class)
            .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
            .setProperty("hibernate.hbm2ddl.auto", "create")
            .buildSessionFactory();
        System.out.println("Creating parent with two children");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Parent parent = new Parent("parent");
        Child child2 = new Child("child 1");
        Child child1 = new Child("child 2");
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);
        Serializable parentId = session.save(parent);
        transaction.commit();
        session.close();

        System.out.println("Loading saved parent");
        System.out.println(sessionFactory.openSession().load(Parent.class, parentId));
    }
}
