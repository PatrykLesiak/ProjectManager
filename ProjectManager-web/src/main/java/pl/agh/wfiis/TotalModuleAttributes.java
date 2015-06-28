/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
   
}
