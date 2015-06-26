package pl.agh.wfiis;


import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.model.ProjectDatabaseController;

@Named(value = "createProject")
@SessionScoped
public class CreateProject implements Serializable {
   
    @EJB
    private ProjectDatabaseController projectDatabaseControler;
    
    Logger logger = Logger.getLogger(getClass().getName());
    
    private String title;
    private String description;
    private String readmeLink;
    private Boolean recruting;
    private Project projectById;
    private String contactsAndLinks;
    private String pictureLink;
    private List<CreateProject> randomProjectList;
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the descriptionl
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param descriptionl the descriptionl to set
     */
    public void setDescription(String descriptionl) {
        this.description = descriptionl;
    }

    /**
     * @return the readmeLink
     */
    public String getReadmeLink() {
        return readmeLink;
    }

    /**
     * @param readmeLink the readmeLink to set
     */
    public void setReadmeLink(String readmeLink) {
        this.readmeLink = readmeLink;
    }

    /**
     * @return the contactsAndLinks
     */
    public String getContactsAndLinks() {
        return contactsAndLinks;
    }

    /**
     * @param contactsAndLinks the contactsAndLinks to set
     */
    public void setContactsAndLinks(String contactsAndLinks) {
        this.contactsAndLinks = contactsAndLinks;
    }

    /**
     * @return the pictureLink
     */
    public String getPictureLink() {
        return pictureLink;
    }

    /**
     * @param pictureLink the pictureLink to set
     */
    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
    
    public int getUserIdFromSession(){
        return 1; //@TODO fix this authentication
    }
    
    public String createNewProject(){
        Project newProject = new Project();
        newProject.setTitle(this.title);
        newProject.setDescription(this.description);
        newProject.setRreadmelink(this.readmeLink);
        newProject.setContactandlinks(this.contactsAndLinks);
        newProject.setPicturelink(this.pictureLink);
        newProject.setRecruting(Boolean.TRUE);
        
        int leaderId = getUserIdFromSession();
        
        projectDatabaseControler.createNewProjectInDatabase(newProject, leaderId);
        
        return "/index";
    }
 
    
 

    /**
     * @return the recruting
     */
    public Boolean getRecruting() {
        return recruting;
    }

    /**
     * @param recruting the recruting to set
     */
    public void setRecruting(Boolean recruting) {
        this.recruting = recruting;
    }
    
    public Project getProjectById(int id){
        return projectDatabaseControler.getProjectByID(id);
    }
    
    public void setProjectById(Project id){
        this.projectById = id;
    }
}
