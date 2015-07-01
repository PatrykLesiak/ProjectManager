package pl.agh.wfiis;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.model.ProjectDatabaseController;


@Named(value = "modifyProject")
@SessionScoped
public class ModifyProject implements Serializable {
    
    /**
    * Logger object for log operations which facilitates development process.
    */
    Logger logger = Logger.getLogger(getClass().getName());
    
    private int projectID;

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    
    /**
     * Project title.
     */
    private String title;

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

    public Boolean getRecruting() {
        return recruting;
    }

    public void setRecruting(Boolean recruting) {
        this.recruting = recruting;
    }

    public String getContactsAndLinks() {
        return contactsAndLinks;
    }

    public void setContactsAndLinks(String contactsAndLinks) {
        this.contactsAndLinks = contactsAndLinks;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
    
    /**
     * Project description.
     */
    private String description;
    
    /**
     * Project readme link.
     */
    private String readmeLink;
    
    /**
     * Mark if recruting for project contributing is possible.
     */
    private Boolean recruting;
    
    /**
     * Project informations about contacts and related links.
     */
    private String contactsAndLinks;
    
    /**
     * Project picture link.
     */
    private String pictureLink;
    
    /**
     * Database controller object used for project related operations.
     */
    @EJB
    private ProjectDatabaseController projectDatabaseControler;
    
    /**
     * Project object under modification.
     */
    private Project project;
    
    /**
     * Empty constructor of bean.
     */
    public ModifyProject() {
    }
    
    /**
     * Retrieve project from the database with ID given in URL.
     */
    public void prepareBean() {
        this.project = projectDatabaseControler.getProjectByID(projectID);
        title = project.getTitle();
        description = project.getDescription();
        readmeLink = project.getRreadmelink();
        recruting = project.getRecruting();
        contactsAndLinks = project.getContactandlinks();
        pictureLink = project.getPicturelink();
        logger.info(project.getTitle());
    }
    
    public String modifyProject() {
        project.setTitle(title);
        project.setDescription(description);
        project.setRreadmelink(readmeLink);
        project.setRecruting(recruting);
        project.setContactandlinks(contactsAndLinks);
        project.setPicturelink(pictureLink);

        projectDatabaseControler.modifyProject(project);
        
        return ("/userProtected/UsersProjects");
    }
    
}
