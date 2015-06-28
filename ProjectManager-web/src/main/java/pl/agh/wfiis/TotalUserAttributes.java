package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.model.ProjectDatabaseController;
import pl.agh.wfiis.model.UserDatabaseController;

/**
 * Managed bean for 
 */
@Named(value = "totalUserAttributes")
@SessionScoped
public class TotalUserAttributes implements Serializable {
    /**
     * Logger object for log operations which facilitates development process.
     */
    Logger logger = Logger.getLogger(getClass().getName());
    
    /**
     * Object of database controller for Project related operations.
     */
    @EJB
    private ProjectDatabaseController projectDatabaseControler;

    /**
     * Object of database controller for User related operations.
     */
    @EJB
    private UserDatabaseController userDatabaseController;
    
    /**
     * Retrieve User object from the database by its ID.
     * 
     * @param id    ID of the User to be found.
     * @return      User object based on founded tuple.
     */
    public User getUserById(int id){
        return projectDatabaseControler.getUserById(id);
    }
    
    public List<Project> getUserProjectListById(int id){
        return projectDatabaseControler.getProjectListByUserId(id);
    }
    
    /**
     * Function retrieves ID of logged user.
     * 
     * @return ID of logged user.
     */
    public int getLoggedUserId() {
        User user = null;
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        
        if (principal != null) {
            user = userDatabaseController.findUserByEmail(principal.getName());
        }
        
        if (user != null) {
            return user.getUserid();
        }
        else {
            logger.severe("There is no user despite that he is logged in");
            return 0;
        }
    }
}
