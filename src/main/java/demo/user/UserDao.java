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


}