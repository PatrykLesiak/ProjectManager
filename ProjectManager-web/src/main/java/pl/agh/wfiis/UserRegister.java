package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.model.UserDatabaseController;

/**
 * Managed bean for view used for process of registering user in the project database.
 */
@Named(value = "userRegister")
@SessionScoped
public class UserRegister implements Serializable {
    
    /**
     * Logger object for log operations which facilitates development process.
     */
    Logger logger = Logger.getLogger(getClass().getName());
    
    /**
     * Database controller object used for user related operations.
     */
    @EJB
    private UserDatabaseController userDatabaseController; 
    
    /**
     * Field for handling email data from the form.
     */
    private String email = "";

    public String getEmail() {
        return email;
    }
    
    /**
     * Field for handling password data from the form.
     */
    private String password = "";

    public String getPassword() {
        return password;
    }

    /**
     * Function used for handling data from the form.
     * 
     * @param email User email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Function used for handling data from the form.
     * 
     * @param password User password in plain text.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Create and pass user object to controller of the database.
     * 
     * @return Navigation destiny returned after form processing.
     */
    public String registerUserInDatabase() {
        User newUser = new User();
        newUser.setEmail(this.email);
        newUser.setPassword(this.password);
        
        userDatabaseController.registerUserInDatabase(newUser);
        logger.info("New user should be registrated in the database");
        
        return "/index";
    }
}
