package demo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by thibautvirolle on 14/10/15.
 */
@Repository("categories")
public class CategoryDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List getCategories() {
        List categories = new ArrayList<Category>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT id, name, category_id FROM categories");
        for (Map row : rows) {
            Category category = new Category();
            category.setId((Integer) (row.get("ID")));
            category.setName((String) (row.get("NAME")));
            if (row.get("CATEGORY_ID") != null)
                category.setCategoryId((Integer) row.get("CATEGORY_ID"));
            categories.add(category);
        }

        return categories;
    }


}
