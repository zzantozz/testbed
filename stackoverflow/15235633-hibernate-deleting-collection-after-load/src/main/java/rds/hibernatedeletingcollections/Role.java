package rds.hibernatedeletingcollections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 3/5/13
 * Time: 9:22 PM
 */
@Entity
public class Role {
    @Id
    @GeneratedValue
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}