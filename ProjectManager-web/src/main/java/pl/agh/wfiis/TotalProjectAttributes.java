package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import pl.agh.wfiis.database.Module;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.database.TechnologiesToModules;
import pl.agh.wfiis.database.Technology;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.database.UsersToModules;
import pl.agh.wfiis.model.ProjectDatabaseController;

/**
 *
 * @author hawker
 */
@Named(value = "totalProjectAttributes")
@RequestScoped
public class TotalProjectAttributes implements Serializable {

    @EJB
    private ProjectDatabaseController projectDatabaseControler;
    
    Logger logger = Logger.getLogger(getClass().getName());
    
    private List<User> totalPeople;
    private List<Module> totalModules;
    private List<Technology> totalTechnology;

    /**
     * @return the totalPeople
     */
    public List<User> getTotalPeople(int Id) {
        Project p = projectDatabaseControler.getProjectByID(Id);
        List<Module> moduleList = (List)p.getModuleCollection();
        HashSet<User> userDictionary = new HashSet<>();
        for(Module m : moduleList){
            for(UsersToModules um : m.getUsersToModulesCollection()){
                userDictionary.add(um.getUserid());
            }
        }
        this.totalPeople = new ArrayList<>(userDictionary);
        return this.totalPeople;
    }

    /**
     * @param totalPeople the totalPeople to set
     */
    public void setTotalPeople(List<User> totalPeople) {
        
        this.totalPeople = totalPeople;
    }

    /**
     * @return the totalModules
     */
    public List<Module> getTotalModules(int id) {
        Project p = projectDatabaseControler.getProjectByID(id);
        this.totalModules = new ArrayList<>();
        for(Module m : p.getModuleCollection()){
            this.totalModules.add(m);
        }
        return totalModules;
    }

    /**
     * @param totalModules the totalModules to set
     */
    public void setTotalModules(List<Module> totalModules) {
        this.totalModules = totalModules;
    }

    /**
     * @return the totalTechnology
     */
    public List<Technology> getTotalTechnology(int id) {
        Project p = projectDatabaseControler.getProjectByID(id);
        this.totalTechnology = new ArrayList<>();
        for(Module m : p.getModuleCollection()){
            for(TechnologiesToModules um : m.getTechnologiesToModulesCollection()){
                Technology t = um.getTechnologyid();
                this.totalTechnology.add(t);
            }
        }
        return this.totalTechnology;
    }

    /**
     * @param totalTechnology the totalTechnology to set
     */
    public void setTotalTechnology(List<Technology> totalTechnology) {
        this.totalTechnology = totalTechnology;
    }
    
    public static String boolToClassText(boolean val){
        return val ? "": "closed";
    }
    public List<Project> getRandomProjectList(){
        int number = 3;
        return projectDatabaseControler.getRandomProjects(number);
    }
    
    public List<Project> getLeadersProjects(int ID) {
        return projectDatabaseControler.getLeadersProjects(ID);
    }
    
    
}
