package com.whiteboard.wb;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 10/18/11
 * Time: 11:08 AM
 */
@Entity
class Container {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    Set<ChildTypeA> childrenA = new HashSet<ChildTypeA>();

    @OneToMany(cascade = CascadeType.ALL)
    Set<ChildTypeB> childrenB = new HashSet<ChildTypeB>();

    public void addChildA(ChildTypeA child){
        childrenA.add(child);
    }
    public void addChildB(ChildTypeB child){
        childrenB.add(child);
    }
}
