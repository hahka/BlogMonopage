package demo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/newuser/{email}", method = RequestMethod.GET)
    public String newUserForm(@PathVariable String email, Model model) {

        User existingUser = userDao.getUserByEmail(email);
        System.out.println(email);
        System.out.println(existingUser);
        if (existingUser == null) {
            User user = new User();
            user.setEmail(email);
            model.addAttribute("user", user);
            return "user/new_user";
        } else {
            model.addAttribute("user", existingUser);
            return "user/user_logged_in";
        }

    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public @ResponseBody
    User newUserSubmit(@ModelAttribute User user) {

        jdbcTemplate.update(
                "INSERT INTO users(user_name) VALUES (?)", new Object[]{user.getUserName()});

        return user;
    }


    @RequestMapping(value = "/userLoggedIn/{email}", method = RequestMethod.GET)
    public String getPostDetailsView(@PathVariable String email, Model model) {
        model.addAttribute("user", userDao.getUserByEmail(email));
        return "user/user_logged_in";
    }


    @RequestMapping(value = "/newuserNg", method = RequestMethod.POST)
    public
    @ResponseBody
    User newUserSubmit(HttpServletRequest request) {

        StringBuffer userParams = new StringBuffer();
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                userParams.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String userParamsString = userParams.toString();

        Map<String, String> userParamsMap = new LinkedHashMap<String, String>();
        for (String keyValue : userParamsString.split(" *& *")) {
            String[] pairs = keyValue.split(" *= *", 2);
            userParamsMap.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
        }

        String email = null;
        try {
            email = URLDecoder.decode(userParamsMap.get("email"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String userName = userParamsMap.get("username");

        User user = new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setRoleId(3);
        userDao.insertUser(user);

        return user;
    }



}
