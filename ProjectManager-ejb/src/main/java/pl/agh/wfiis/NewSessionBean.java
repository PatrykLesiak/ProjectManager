/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.agh.wfiis;

import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class NewSessionBean {

    @EJB
    private UserFacade userFacade;
    
    private String lol2;
    
    public NewSessionBean() {
        lol2 = "test z lolem2 sie udal";
    }
    
    public String test() {
        User newUser = new User();
        newUser.setUserid(3);
        newUser.setFirstname("Andrzej");
        userFacade.create(newUser);
        
        String out = "";
        List users = userFacade.findAll();
        for (Iterator it = users.iterator(); it.hasNext();) {
            User user = (User)it.next();
            out += user.getFirstname() + ", ";
        }
        return out;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
