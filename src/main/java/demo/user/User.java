package demo.user;

/**
 * Created by thibautvirolle on 23/09/15.
 */
public class User {

    private long id;
    private String userName;
    private String email;

    private int roleId;
    private String role;

    public User() {
    }

    public User(long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, user_name='%s', email='%s']",
                id, userName, email);
    }

    public long getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
