package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.model.UserDatabaseController;

@Named(value = "userRegister")
@SessionScoped
public class UserRegister implements Serializable {

    Logger logger = Logger.getLogger(getClass().getName());
    
    @EJB
    private UserDatabaseController userDatabaseController; 
    
    private String email = "";
    private String password = "";
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRegister() {
        logger.info("Object creation");
    }
    
    public String registerUserInDatabase() {
        User newUser = new User();
        newUser.setEmail(this.email);
        newUser.setPassword(this.password);
        
        userDatabaseController.registerUserInDatabase(newUser);
        logger.info("New user should be registrated in the database");
        
        return "/index";
    }
    
}
