package rds.hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: Mar 15, 2010
 * Time: 5:24:47 AM
 */
@Entity
public class Bar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic
    private String name;
    @ManyToMany(mappedBy = "bars")
    private Set<Foo> foos = new HashSet<Foo>();

    Bar() {
    }

    public Bar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Foo> getFoos() {
//        return foos;
        return new HashSet<Foo>();
    }

    public String toString() {
        return name;
    }
}
