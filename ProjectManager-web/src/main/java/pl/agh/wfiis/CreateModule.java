package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import pl.agh.wfiis.database.Module;
import pl.agh.wfiis.model.ProjectDatabaseController;

/**
 * Operations needed to create new module in the database.
 */
@Named(value = "createModule")
@SessionScoped
public class CreateModule implements Serializable {
    
    /**
    * Logger object for log operations which facilitates development process.
    */
    Logger logger = Logger.getLogger(getClass().getName());
    /**
     * Variable for storing Project related ID.
     */
    private int ProjectId;
    
    /**
     * Variable for storing module title.
     */
    private String title;
    
    /**
     * Variable for storing module description.
     */
    private String description;
    
    /**
     * Variable for storing readme links.
     */
    private String readmeLink;

    /**
     * Database controller object used for project related operations.
     */
    @EJB
    private ProjectDatabaseController projectDatabaseControler;
    
    /**
     * Creates a new instance of CreateModule
     */
    public CreateModule() {
    }
    
    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int ProjectId) {
        this.ProjectId = ProjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        logger.info("Ustawiam " + title);
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReadmeLink() {
        return readmeLink;
    }

    public void setReadmeLink(String readmeLink) {
        this.readmeLink = readmeLink;
    }
       
    /**
     * Retrieve module from the database with ID given in URL.
     */
    public void prepareBean() {
        logger.info("Creating new module for Project id: " + this.ProjectId);

    }
    
    /**
     * Function for form processing. It modifies module tuple in table.
     */
    public String create() {
        Module module = new Module();
        module.setTitle(title);
        module.setDescription(description);
        module.setReadmelink(readmeLink);
        module.setProjectid(projectDatabaseControler.getProjectByID(ProjectId));
        module.setRecruting(Boolean.FALSE);

        projectDatabaseControler.createModule(module);
        
        return ("/userProtected/modify_project.xhtml?id=" + ProjectId);
    }
   
}
