package demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by thibautvirolle on 17/10/15.
 */
@Repository("users")
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List getUsers() {
        return jdbcTemplate.query(
                "SELECT id, user_name FROM users", new BeanPropertyRowMapper(User.class));
    }


    public User getUserById(int id) {
        List users = jdbcTemplate.query(
                "SELECT user_name " +
                        "FROM users u " +
                        "WHERE u.id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper(User.class));

        return (users.size() > 0 ? (User) users.get(0) : null);
    }

    public User getUserById(long id) {
        List users = jdbcTemplate.query(
                "SELECT user_name " +
                        "FROM users u " +
                        "WHERE u.id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper(User.class));

        return (users.size() > 0 ? (User) users.get(0) : null);
    }

    public int insertUser(User user) {
        return jdbcTemplate.update(
                "INSERT INTO users(email, user_name, role_id) VALUES (?, ?, ?)",
                user.getEmail(), user.getUserName(), user.getRoleId());
    }

    public User getUserByEmail(String email) {
        List users = jdbcTemplate.query(
                "SELECT user_name, id, email, role_id " +
                        "FROM users u " +
                        "WHERE u.email = ?",
                new Object[]{email},
                new BeanPropertyRowMapper(User.class));

        return (users.size() > 0 ? (User) users.get(0) : null);
    }

    public User getUserByUserName(String username) {
        List users = jdbcTemplate.query(
                "SELECT user_name, id, email, role_id " +
                        "FROM users u " +
                        "WHERE u.user_name = ?",
                new Object[]{username},
                new BeanPropertyRowMapper(User.class));

        return (users.size() > 0 ? (User) users.get(0) : null);
    }
}