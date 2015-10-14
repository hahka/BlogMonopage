package demo.post;

import demo.category.CategoryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CategoryDao categoryDao;

    @ModelAttribute("posts")
    public List getPostsList() {
        return jdbcTemplate.query(
                "SELECT id, title, content FROM posts ORDER BY date DESC", new BeanPropertyRowMapper(Post.class));
    }


    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String getPostsListView(Model model) {
        return "post/posts_list";
    }



    @RequestMapping(value="/newpost", method=RequestMethod.GET)
    public String newPostForm(Model model) {

        model.addAttribute("post", new Post());
        model.addAttribute("categories", categoryDao.getCategories());

        return "post/new_post";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public @ResponseBody
    Post newPostSubmit(@ModelAttribute Post post) {

        jdbcTemplate.update(
                "INSERT INTO posts(title, content, category_id) VALUES (?, ?, ?)",
                post.getTitle(), post.getContent(), post.getCategoryId());

        return post;
        //return null;
    }


}
