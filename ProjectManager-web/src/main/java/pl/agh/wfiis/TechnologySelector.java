package pl.agh.wfiis;

import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import pl.agh.wfiis.database.Technology;
import pl.agh.wfiis.model.ProjectDatabaseController;
import pl.agh.wfiis.model.UserDatabaseController;

/**
 * Managed bean for technology selector handling.
 */
@Named(value = "technologySelector")
@SessionScoped
public class TechnologySelector implements Serializable {
 Logger logger = Logger.getLogger(getClass().getName());
    /**
     * Database controller used for user related operations.
     */
    @EJB
    private UserDatabaseController userDatabaseController;
    
    /**
     * Database controller used for project related operations.
     */
    @EJB
    private ProjectDatabaseController projectDatabaseController;    
    
    /**
     * Injected TotalUserAttributes managed bean.
     */
    @Inject
    private TotalUserAttributes totalUserAttributes;
    
    /**
     * Injected TotalUserAttributes managed bean.
     */
    @Inject
    private TotalProjectAttributes totalProjectAttributes;
    /**
     * Value of selested technology item for user interaction.
     */
    private Integer selectedTechnologyForUser;
    
    /**
     * Value of selested technology item for module interaction.
     */
    private Integer selectedTechnologyForModule;

    public Integer getSelectedTechnologyForModule() {
        return selectedTechnologyForModule;
    }

    /**
     * Redirect create request to database controller.
     * 
     * @param selectedTechnologyForModule Module id.
     */
    public void setSelectedTechnologyForModule(Integer selectedTechnologyForModule) {
        Map<String, String> urlParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.selectedTechnologyForModule = selectedTechnologyForModule;
        projectDatabaseController.assignTechnologyToUser(Integer.parseInt(urlParameters.get("id")), selectedTechnologyForModule);
    }
    
    /**
     * Values of selector.
     */
    private Map<String, Integer> availableTechnologies;
	
    public Map<String, Integer> getAvailableTechnologies() {
    	return availableTechnologies;
    }
    
    public Integer getSelectedTechnologyForUser() {
        return selectedTechnologyForUser;
    }
    
    private int moduleId;
    
    public void setSelectedTechnologyForUser(Integer selectedTechnology) {
        this.selectedTechnologyForUser = selectedTechnology;
        userDatabaseController.assignTechnologyToUser(totalUserAttributes.getLoggedUserId(), selectedTechnology);
        init();
    }
    
    /**
     * Initialize values of selector.
     */
    @PostConstruct
    public void init() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map<String, String> urlParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (urlParameters.get("id") == null && ctx.getViewRoot().getViewId().contains("modify_module")) {
            logger.info("Nic z tego");
            return;
        }
        
        availableTechnologies = new LinkedHashMap<>();
        
        if (ctx.getViewRoot().getViewId().contains("profile_settings")) {
            logger.info("OK");
            for(Technology technology : userDatabaseController.getAllTechnologies()) {
                if (!userDatabaseController.getUserTechnologies(totalUserAttributes.getLoggedUserId()).contains(technology)) {
                    availableTechnologies.put(technology.getName(), technology.getTechnologyid());
                }
            }
        }
        else if (ctx.getViewRoot().getViewId().contains("modify_module")) {
            
            logger.info("OK2");
            logger.info(urlParameters.get("id"));
            for(Technology technology : userDatabaseController.getAllTechnologies()) {  
                if (!projectDatabaseController.getAllModuleTechnologies(Integer.parseInt(urlParameters.get("id"))).contains(technology)) {
                    availableTechnologies.put(technology.getName(), technology.getTechnologyid());
                }
            }            
        }
    }
    
    /**
     * Helper collection of technologies.
     */
    private List<Technology> previousResult;
    
    /**
     * Retrieves module technologies from the database through database controller.
     * 
     * @return Collection of technologies.
     * @throws IOException 
     */
    public List<Technology> getModuleTechnologies() throws IOException {
        Map<String, String> urlParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        if (urlParameters.get("id") != null) {
            previousResult = totalProjectAttributes.getModuleTechnologies(Integer.parseInt(urlParameters.get("id")));
        }

        return previousResult;
    }
}
