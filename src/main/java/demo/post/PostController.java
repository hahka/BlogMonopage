package demo.post;

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


    @ModelAttribute("posts")
    public List<Post> getPostsList() {
        return jdbcTemplate.query("SELECT id, title, content FROM posts", new BeanPropertyRowMapper(Post.class));
        //return null;
    }


    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String getPostsListView(Model model) {
        return "post/posts_list";
    }



    @RequestMapping(value="/newpost", method=RequestMethod.GET)
    public String newPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/new_post";
    }
    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public @ResponseBody
    Post newPostSubmit(@ModelAttribute Post post) {

        jdbcTemplate.update(
                "INSERT INTO posts(title) VALUES (?)", new Object[]{post.getTitle()});

        return post;
        //return null;
    }


}
