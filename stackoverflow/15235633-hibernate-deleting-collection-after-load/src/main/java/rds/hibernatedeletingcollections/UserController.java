package rds.hibernatedeletingcollections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 3/5/13
 * Time: 9:23 PM
 */
@Controller
public class UserController {
    @Autowired UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers(ModelMap model) {
        model.put("users", userService.findAll());
        return "admin/users";
    }

    @RequestMapping("/putusers")
    public String createSome() {
        userService.createSome();
        return "admin/users";
    }
}
