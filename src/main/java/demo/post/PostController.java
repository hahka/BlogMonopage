package demo.post;

import demo.category.CategoryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    PostDao postDao;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String getPostsListView(Model model) {
        model.addAttribute("posts", postDao.getPosts());
        //System.out.println(postDao.getPosts());
        return "post/posts_list";
    }


    @RequestMapping(value = "/postsNg", method = RequestMethod.GET)
    public
    @ResponseBody
    List getPostsListNg() {
        return postDao.getPosts();
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

    @RequestMapping(value = "/newpostNg", method = RequestMethod.POST)
    public
    @ResponseBody
    Post newPostSubmit(HttpServletRequest request) {

        StringBuffer postParams = new StringBuffer();
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                postParams.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String postParamsString = postParams.toString();

        Map<String, String> postParamsMap = new LinkedHashMap<String, String>();
        for (String keyValue : postParamsString.split(" *& *")) {
            String[] pairs = keyValue.split(" *= *", 2);
            postParamsMap.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
        }

        String title = postParamsMap.get("title");

        long categoryId = Long.valueOf(postParamsMap.get("categoryId"));
        String content = postParamsMap.get("content");


        Post post = new Post(title, categoryId, content);
        jdbcTemplate.update(
                "INSERT INTO posts(title, content, category_id) VALUES (?, ?, ?)",
                post.getTitle(), post.getContent(), post.getCategoryId());

        return post;
    }



}
