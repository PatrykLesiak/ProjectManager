package pl.agh.wfiis.model;

import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import pl.agh.wfiis.database.Technology;
import pl.agh.wfiis.facades.TechnologyFacade;

@Stateless
@LocalBean
public class TestBean {

    @EJB
    private TechnologyFacade technologyFacade;

    public String test() {
        String out = "";
        List news = technologyFacade.findAll();
        for (Iterator it = news.iterator(); it.hasNext();) {
            Technology elem = (Technology) it.next();
            out += elem.getName()+ ", ";
        }
        return out;
    }
}
