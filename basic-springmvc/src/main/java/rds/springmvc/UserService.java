package rds.springmvc;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/20/11
 * Time: 1:57 AM
 */
public interface UserService {
    User createUser(User user);
    User retrieveUser(Long id);
    List<User> getAll();
}
