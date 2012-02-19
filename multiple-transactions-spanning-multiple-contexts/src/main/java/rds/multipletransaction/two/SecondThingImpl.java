package rds.multipletransaction.two;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 2/18/12
 * Time: 6:43 PM
 */
@Component
public class SecondThingImpl implements SecondThing {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void go() {
        System.out.println("Creating a Bar");
        sessionFactory.getCurrentSession().save(new Bar("fred"));
    }

    @Override
    @Transactional(readOnly = true)
    public void seeWhatsThere() {
        System.out.println("SecondThing Showing what's there");
        System.out.println(sessionFactory.getCurrentSession().createQuery("from Bar").list());
    }
}
