package demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by thibautvirolle on 30/10/15.
 */
@Component
@Qualifier("Authentication")
public class Authentication {

    private User user;

    @Autowired
    public void init() {
        this.user = null;
    }

    public User getLoggedInUser() {
        return this.user;
    }

    public void setLoggedInUser(User user) {
        this.user = user;
    }

}
