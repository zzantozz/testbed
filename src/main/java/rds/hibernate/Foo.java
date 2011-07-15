package rds.hibernate;

import org.hibernate.annotations.*;
import org.hibernate.annotations.MapKey;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    @OneToMany(targetEntity = Bar.class, mappedBy = "foo", orphanRemoval = true)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE})
    private Set<Bar> bars = new HashSet<Bar>();

    Foo() {
    }

    public Foo(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Set<Bar> getBars() {
        throw new IllegalStateException("foo");
    }

    public void setBars(Set<Bar> bars) {
        this.bars = bars;
    }

    @Override
    public String toString() {
        return "Foo{" +
                       "name='" + name + '\'' +
                       ", bars=" + bars +
                       '}';
    }
}
