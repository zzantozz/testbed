package rds.hibernate;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 9/8/11
 * Time: 12:14 AM
 */
@Entity
public class LanguageType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lang_id")
    private int id;
}
