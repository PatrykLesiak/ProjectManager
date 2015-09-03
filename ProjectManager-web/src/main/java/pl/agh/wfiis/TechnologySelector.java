package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import pl.agh.wfiis.database.Technology;
import pl.agh.wfiis.model.UserDatabaseController;

/**
 *
 */
@Named(value = "technologySelector")
@RequestScoped
public class TechnologySelector implements Serializable {
    /**
     * Logger object for log operations which facilitates development process.
     */
    Logger logger = Logger.getLogger(getClass().getName());
    
    @EJB
    private UserDatabaseController userDatabaseController;
    
    @Inject
    private TotalUserAttributes totalUserAttributes;
    

    private Integer selectedTechnology;
    private Map<String, Integer> availableTechnologies;
	
    public Map<String, Integer> getAvailableTechnologies() {
    	return availableTechnologies;
    }
    
    public Integer getSelectedTechnology() {
        return selectedTechnology;
    }

    public void setSelectedTechnology(Integer selectedTechnology) {
        this.selectedTechnology = selectedTechnology;
        userDatabaseController.assignTechnologyToUser(totalUserAttributes.getLoggedUserId(), selectedTechnology);
        init();
    }
    
    @PostConstruct
    private void init() {
	availableTechnologies = new LinkedHashMap<>();
        for(Technology technology : userDatabaseController.getAllTechnologies()) {
            if (!userDatabaseController.getUserTechnologies(totalUserAttributes.getLoggedUserId()).contains(technology)) {
                availableTechnologies.put(technology.getName(), technology.getTechnologyid());
            }
        }
    }


}
