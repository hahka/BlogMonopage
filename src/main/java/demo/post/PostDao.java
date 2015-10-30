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
                "SELECT p.id, p.title, p.content, c.name as categoryName, u.user_name, p.user_id " +
                        "FROM posts p " +
                        "INNER JOIN categories c ON p.category_id = c.id " +
                        "INNER JOIN users u ON p.user_id = u.id " +
                        "ORDER BY date DESC", new BeanPropertyRowMapper(Post.class));
    }

    public Post getPostById(int id) {
        List posts = jdbcTemplate.query(
                "SELECT p.id, p.title, p.content, c.name as categoryName, u.user_name, p.user_id " +
                        "FROM posts p " +
                        "INNER JOIN categories c ON p.category_id = c.id " +
                        "INNER JOIN users u ON p.user_id = u.id " +
                        "WHERE p.id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper(Post.class));

        return (posts.size() > 0 ? (Post) posts.get(0) : null);
    }


    public List getPostsByUserId(int id) {
        return jdbcTemplate.query(
                "SELECT p.id, p.title, p.content, c.name as categoryName, u.user_name, p.user_id " +
                        "FROM posts p " +
                        "INNER JOIN categories c ON p.category_id = c.id " +
                        "INNER JOIN users u ON p.user_id = u.id " +
                        "WHERE p.user_id = ? " +
                        "ORDER BY date DESC ",
                new Object[]{id},
                new BeanPropertyRowMapper(Post.class));
    }

    public int insertPost(Post post) {
        return jdbcTemplate.update(
                "INSERT INTO posts(title, content, category_id, user_id, date) VALUES (?, ?, ?, ?, ?)",
                post.getTitle(), post.getContent(), post.getCategoryId(), post.getUserId(), post.getDate());
    }




}
