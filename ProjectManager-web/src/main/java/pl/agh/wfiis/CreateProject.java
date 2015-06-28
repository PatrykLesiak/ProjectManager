package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.model.ProjectDatabaseController;
import pl.agh.wfiis.model.UserDatabaseController;

/**
 * Managed bean for view used for registering new project in the database.
 */
@Named(value = "createProject")
@SessionScoped
public class CreateProject implements Serializable {
    
    /**
     * Database controller object used for project related operations.
     */
    @EJB
    private ProjectDatabaseController projectDatabaseControler;
    
    
    /**
     * Database controller object used for user related operations.
     */
    @EJB
    private UserDatabaseController userDatabaseController;
    
    /**
    * Logger object for log operations which facilitates development process.
    */
    Logger logger = Logger.getLogger(getClass().getName());
    
    /**
     * Project title.
     */
    private String title;
    
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
     * No idea.
     */
    private Project projectById;
    
    /**
     * Project informations about contacts and related links.
     */
    private String contactsAndLinks;
    
    /**
     * Project picture link.
     */
    private String pictureLink;
    
    /**
     * The random project list from the database.
     */
    private List<CreateProject> randomProjectList;
    
    /**
     * Function for getting the value of private field of the class.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Function for manimulating private field of the class.
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Function for getting the value of private field of the class.
     * @return the descriptionl
     */
    public String getDescription() {
        return description;
    }

    /**
     * Function for manimulating private field of the class.
     * @param descriptionl the descriptionl to set
     */
    public void setDescription(String descriptionl) {
        this.description = descriptionl;
    }

    /**
     * Function for getting the value of private field of the class.
     * @return The project readme link.
     */
    public String getReadmeLink() {
        return readmeLink;
    }

    /**
     * Function for manimulating private field of the class.
     * @param readmeLink The project readmeLink to set.
     */
    public void setReadmeLink(String readmeLink) {
        this.readmeLink = readmeLink;
    }

    /**
     * Function for getting the value of private field of the class.
     * @return The project contacts and links.
     */
    public String getContactsAndLinks() {
        return contactsAndLinks;
    }

    /**
     * Function for manimulating private field of the class.
     * @param contactsAndLinks The project contacts and links to set.
     */
    public void setContactsAndLinks(String contactsAndLinks) {
        this.contactsAndLinks = contactsAndLinks;
    }

    /**
     * Function for getting the value of private field of the class.
     * @return The project picture link.
     */
    public String getPictureLink() {
        return pictureLink;
    }

    /**
     * Function for manimulating private field of the class.
     * @param pictureLink The project pictureLink to set.
     */
    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
    
    /**
     * Function for getting the value of private field of the class.
     * @return the recruting
     */
    public Boolean getRecruting() {
        return recruting;
    }

    /**
     * Function for manimulating private field of the class.
     * @param recruting the recruting to set
     */
    public void setRecruting(Boolean recruting) {
        this.recruting = recruting;
    }
    
    /**
     * Function for getting the value of private field of the class.
     * @param id    Projects ID.
     * @return      Founded project object.
     */
    public Project getProjectById(int id){
        return projectDatabaseControler.getProjectByID(id);
    }

    /**
     * Function for manimulating private field of the class.
     * @param id Projects ID.
     */
    public void setProjectById(Project id){
        this.projectById = id;
    }
    
    /**
     *  Function retrieves ID of logged user.
     * 
     * @return ID of logged user.
     */
    public int getUserIdFromSession(){
        User user = null;
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        
        if (principal != null) {
            user = userDatabaseController.findUserByEmail(principal.getName());
        }
        
        if (user != null) {
            return user.getUserid();
        }
        else {
            logger.severe("There is no user despite that he is logged in");
            return 0;
        }
    }
    
    /**
     * Create new project object based on informations from the form and pass to database controller.
     * 
     * @return Navigation destiny returned after form processing.
     */
    public String createNewProject(){
        Project newProject = new Project();
        newProject.setTitle(this.title);
        newProject.setDescription(this.description);
        newProject.setRreadmelink(this.readmeLink);
        newProject.setContactandlinks(this.contactsAndLinks);
        newProject.setPicturelink(this.pictureLink);
        newProject.setRecruting(Boolean.FALSE);
        
        int leaderId = getUserIdFromSession();
        
        projectDatabaseControler.createNewProjectInDatabase(newProject, leaderId);
        
        return "/index";
    }
}
