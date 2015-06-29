package pl.agh.wfiis;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import pl.agh.wfiis.database.Module;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.model.ProjectDatabaseController;

@Named(value = "modifyModule")
@SessionScoped
public class ModifyModule implements Serializable {
    
    /**
    * Logger object for log operations which facilitates development process.
    */
    
    Logger logger = Logger.getLogger(getClass().getName());
    /**
     * Variable for storing module ID.
     */
    private int moduleId;

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public Project getProjectId() {
        return ProjectId;
    }

    public void setProjectId(Project ProjectId) {
        this.ProjectId = ProjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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

    public Boolean getRecruiting() {
        return recruiting;
    }

    public void setRecruiting(Boolean recruiting) {
        this.recruiting = recruiting;
    }
    
    /**
     * Variable for storing Project related ID.
     */
    private Project ProjectId;
    
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
     * Variable for storing information about recruitment.
     */
    private Boolean recruiting;
    
    /**
     * Variable for storing module object for modification.
     */
    private Module module;

    /**
     * Database controller object used for project related operations.
     */
    @EJB
    private ProjectDatabaseController projectDatabaseControler;
    
    /**
     * Creates a new instance of ModifyModule
     */
    public ModifyModule() {
    }
    
    /**
     * Retrieve module from the database with ID given in URL.
     */
    public void prepareBean() {
        logger.info("Prepering module ID: " + moduleId);
        this.module = projectDatabaseControler.getModuleByID(moduleId);
        ProjectId = module.getProjectid();
        title = module.getTitle();
        description = module.getDescription();
        readmeLink = module.getReadmelink();
        recruiting = module.getRecruting();  
    }
    
    /**
     * Function for form processing. It modifies module tuple in table.
     */
    public void modify() {
        module.setTitle(title);
        module.setDescription(description);
        module.setReadmelink(readmeLink);
        module.setRecruting(recruiting);

        projectDatabaseControler.modifyModule(module);
    }
    
}
