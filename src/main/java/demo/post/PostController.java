package demo.post;

import demo.Utils;
import demo.category.CategoryDao;
import demo.comment.CommentDao;
import demo.user.Authentication;
import demo.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;


@Controller
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PostDao postDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    Authentication loggedInUser;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String getPostsListView(Model model) {
        model.addAttribute("posts", postDao.getPosts());
        User user = loggedInUser.getLoggedInUser();


        if (postDao.getPosts().size() > 0) {
            if (user != null) {
                if (user.getRoleId() == 1 || user.getRoleId() == 2) {
                    return "post/posts_list_with_addition";
                } else {
                    return "post/posts_list";
                }
            } else {
                return "post/posts_list";
            }
        } else {
            if (user != null) {
                if (user.getRoleId() == 1 || user.getRoleId() == 2) {
                    return "post/posts_list_empty_with_addition";
                } else {
                    return "post/posts_list_empty";
                }
            } else {
                return "post/posts_list_empty";
            }
        }

    }


    @RequestMapping(value = "/postsByUserId/{id}", method = RequestMethod.GET)
    public String getPostsListByUserView(@PathVariable Integer id, Model model) {

        model.addAttribute("posts", postDao.getPostsByUserId(id));
        User user = loggedInUser.getLoggedInUser();


        if (postDao.getPostsByUserId(id).size() > 0) {
            if (user != null) {
                if (user.getRoleId() == 1 || user.getRoleId() == 2) {
                    return "post/posts_list_with_addition";
                } else {
                    return "post/posts_list";
                }
            } else {
                return "post/posts_list";
            }
        } else {
            if (user != null) {
                if (user.getRoleId() == 1 || user.getRoleId() == 2) {
                    return "post/posts_list_empty_with_addition";
                } else {
                    return "post/posts_list_empty";
                }
            } else {
                return "post/posts_list_empty";
            }
        }

    }

    @RequestMapping(value = "/postdetails/{id}", method = RequestMethod.GET)
    public String getPostDetailsView(@PathVariable Integer id, Model model) {
        model.addAttribute("post", postDao.getPostById(id));
        model.addAttribute("comments", commentDao.getCommentsByPostId(id));
        return "post/post_details";
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

        postDao.insertPost(post);
        return post;
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

        String title = null;
        long categoryId = -1;
        String content = null;

        try {
            title = Utils.uriDecoded(postParamsMap.get("title"));
            categoryId = Long.valueOf(Utils.uriDecoded(postParamsMap.get("categoryId")));
            content = Utils.uriDecoded(postParamsMap.get("content"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Post post = new Post(title, categoryId, content);

        if (loggedInUser.getLoggedInUser() != null)
            post.setUserId(loggedInUser.getLoggedInUser().getId());
        postDao.insertPost(post);

        return post;
    }



}
