package rds.springdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 2/25/13
 * Time: 8:50 PM
 */
@Entity
public class Bar {
    @Id @GeneratedValue
    private long id;
    private String name;
    @ManyToOne
    private Foo foo;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Bar setName(String name) {
        this.name = name;
        return this;
    }

    public Foo getFoo() {
        return foo;
    }

    public Bar setFoo(Foo foo) {
        this.foo = foo;
        return this;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foo=" + foo.getName() +
                '}';
    }
}
