package pl.agh.wfiis;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.model.UserDatabaseController;

/**
 * Bean for user settings purpose.
 */
@Named(value = "userProfileManager")
@SessionScoped
public class UserProfileManager implements Serializable {

    
    /**
     * Injection of Http servlet request.
     */
    @Inject
    private HttpServletRequest request;
    
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
     * Object representing usert tuple in the database..
     */
    private User user;
    
    /**
     * User last name.
     */
    private String lastName;
    
    /**
     * User first name.
     */
    private String firstName;
    
    /**
     * User avatar link.
     */
    private String avatarLink;
    
    /**
     * User readme link.
     */
    private String readmeLink;

    /**
     * Get user last name.
     * 
     * @return 
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set user last name.
     * 
     * @param lastName User last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get user first name.
     * 
     * @return User first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set user first name.
     * 
     * @param firstName User first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get user avatar link.
     * 
     * @return User avatar link.
     */
    public String getAvatarLink() {
        return avatarLink;
    }

    /**
     * Set user avatar link.
     * 
     * @param avatarLink User avatar link.
     */
    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    /**
     * Get user readme link.
     * 
     * @return User readme link.
     */
    public String getReadmeLink() {
        return readmeLink;
    }

    /**
     * Set user readme link.
     * 
     * @param readmeLink User readme link.
     */
    public void setReadmeLink(String readmeLink) {
        this.readmeLink = readmeLink;
    }

    /**
     * Creates a new instance of UserProfileManager
     */
    public UserProfileManager() {
        
    }
       
    /**
     * Creates user object based on session data.
     */
    public void initBean() {
        user = userDatabaseController.getUserByEmail(request.getUserPrincipal().getName());      
        lastName = user.getLastname();
        firstName = user.getFirstname();
        avatarLink = user.getAvatarlink();
        readmeLink = user.getReadmelink();
    }
    
    /**
     * Modify user with given in form informations.
     * 
     * @return Navigation destiny.
     */
    public String modify() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();        
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setReadmelink(readmeLink);
        user.setAvatarlink(avatarLink);
        userDatabaseController.modifyUser(user);
        
        return "UsersProjects";
    }
    
}
