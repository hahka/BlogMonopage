package demo.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by thibautvirolle on 17/10/15.
 */
@Repository("posts")
public class PostDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List getPosts() {
        return jdbcTemplate.query(
                "SELECT p.id, p.title, p.content, c.name as categoryName " +
                        "FROM posts p INNER JOIN categories c ON p.category_id = c.id " +
                        "ORDER BY date DESC", new BeanPropertyRowMapper(Post.class));
    }


}
