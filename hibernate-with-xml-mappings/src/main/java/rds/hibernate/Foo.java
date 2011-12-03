package rds.hibernate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/22/11
 * Time: 9:39 AM
 */
public class Foo {
    private int id;
    private String name;
    private List<Bar> bars = new ArrayList<Bar>();

    protected Foo() {}

    public Foo(String name) {
        this.name = name;
    }

    public List<Bar> getBars() {
        return bars;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "name='" + name + '\'' +
                ", bars=" + bars +
                '}';
    }
}
