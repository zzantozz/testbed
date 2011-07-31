package cljtest;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan
 * Date: Jul 18, 2010
 * Time: 2:00:41 PM
 */
public class Foo {
    private String name;
    private int x;

    public Foo(String name, int x) {
        this.name = name;
        this.x = x;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }
}
