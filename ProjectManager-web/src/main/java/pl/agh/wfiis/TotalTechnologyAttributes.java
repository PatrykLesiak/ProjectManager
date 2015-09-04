package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.database.Technology;
import pl.agh.wfiis.model.ProjectDatabaseController;

/**
 * Managed bean for technology attributes operations.
 */
@Named(value = "totalTechnologyAttributes")
@SessionScoped
public class TotalTechnologyAttributes implements Serializable {
     /**
     * Object of database controller for Project related operations.
     */
    @EJB
    private ProjectDatabaseController projectDatabaseControler;
    
    /**
     * Returns technology by id
     * @param id
     * @return technology
     */
    public Technology getTechnologyById(int id){
        return projectDatabaseControler.getTechnologyById(id);
    }
    
    /**
     * Gets list of projects where given technology is used
     * @param id
     * @return 
     */
    public List<Project> getProjectTechnologyListById(int id){
        return projectDatabaseControler.getProjectListByTechnologyId(id);
    }
    
    /**
     * Returns all Technologies in database
     * 
     * @return          List of all Technologies.
     */
    public List<Technology> getAllTechnologiesList(){
        return projectDatabaseControler.getAllTechnologies();
    }
    
}
