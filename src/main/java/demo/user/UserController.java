package demo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsersListView(Model model) {
        model.addAttribute("users", userDao.getUsers());
        return "user/users_list";
    }

    @RequestMapping(value = "/usersNg", method = RequestMethod.GET)
    public
    @ResponseBody
    List getUsersNg() {
        return userDao.getUsers();
    }

    @RequestMapping(value="/newuser", method=RequestMethod.GET)
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/new_user";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public @ResponseBody
    User newUserSubmit(@ModelAttribute User user) {

        jdbcTemplate.update(
                "INSERT INTO users(user_name) VALUES (?)", new Object[]{user.getUserName()});

        return user;
        //return null;
    }


}
