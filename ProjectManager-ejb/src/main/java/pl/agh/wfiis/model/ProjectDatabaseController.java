package pl.agh.wfiis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import pl.agh.wfiis.database.Module;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.database.Technology;
import pl.agh.wfiis.facades.ProjectFacade;
import pl.agh.wfiis.facades.UserFacade;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.facades.ModuleFacade;
import pl.agh.wfiis.facades.TechnologyFacade;


@Stateless
@LocalBean
public class ProjectDatabaseController {

    Logger logger = Logger.getLogger(getClass().getName());
    
    @EJB
    private ProjectFacade projectFacade;
    
    @EJB 
    private UserFacade userFacade;
    
    @EJB
    private ModuleFacade moduleFacade;
    
    @EJB
    private TechnologyFacade technologyFacade;
    
    public List<Project> getAllProjects() {
        List<Project> listOfAllProjects = projectFacade.findAll();      
        return listOfAllProjects;
    }
         
    public List<Project> getRandomProjects(int number) {
        int projectCount = projectFacade.count();
        List<Project> listOfRandomProjects = new ArrayList<>();
        Random r = new Random();
        for(int i =0;i<number;++i){
            int randomId = r.nextInt(projectCount)+1;
            listOfRandomProjects.add(projectFacade.find(randomId));     
        }
        return listOfRandomProjects;
    }
    
    public void createNewProjectInDatabase(Project newProject, int leaderId){
        User leader = userFacade.find(leaderId);
        newProject.setLeaderid(leader);
        projectFacade.create(newProject);   
        logger.info("New project should be registrated in the database");
    }
    
    public Project getProjectByID(int Id){
        return projectFacade.find(Id);
    }
    
    public Module getModuleByID(int Id) {
        return moduleFacade.find(Id);
    }
    
    public Technology getTechnologyid(int Id) {
        return technologyFacade.find(Id);
    }
    
    public User getUserById(int id){
        return userFacade.find(id);
    }
}
