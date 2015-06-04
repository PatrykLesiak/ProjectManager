/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.agh.wfiis;


import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.model.ProjectDatabaseController;
import javax.persistence.Persistence;

@Named(value = "createProject")
@SessionScoped
public class CreateProject implements Serializable {

     Logger logger = Logger.getLogger(getClass().getName());
    
    @EJB
    private ProjectDatabaseController projectDatabaseControler; 
    private String title;
    private String description;
    private String readmeLink;
    private String contactsAndLinks;
    private String pictureLink;

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
    
    public int authenticateUser(){
        return 1; //@TODO fix this authentication
    }
    
    public String createNewProject(){
        Project newProject = new Project();
        newProject.setTitle(this.title);
        newProject.setDescription(this.description);
        newProject.setRreadmelink(this.readmeLink);
        newProject.setContactandlinks(this.contactsAndLinks);
        newProject.setPicturelink(this.pictureLink);
        int userId = authenticateUser();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAService");
        EntityManager em = emf.createEntityManager();
        User user = (User) em.createNamedQuery("User.findByUserid").setParameter("id", userId);
        newProject.setLeaderid(user);
        newProject.setRecruting(Boolean.TRUE);
        
        projectDatabaseControler.createNewProject(newProject);
        logger.info("New project should be registrated in the database");
        
        return "/index";
    }
}
