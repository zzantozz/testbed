package rds.hibernate;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/20/11
 * Time: 3:47 PM
 */
public class Book {
    private int id;
    private String name;
    private Publisher publisher;
    private Author author;

    Book() {}

    public Book(String name, Author author, Publisher publisher) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                       "id=" + id +
                       ", publisher=" + publisher +
                       ", author=" + author +
                       '}';
    }
}
