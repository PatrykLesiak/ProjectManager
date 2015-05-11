/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.agh.wfiis;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class NewSessionBean {

    private String lol2;
    
    public NewSessionBean() {
        lol2 = "test z lolem2 sie udal";
    }

    
    public String test() {
        return lol2;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
