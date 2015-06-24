/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.agh.wfiis.model;

import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import pl.agh.wfiis.database.Module;
import pl.agh.wfiis.facades.ModuleFacade;

@Stateless
@LocalBean
public class ModuleDatabaseController {
    @EJB
    private ModuleFacade moduleFacade;
    Logger logger = Logger.getLogger(getClass().getName());
    
    public Module getModule(int moduleId){
        return moduleFacade.find(moduleId);
    }
    
}
