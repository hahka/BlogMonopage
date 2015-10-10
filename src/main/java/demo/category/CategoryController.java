package demo.category;

import demo.post.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    //JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/categories", method= RequestMethod.GET)
    public @ResponseBody
    Category[] categories() {

        /*List results = jdbcTemplate.query(
                "SELECT id, name, category_id FROM categories", new BeanPropertyRowMapper(Category.class));

        return (Category[]) results.toArray(new Category[results.size()]);*/
        return null;
    }


    @ModelAttribute("categories")
    public List<Post> getCategoriesList() {
        //return jdbcTemplate.query("SELECT id, name, category_id FROM categories", new BeanPropertyRowMapper(Category.class));
        return null;
    }


    @RequestMapping(value = "/categories_dropdown", method = RequestMethod.GET)
    public String getCategoriesDropdownView(Model model) {
        return "category/categories_dropdown";
    }
}
