package demo.user;

/**
 * Created by thibautvirolle on 23/09/15.
 */
public class User {

    private long id;
    private String userName;

    public User() {
    }

    public User(long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, user_name='%s']",
                id, userName);
    }

    public long getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

}
