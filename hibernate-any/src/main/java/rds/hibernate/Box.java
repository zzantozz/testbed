package rds.hibernate;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/13/11
 * Time: 10:51 PM
 */
@Entity
public class Box<T> {

    @Any(metaColumn = @Column(name = "what_i_contain"))
    @Cascade(CascadeType.ALL)
    @AnyMetaDef(
        idType = "integer",
        metaType = "string",
        metaValues = {
            @MetaValue(value = "Foo", targetEntity = Foo.class),
            @MetaValue(value = "Bar", targetEntity = Bar.class)
        })
    @JoinColumn(name = "property_id")
    private T t;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    protected Box() {}

    public Box(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Box{" +
                       "t=" + t +
                       '}';
    }
}
