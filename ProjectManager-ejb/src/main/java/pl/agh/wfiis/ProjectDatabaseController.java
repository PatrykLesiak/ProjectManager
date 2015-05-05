package pl.agh.wfiis;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="projectDatabaseController")
@SessionScoped
public class ProjectDatabaseController implements DatabaseController {
    private String test = "Wartosc z beanu";

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
    
    
}
