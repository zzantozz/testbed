package rds.hibernate;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 9/8/11
 * Time: 12:13 AM
 */
@Entity
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "task_status_id")
    TaskStatus status;
}
