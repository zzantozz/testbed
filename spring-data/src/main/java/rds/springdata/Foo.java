package rds.springdata;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 2/25/13
 * Time: 8:50 PM
 */
@Entity
public class Foo {
    @Id @GeneratedValue
    private long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="foo")
    private Set<Bar> bars = new HashSet<Bar>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Foo setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Bar> getBars() {
        return bars;
    }

    public Foo setBars(Set<Bar> bars) {
        this.bars = bars;
        return this;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bars=" + bars +
                '}';
    }
}
