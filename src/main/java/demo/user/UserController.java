package demo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody
    User[] user() {

        /*ArrayList<User> users = new ArrayList<User>();

        String url = "jdbc:postgresql://ec2-54-195-248-72.eu-west-1.compute.amazonaws.com:5432/d3ck7uavpfj8h9";
        //String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";


//        DROP TABLE IF EXISTS USERS;
//        CREATE TABLE USERS(ID INT PRIMARY KEY, USER_NAME VARCHAR(255));
//        INSERT INTO USERS VALUES(1, 'Hello');
//        INSERT INTO USERS VALUES(2, 'World');
//        SELECT * FROM USERS ORDER BY ID;


        ConnectionProperties props = new ConnectionProperties(true);

        try {
            Connection conn = DriverManager.getConnection(url, props);
            PreparedStatement pstmt = conn.prepareStatement("SELECT id, user_name FROM users");

//            // cast to the pg extension interface
//            org.postgresql.PGStatement pgstmt = (org.postgresql.PGStatement)pstmt;
//
//            // on the third execution start using server side statements
//            pgstmt.setPrepareThreshold(3);
//
//            //pstmt.setInt(1,i);
//            boolean usingServerPrepare = pgstmt.isUseServerPrepare();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("USER_NAME");
                int id = rs.getInt("ID");
                users.add(new User(id,userName));
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return (User[])users.toArray(new User[users.size()]);*/


        List results = jdbcTemplate.query(
                "SELECT id, user_name FROM users", new BeanPropertyRowMapper(User.class));

        return (User[]) results.toArray(new User[results.size()]);
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
