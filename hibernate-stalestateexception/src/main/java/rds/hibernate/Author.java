package rds.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/20/11
 * Time: 3:47 PM
 */
public class Author {
    private int id;
    private Set<Book> books = new HashSet<Book>();

    public Set<Book> getBooks() {
        return books;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Author{" +
                       "id=" + id +
                       '}';
    }
}
