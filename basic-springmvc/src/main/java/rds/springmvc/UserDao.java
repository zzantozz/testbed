package rds.springmvc;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/20/11
 * Time: 1:56 AM
 */
public interface UserDao {
    void makePersistent(User user);
    List<User> findAll();
}
