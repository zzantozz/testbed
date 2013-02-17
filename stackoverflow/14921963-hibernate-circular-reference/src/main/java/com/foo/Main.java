package com.foo;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 2/17/13
 * Time: 9:22 AM
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Folder.class)
                .addAnnotatedClass(File.class)
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Folder folder = new Folder();
        folder.setName("a folder");
        File file = new File();
        file.setName("a file");
        folder.getFiles().add(file);
        file.setRootFolder(folder);

        System.out.println("Save folder: " + folder);
        Serializable folderId = session.save(folder);
        System.out.println("Save file: " + file);
        session.save(file);
        tx.commit();
        session.close();

        Session newSession = sessionFactory.openSession();
        Folder loadedFolder = (Folder) newSession.load(Folder.class, folderId);
        System.out.println("Result of loading saved folder: " + loadedFolder);
        newSession.close();
    }
}
