package rds.hibernate;

import org.hibernate.annotations.*;
import org.hibernate.annotations.MapKey;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: Mar 15, 2010
 * Time: 5:23:20 AM
 */
@Entity
public class Foo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic
    private String name;
    @MapKeyManyToMany(targetEntity = Bar.class)
    @CollectionOfElements(targetElement = Integer.class)
    private Map<Bar, Integer> bars = new HashMap<Bar, Integer>();

    Foo() {
    }

    public Foo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<Bar, Integer> getBars() {
        return bars;
    }

    public String toString() {
        return name;
    }
}
