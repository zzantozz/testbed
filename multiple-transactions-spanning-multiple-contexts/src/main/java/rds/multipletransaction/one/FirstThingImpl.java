package rds.multipletransaction.one;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rds.multipletransaction.two.SecondThing;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 2/18/12
 * Time: 6:27 PM
 */
@Component
public class FirstThingImpl implements FirstThing {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired(required = false)
    private SecondThing secondThing;

    @Override
    @Transactional
    public void go() {
        System.out.println("Creating a Foo");
        sessionFactory.getCurrentSession().save(new Foo("bob"));
        if (secondThing != null) {
            System.out.println("Invoking nested SecondThing");
            secondThing.go();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void seeWhatsThere() {
        System.out.println("FirstThing Showing what's there");
        for (Object o : sessionFactory.getCurrentSession().createQuery("from Foo").list()) {
            System.out.println(o);
        }
        if (secondThing != null) {
            System.out.println("Invoking nested SecondThing");
            secondThing.seeWhatsThere();
        }
    }

    public void setSecondThing(SecondThing secondThing) {
        this.secondThing = secondThing;
    }
}
