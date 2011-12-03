package rds.springmvc;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/20/11
 * Time: 1:57 AM
 */
public interface UserService {
    void createUser(User user);
    List<User> getAll();
}
