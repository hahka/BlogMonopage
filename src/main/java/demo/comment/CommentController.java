package demo.comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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


}
