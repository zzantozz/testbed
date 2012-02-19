package rds.multipletransaction.one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/18/11
 * Time: 9:49 PM
 */
@Entity
public class Foo {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Foo() {
    }

    public Foo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
