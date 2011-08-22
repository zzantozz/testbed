package rds.hibernate;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/22/11
 * Time: 9:39 AM
 */
public class Foo {
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
