package demo.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by thibautvirolle on 14/10/15.
 */
@Repository("comments")
public class CommentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List getComments() {
        List comments = new ArrayList<Comment>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT id, content, date, validated, post_id, user_id FROM comments");
        for (Map row : rows) {
            Comment comment = new Comment();
            comment.setId((Integer) (row.get("ID")));
            comment.setContent((String) (row.get("CONTENT")));
            comment.setDate((Date) (row.get("DATE")));
            if (row.get("VALIDATED") != null)
                comment.setValidated((Boolean) row.get("VALIDATED"));
            if (row.get("POST_ID") != null)
                comment.setPostId((Integer) row.get("POST_ID"));
            if (row.get("USER_ID") != null)
                comment.setUserId((Integer) row.get("USER_ID"));
            comments.add(comment);
        }

        return comments;
    }


    public List getCommentsByPostId(int postId) {
        List comments = new ArrayList<Comment>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT id, content, date, validated, post_id, user_id FROM comments WHERE post_id = ?", new Object[]{postId});
        for (Map row : rows) {
            Comment comment = new Comment();
            comment.setId((Integer) (row.get("ID")));
            comment.setContent((String) (row.get("CONTENT")));
            comment.setDate((Date) (row.get("DATE")));
            if (row.get("VALIDATED") != null)
                comment.setValidated((Boolean) row.get("VALIDATED"));
            if (row.get("POST_ID") != null)
                comment.setPostId((Integer) row.get("POST_ID"));
            if (row.get("USER_ID") != null)
                comment.setUserId((Integer) row.get("USER_ID"));
            comments.add(comment);
        }

        return comments;
    }

}
