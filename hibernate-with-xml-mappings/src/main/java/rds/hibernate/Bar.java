package rds.hibernate;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 9/4/11
 * Time: 10:05 PM
 */
public class Bar {
    private int id;
    private String name;
    private Foo foo;

    protected Bar() {}

    public Bar(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "name='" + name + '\'' +
                '}';
    }
}
