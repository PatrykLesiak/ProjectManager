package pl.agh.wfiis.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.facades.UserFacade;
import pl.agh.wfiis.facades.ProjectFacade;

/**
 * Statefull bean responsible for all kind of operations on the database connected with users of the application.
 */
@Stateful
@LocalBean
public class UserDatabaseController {

    /**
     * Facade to database for User class.
     */
    @EJB
    private UserFacade userFacade;
    
    /**
     * Facade to database for Project class.
     */
    @EJB
    private ProjectFacade projectFacade;
    
    /**
     * Logger object for log operations which facilitates development process.
     */
    Logger logger = Logger.getLogger(getClass().getName());
    
    /**
     * Function responsible for writing user data into Users table in the database.
     * After this operation user will be register in the database.
     * 
     * @param user Filled object representing user to be register.
     */
    public void registerUserInDatabase(User user) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytesPassword = digest.digest(user.getPassword().getBytes("UTF-8"));
            StringBuffer result = new StringBuffer();
            for (byte byt : bytesPassword) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
            user.setPassword(result.toString());
            userFacade.create(user);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException exception) {
            Logger.getLogger(UserDatabaseController.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    /**
     * Function is responsible for retrieving user object by its email.
     * 
     * @param email Email of the user to be found.
     * @return      User object or null pointer when user cannot be found.
     */
    public User findUserByEmail(String email) {
        List<User> userList = userFacade.findAll();
        
        for (User user: userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        
        return null;
    }
    
}
