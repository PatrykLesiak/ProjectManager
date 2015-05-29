package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Patryk
 */
@Named(value = "newJSFManagedBean")
@SessionScoped
public class NewJSFManagedBean implements Serializable {
    @EJB
    private pl.agh.wfiis.model.TestBean newSessionBean;
    private String lol;
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public NewJSFManagedBean() {
        lol = "Test z lolem sie udal";
    }
    
    public String test() {
        return lol;
    }
    
    public String test2() {
        return newSessionBean.test();
    }
                
}
