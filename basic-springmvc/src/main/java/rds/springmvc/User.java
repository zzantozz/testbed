package rds.springmvc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/17/11
 * Time: 11:35 AM
 */
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    protected User() {}

    public User(String name) {
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
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
