package pl.agh.wfiis.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.facades.ProjectFacade;
import pl.agh.wfiis.facades.UserFacade;
import pl.agh.wfiis.database.User;


@Stateless
@LocalBean
public class ProjectDatabaseController {

    Logger logger = Logger.getLogger(getClass().getName());
    
    @EJB
    private ProjectFacade projectFacade;
    
    @EJB 
    private UserFacade userFacade;
    
    public List<Project> getAllProjects() {
        List<Project> listOfAllProjects = projectFacade.findAll();      
        return listOfAllProjects;
    }
    
    public void createNewProjectInDatabase(Project newProject, int leaderId){
        User leader = userFacade.find(leaderId);
        newProject.setLeaderid(leader);
        projectFacade.create(newProject);   
        logger.info("New project should be registrated in the database");
    }
}
