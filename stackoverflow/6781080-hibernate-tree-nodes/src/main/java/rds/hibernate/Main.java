package rds.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/22/11
 * Time: 8:51 AM
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                      .addAnnotatedClass(Node.class)
                      .addAnnotatedClass(Task.class)
                      .addAnnotatedClass(TaskStatus.class)
                      .addAnnotatedClass(LanguageType.class)
                      .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
                      .setProperty("hibernate.hbm2ddl.auto", "create")
                      .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        a.setLeftChild(b);
        b.setParent(a);
        a.setRightChild(c);
        c.setParent(a);
        b.setLeftChild(d);
        d.setParent(b);
        b.setRightChild(e);
        e.setParent(b);
        System.out.println("Before saving:");
        print(a, 1);
        Serializable rootNodeId = session.save(a);

        Task t = new Task();
        TaskStatus status = new TaskStatus();
        LanguageType l = new LanguageType();
        status.languageType = l;
        t.status = status;
        Serializable taskid = session.save(t);
        session.save(status);
        session.save(l);
        transaction.commit();
        session.close();

        session = sessionFactory.openSession();
        Node root = (Node) session.load(Node.class, rootNodeId);
        System.out.println("Freshly loaded:");
        print(root, 1);
        Object load = session.load(Task.class, taskid);
        System.out.println(load);
        session.close();
    }

    private static void print(Node node, int depth) {
        if (node == null) { return; }
        System.out.format("%" + depth + "s\n", node);
        print(node.getLeftChild(), depth + 1);
        print(node.getRightChild(), depth + 1);
    }
}
