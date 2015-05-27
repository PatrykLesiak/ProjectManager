package pl.agh.wfiis.model;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.facades.ProjectFacade;


@Stateless
@LocalBean
public class ProjectDatabaseController {

    @EJB
    private ProjectFacade projectFacade;
    
    public List<Project> getAllProjects() {
        List<Project> listOfAllProjects = projectFacade.findAll();      
        return listOfAllProjects;
    }
}
