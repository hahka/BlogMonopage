package demo.comment;

import java.util.Date;

/**
 * Created by thibautvirolle on 23/09/15.
 */
public class Comment {

    private long id;
    private String content;
    private long postId;
    private Date date;
    private boolean validated;
    private long userId;


    public Comment() {
    }

    public Comment(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.format(
                "Comment[id=%d, content='%s', post_id='%d', user_id='%d', validated='%b', date='%s']",
                id, content, postId, userId, validated, date.toString());
    }

}
