package demo.comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CommentDao commentDao;

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public String getCategoriesListView(Model model) {
        model.addAttribute("comments", commentDao.getComments());
        return "comment/comments_list";
    }


    @RequestMapping(value = "/newcomment/{id}", method = RequestMethod.GET)
    public String newPostForm(@PathVariable Integer id, Model model) {

        Comment newComment = new Comment();
        newComment.setPostId(id);
        model.addAttribute("comment", newComment);

        return "comment/new_comment";
    }


    @RequestMapping(value = "/newcommentNg", method = RequestMethod.POST)
    public
    @ResponseBody
    Comment newCommentSubmit(HttpServletRequest request) {

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

        long postId = Long.valueOf(postParamsMap.get("postId"));
        String content = postParamsMap.get("content");


        Comment comment = new Comment(content, postId);

        jdbcTemplate.update(
                "INSERT INTO comments(content, post_id) VALUES (?, ?)",
                comment.getContent(), comment.getPostId());

        return comment;
    }




}
