package rds.multipletransaction.two;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 2/18/12
 * Time: 6:44 PM
 */
@Entity
public class Bar {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Bar() {
    }

    public Bar(String name) {
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
        return "Bar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
