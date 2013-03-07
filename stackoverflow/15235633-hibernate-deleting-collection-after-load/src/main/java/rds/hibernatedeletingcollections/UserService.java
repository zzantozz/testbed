package rds.hibernatedeletingcollections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 3/5/13
 * Time: 9:23 PM
 */
@Service
public class UserService {
    @Autowired UserRepository userRepository;

    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void createSome() {
        User bob = new User();
        bob.setName("bob");
        Role admin = new Role();
        admin.setName("admin");
        Role user = new Role();
        user.setName("user");
        bob.setRoles(new HashSet<>(Arrays.asList(admin, user)));
        userRepository.save(bob);
    }
}
