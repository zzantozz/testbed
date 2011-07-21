package rds.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/20/11
 * Time: 3:46 PM
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                                .addResource("all.hbm.xml")
                                                .setProperty("hibernate.connection.url", "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
                                                .setProperty("hibernate.hbm2ddl.auto", "create")
                                                .setProperty("hibernate.show_sql", "true")
                                                .buildSessionFactory();
        Ids ids = createExampleObjects(sessionFactory);
        Book book = getBook(sessionFactory, ids.bookId);
        delete(book.getAuthor(), sessionFactory);
        delete(book.getPublisher(), sessionFactory);
        delete(book, sessionFactory);
    }

    private static Book getBook(SessionFactory sessionFactory, int bookId) {
        Session session = sessionFactory.openSession();
        Book book = (Book) session.get(Book.class, bookId);
        session.close();
        return book;
    }

    private static void delete(Object o, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(o);
        transaction.commit();
        session.close();
    }

    private static Ids createExampleObjects(SessionFactory sessionFactory) {
        Ids ids;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Publisher publisher = new Publisher();
        Author author = new Author();
        Book book = new Book("HHGTTG", author, publisher);
        publisher.getBooks().add(book);
        author.getBooks().add(book);
        session.save(book);
        ids = new Ids(book.getId(), publisher.getId(), author.getId());
        transaction.commit();
        session.close();
        return ids;
    }
}

class Ids {
    public final int bookId;
    public final int publisherId;
    public final int authorId;

    Ids(int bookId, int publisherId, int authorId) {
        this.bookId = bookId;
        this.publisherId = publisherId;
        this.authorId = authorId;
    }
}