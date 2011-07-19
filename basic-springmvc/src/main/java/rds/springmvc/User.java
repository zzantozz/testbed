package rds.springmvc;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/17/11
 * Time: 11:35 AM
 */
@Entity
public class User {
    @Id
    private int id;
    private String surname;

    public User(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }
}
