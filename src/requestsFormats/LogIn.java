package requestsFormats;
/**
 * AP-Project-Phase 3
 * @author Samin Mahdipour
 * @since 12.22.2021
 * @version 3.0
 * This class defines logIn
 */
public class LogIn {
    String id;
    String password;

    /**
     * constructor
     * @param id data
     * @param password data
     */
    public LogIn(String id, String password) {
        this.id = id;
        this.password = password;
    }

    /**
     * getter
     * @return data
     */
    public String getId() {
        return id;
    }

    /**
     * setter
     * @param id set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * getter
     * @return data
     */
    public String getPassword() {
        return password;
    }
    /**
     * setter
     * @param password set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
