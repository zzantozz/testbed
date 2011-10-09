package rds.hibernate;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 9/8/11
 * Time: 12:13 AM
 */
@Entity
public class TaskStatus {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_status_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "lang_id")
    LanguageType languageType;
}
