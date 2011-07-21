package rds.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/18/11
 * Time: 9:51 PM
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("*** Starting SessionFactory with create");
        SessionFactory sessionFactoryWithCreate = new Configuration()
                      .addAnnotatedClass(Foo.class)
                      .addAnnotatedClass(Bar.class)
                      .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
                      .setProperty("hibernate.hbm2ddl.auto", "create")
                      .buildSessionFactory();
        System.out.println("*** Closing SessionFactory with create");
        sessionFactoryWithCreate.close();
        System.out.println("*** Starting SessionFactory with create-drop");
        SessionFactory sessionFactoryWithCreateDrop = new Configuration()
                      .addAnnotatedClass(Foo.class)
                      .addAnnotatedClass(Bar.class)
                      .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
                      .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                      .buildSessionFactory();
        System.out.println("*** Closing SessionFactory with create-drop");
        sessionFactoryWithCreateDrop.close();
    }
}
