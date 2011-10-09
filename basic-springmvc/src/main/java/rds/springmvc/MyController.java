package rds.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/17/11
 * Time: 11:34 AM
 */
@Controller
public class MyController {

    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    private UserService userService;

    @Autowired
    public MyController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String users(@ModelAttribute ArrayList<User> users) {
        log.info("Showing users");
        users.addAll(this.userService.getAll());
        return "users";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createUser(User user, @ModelAttribute ArrayList<User> users) {
        log.info("Creating user");
        userService.createUser(user);
        users.addAll(this.userService.getAll());
        return "users";
    }

}
