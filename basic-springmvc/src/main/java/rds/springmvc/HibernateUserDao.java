package rds.springmvc;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/17/11
 * Time: 11:34 AM
 */
@Repository
public class HibernateUserDao implements UserDao {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateUserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void makePersistent(User user) {
        this.sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> findAll() {
        Query query = this.sessionFactory.getCurrentSession().createQuery("from User");
        return query.list();
    }
}
