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
import pl.agh.wfiis.database.Groups;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.database.UsersGroups;
import pl.agh.wfiis.facades.GroupsFacade;
import pl.agh.wfiis.facades.UserFacade;
import pl.agh.wfiis.facades.ProjectFacade;
import pl.agh.wfiis.facades.UsersGroupsFacade;

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
     * Facade to database for Groups class.
     */
    @EJB
    private GroupsFacade groupsFacade;
    
    /**
     * Facade to database for UsersGroups class.
     */
    @EJB
    private UsersGroupsFacade usersGroupsFacade ;
    
    /**
     * Logger object for log operations which facilitates development process.
     */
    Logger logger = Logger.getLogger(getClass().getName());
    
    /**
     * Assign user with given email to loggedUser group.
     * 
     * @param email User email
     */
    private void assignUserToLoggedUserGroup(String email) {
        List<Groups> groups = groupsFacade.findAll();
        List<User> users = userFacade.findAll();
        Groups foundedLoggedUserGroupId = null;
        User foundedUser = null;
        
        for (Groups group: groups) {
            if (group.getGroupName().equals("loggedUser")) {
                foundedLoggedUserGroupId = group;
                break;
            }
        }
        
        if (foundedLoggedUserGroupId == null) {
            logger.severe("Error: There is no group named loggedUser.");
            return;
        }
        
        for (User user: users) {
            if (user.getEmail().equals(email)) {
                foundedUser = user;
                break;
            }
        }
        
        if (foundedUser == null) {
            logger.severe("Error: There is no user with " + email + "email address.");
            return;
        }
        
        UsersGroups usersGroupsEntity = new UsersGroups();
        usersGroupsEntity.setGroupId(foundedLoggedUserGroupId);
        usersGroupsEntity.setUserId(foundedUser);
        usersGroupsFacade.create(usersGroupsEntity);
    }
    
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
            assignUserToLoggedUserGroup(user.getEmail());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException exception) {
            Logger.getLogger(UserDatabaseController.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    /**
     * Retrieve user object by its email.
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
    
    /**
     * Check if given user is in specyfied group.
     * 
     * @param email User email.
     * @param groupName Group name.
     * @return Belonging user to given group.
     */
    public Boolean isUserInGroup(String email, String groupName) {
        List<UsersGroups> list = usersGroupsFacade.findAll();
        for (UsersGroups tuple : list) {
            User user = tuple.getUserId();
            Groups group = tuple.getGroupId();
            if(user.getEmail().equals(email) && group.getGroupName().equals(groupName)) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Retrieves User object with given email.
     * 
     * @param email User email to be found.
     * @return Founded User object or null if user does not exist.
     */
    public User getUserByEmail(String email) {
        List<User> users = userFacade.findAll();
        for(User user: users) {
            if(user.getEmail().equals(email) ) {
                return user;
            }
        }
        return null;
    }
    
    public void modifyUser(User user) {
        userFacade.edit(user);
        
    }
    
}
