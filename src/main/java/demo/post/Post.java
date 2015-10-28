package demo.post;

import java.awt.*;
import java.sql.Date;

/**
 * Created by thibautvirolle on 23/09/15.
 * L'objet Post ne correspond pas à celui présent en base de données, mais à celui que l'on veut afficher sur le blog.
 * Il faut donc qu'il contienne un champ categoryName, bien qu'il soit récupéré grace à une jointure SQL.
 */
public class Post {

    private long id;
    private long userId;
    private long categoryId;
    private String categoryName;
    private String title;
    private String content;
    private Image image;
    private Date date;

    public Post() {
    }

    public Post(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Post(String title, long categoryId, String content) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format(
                "Post[id=%d, title='%s', content='%s', categoryName='%s']",
                id, title, content, categoryName);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
