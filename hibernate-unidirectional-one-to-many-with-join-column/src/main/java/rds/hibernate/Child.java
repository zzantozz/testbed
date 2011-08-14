package rds.hibernate;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/13/11
 * Time: 10:05 PM
 */
@Entity
public class Child {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;

    protected Child() {}

    public Child(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Child{" +
                       "description='" + description + '\'' +
                       '}';
    }
}
