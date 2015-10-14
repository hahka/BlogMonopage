package demo.category;

/**
 * Created by thibautvirolle on 23/09/15.
 */
public class Category {

    private long id;
    private long categoryId;
    private String name;

    public Category() {
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Category[id=%d, name='%s', parent_category_id='%d']",
                id, name, categoryId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryId() {
        this.categoryId = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
