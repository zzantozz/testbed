package com.whiteboard.wb;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 10/18/11
 * Time: 11:06 AM
 */
@MappedSuperclass
public abstract class Base {
    @Id
    @GeneratedValue
    private long id;
}

