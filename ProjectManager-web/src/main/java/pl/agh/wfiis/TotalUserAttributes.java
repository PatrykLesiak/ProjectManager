package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import pl.agh.wfiis.database.Invitestoprojects;
import pl.agh.wfiis.database.Module;
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
    
    public boolean isInModule(int userid, int moduleid){
        if(userid == 0) return false;
        User u = projectDatabaseControler.getUserById(userid);
        Module m = projectDatabaseControler.getModuleByID(moduleid);
        boolean result = projectDatabaseControler.isUserInModule(u,m);
        return result;
    }
    public String applyToModule(int userid, int moduleid){
        if(userid == 0) return "";
        User u = projectDatabaseControler.getUserById(userid);
        Module m = projectDatabaseControler.getModuleByID(moduleid);
        projectDatabaseControler.userApplyToModule(u, m);
        return "";
    }
    
    public boolean alreadyApplied(int userid, int moduleid){
        if(userid == 0) return false;
        User u = projectDatabaseControler.getUserById(userid);
        Module m = projectDatabaseControler.getModuleByID(moduleid);
        boolean result = projectDatabaseControler.userAlreadyApplied(u,m);
        return result;
    }
    
    public List<Module> getAllLideredModules(int userid){
        User u = projectDatabaseControler.getUserById(userid);
        return projectDatabaseControler.getAllLideredProjects(u);
        }
    
    public void inviteToModule(int userid, int moduleid){
        User u = projectDatabaseControler.getUserById(userid);
        Module m = projectDatabaseControler.getModuleByID(moduleid);
        projectDatabaseControler.inviteUserToModule(u,m);
    }
    
    public boolean alreadyInvitedToModule(int userid, int moduleid){
        User u = projectDatabaseControler.getUserById(userid);
        Module m = projectDatabaseControler.getModuleByID(moduleid);
        return projectDatabaseControler.userAlreadyInvited(u,m);
    }
    
    public boolean userIsLeader(int userid){
        User u = projectDatabaseControler.getUserById(userid);
        return !u.getProjectCollection().isEmpty();
    }
}
