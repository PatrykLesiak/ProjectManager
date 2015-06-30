package pl.agh.wfiis;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import pl.agh.wfiis.model.UserDatabaseController;

/**
 * Bean for performing session operation. 
 */
@Named(value = "userSessionManager")
@SessionScoped
public class UserSessionManager implements Serializable {
    /**
     * Logger object for log operations which facilitates development process.
     */
    Logger logger = Logger.getLogger(getClass().getName());
    
    /**
     * Object of database controller for User related operations.
     */
    @EJB
    private UserDatabaseController userDatabaseController;
    
    /**
     * Injection of Http servlet request.
     */
    @Inject
    private HttpServletRequest request;
    
    /**
     * Creates a new instance of Logout
     */
    public UserSessionManager() {}
    
    /**
     * Check if user is logged in.
     * 
     * @return Authentication state.
     */
    public Boolean isUserLoggedin() {
        return isUserInRole("loggedUser");
    }
    
    /**
     * Perform session destroy.
     */
    public void logOut() {
	HttpSession session = getHttpSession();
	session.invalidate();
    }
    
    /**
     * Retrieve http session.
     * 
     * @return Current Http session.
     */
    private HttpSession getHttpSession() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	return (HttpSession) facesContext.getExternalContext().getSession(false);
    }    
    
    /**
     * Check if logged user is in given role.
     * 
     * @param roleName Role to be checked.
     * @return User belonging to the specified group.
     */
    public boolean isUserInRole(String roleName) {
        if(request.getUserPrincipal() != null) {
            return userDatabaseController.isUserInGroup(request.getUserPrincipal().getName(), "loggedUser");
        }

	return false;
    }
    
}
