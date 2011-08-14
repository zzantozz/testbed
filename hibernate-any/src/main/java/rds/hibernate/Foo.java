package rds.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/13/11
 * Time: 10:51 PM
 */
@Entity
public class Foo {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    protected Foo() {}

    public Foo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Foo{" +
                       "name='" + name + '\'' +
                       '}';
    }
}
