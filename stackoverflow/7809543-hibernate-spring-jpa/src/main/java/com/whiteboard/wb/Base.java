package com.whiteboard.wb;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 10/18/11
 * Time: 11:06 AM
 */
@Entity
public abstract class Base {
    @Id
    @GeneratedValue
    private long id;
}

