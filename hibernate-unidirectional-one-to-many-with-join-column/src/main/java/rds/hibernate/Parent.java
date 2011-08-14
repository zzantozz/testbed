package rds.hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/13/11
 * Time: 10:05 PM
 */
@Entity
public class Parent {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private List<Child> children = new ArrayList<Child>();

    protected Parent() {}

    public Parent(String description) {
        this.description = description;
    }

    public List<Child> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Parent{" +
                       "description='" + description + '\'' +
                       ", children=" + children +
                       '}';
    }
}
