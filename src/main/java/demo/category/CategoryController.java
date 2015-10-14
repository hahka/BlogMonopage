package demo.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CategoryDao categoryDao;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String getCategoriesListView(Model model) {
        model.addAttribute("categories", categoryDao.getCategories());
        return "category/categories_list";
    }

    @RequestMapping(value = "/categories_raw", method = RequestMethod.GET)
    public
    @ResponseBody
    List getCategories() {
        return categoryDao.getCategories();
    }



    @RequestMapping(value = "/categories_dropdown", method = RequestMethod.GET)
    public String getCategoriesDropdownView(Model model) {
        model.addAttribute("categories", categoryDao.getCategories());
        return "category/categories_dropdown";
    }


}
