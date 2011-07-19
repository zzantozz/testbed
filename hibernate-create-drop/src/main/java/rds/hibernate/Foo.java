package rds.hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "foo", fetch = FetchType.EAGER)
    private List<Bar> bars = new ArrayList<Bar>();

    public List<Bar> getBars() {
        return bars;
    }

    public void setBars(List<Bar> bars) {
        this.bars = bars;
    }

    @Override
    public String toString() {
        return "Foo{" +
                       "id=" + id +
                       ", bars=" + bars +
                       '}';
    }
}
