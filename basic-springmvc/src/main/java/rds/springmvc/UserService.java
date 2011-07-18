package rds.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/17/11
 * Time: 11:33 AM
 */
@Service
@Transactional
public class UserService {
@Autowired(required=true)
private UserDAO userDAO;

@Transactional
public User createUser(User user) {
    return this.userDAO.persistOrMerge(user);
}

@Transactional(readOnly=true)
public User retrieveUser(Long id) {
    return this.userDAO.findById(id);
}

}