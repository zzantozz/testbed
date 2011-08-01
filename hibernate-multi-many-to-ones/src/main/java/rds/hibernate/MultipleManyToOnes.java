package rds.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/1/11
 * Time: 10:56 AM
 */
public class MultipleManyToOnes {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                                .addAnnotatedClass(SecurityContact.class)
                                                .addAnnotatedClass(AgentContact.class)
                                                .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
                                                .setProperty("hibernate.hbm2ddl.auto", "create")
                                                .buildSessionFactory();
        Serializable savedContactId = createASecurityContactWithAgentContact(sessionFactory);
        loadTheSecurityContactAndAddAnAuditContact(sessionFactory, savedContactId);
        showAllSecurityContacts(sessionFactory);
    }

    private static Serializable createASecurityContactWithAgentContact(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AgentContact agentContact = new AgentContact();
        agentContact.setName("my agent contact");
        SecurityContact contactWithAgent = new SecurityContact();
        contactWithAgent.setName("security contact with only an agent contact set");
        contactWithAgent.setAgentContact(agentContact);
        agentContact.getSecurityContacts().add(contactWithAgent);
        session.save(agentContact);
        Serializable savedContactId = session.save(contactWithAgent);
        transaction.commit();
        session.close();
        System.out.println("Created: " + contactWithAgent);
        return savedContactId;
    }

    private static void loadTheSecurityContactAndAddAnAuditContact(SessionFactory sessionFactory, Serializable savedContactId) {
        Session session;
        Transaction transaction;
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        SecurityContact loadedSecurityContactWithAgent = (SecurityContact) session.load(SecurityContact.class, savedContactId);
        AgentContact auditContact = new AgentContact();
        auditContact.setName("my audit contact");
        loadedSecurityContactWithAgent.setAuditContact(auditContact);
        auditContact.getSecurityContacts().add(loadedSecurityContactWithAgent);
        session.save(auditContact);
        transaction.commit();
        System.out.println("Added an audit contact to the existing security contact");
        session.close();
    }

    private static void showAllSecurityContacts(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(SecurityContact.class).list();
        System.out.println("Database now has " + list.size() + " security contacts:");
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            System.out.println("  " + (i + 1) + ": " + o);
        }
        session.close();
    }
}
