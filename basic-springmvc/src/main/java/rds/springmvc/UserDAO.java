package rds.springmvc;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/17/11
 * Time: 11:34 AM
 */
@Repository
public class UserDAO {
@Autowired(required=true)
private SessionFactory sessionFactory;

public User findById(Long id) {
    return (User) this.sessionFactory.getCurrentSession().createQuery(
        "from User user where user.id=?").setLong(0, id)
        .uniqueResult();
}

public User persistOrMerge(User user) {
    return (User) this.sessionFactory.getCurrentSession().merge(user);
}
}
