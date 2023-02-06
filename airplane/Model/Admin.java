package Model;


import java.io.Serial;
import java.io.Serializable;

/**
 * Admin class to keeping admin attributes ...
 */
public class Admin implements Serializable {
    @Serial
    private static final long serialVersionUID = 1113799434508676095L;
    private String username;
    private String password;

    /**
     * Admin class constructor ...
     * @param username
     * @param password
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Getting admins username because it is a private field ...
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * getting admins password because it is a private field ...
     * @return
     */

    public String getPassword() {
        return password;
    }

    /**
     * setting admins password because it is a private field ...
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
