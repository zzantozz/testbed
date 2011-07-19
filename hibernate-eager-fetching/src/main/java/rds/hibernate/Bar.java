package rds.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/18/11
 * Time: 9:49 PM
 */
@Entity
public class Bar {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Foo foo;

    Bar() {}

    public Bar(Foo foo) {
        this.foo = foo;
    }

    public Foo getFoo() {
        return foo;
    }

    public void setFoo(Foo foo) {
        this.foo = foo;
    }

    @Override
    public String toString() {
        return "Bar{" +
                       "id=" + id +
                       '}';
    }
}
