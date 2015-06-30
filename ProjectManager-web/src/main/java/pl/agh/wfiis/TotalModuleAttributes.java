package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import pl.agh.wfiis.database.Asksforcollaboration;
import pl.agh.wfiis.database.Invitestoprojects;
import pl.agh.wfiis.database.Module;
import pl.agh.wfiis.database.TechnologiesToModules;
import pl.agh.wfiis.database.Technology;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.database.UsersToModules;
import pl.agh.wfiis.model.ProjectDatabaseController;

/**
 *
 * @author hawker
 */
@Named(value = "totalModuleAttributes")
@SessionScoped
public class TotalModuleAttributes implements Serializable {

    @EJB
    private ProjectDatabaseController projectDatabaseController;
    
    private List<User> userList;
    private List<Technology> technologyList;

    Logger logger = Logger.getLogger(getClass().getName());
    private Object Iterables;
    
    /**
     * @return the userList
     */
    public List<User> getUserList(int id) {
        Module m = projectDatabaseController.getModuleByID(id);
        this.userList = new ArrayList<>();
        for(UsersToModules um : m.getUsersToModulesCollection())
        {
            this.userList.add(um.getUserid());
        }
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * @return the technologyList
     */
    public List<Technology> getTechnologyList(int id) {
        this.technologyList = new ArrayList<>();
        Module m = projectDatabaseController.getModuleByID(id);

        for(TechnologiesToModules um : m.getTechnologiesToModulesCollection()){
            technologyList.add(um.getTechnologyid());
        }
        return technologyList;
    }

    /**
     * @param technologyList the technologyList to set
     */
    public void setTechnologyList(List<Technology> technologyList) {
        this.technologyList = technologyList;
    }
    
    public Module getModuleById(int id){
        Module m = projectDatabaseController.getModuleByID(id);
        return m;
    }
   
    public void acceptInvitation(int invid){
        Invitestoprojects inv = projectDatabaseController.getInvitestoprojectById(invid);
        projectDatabaseController.addUserToModule(inv);
        projectDatabaseController.deleteInvitation(inv);
    }
    public void acceptAsk(int askid){
        Asksforcollaboration ask = projectDatabaseController.getAsksforcollaborationById(askid);
        projectDatabaseController.addUserToModule(ask);
        projectDatabaseController.deleteAsk(ask);
    }
}
