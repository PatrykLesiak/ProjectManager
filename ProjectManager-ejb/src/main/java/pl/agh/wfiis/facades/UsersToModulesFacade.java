package pl.agh.wfiis.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.agh.wfiis.database.UsersToModules;

@Stateless
public class UsersToModulesFacade extends AbstractFacade<UsersToModules> {
    @PersistenceContext(unitName = "pl.agh.wfiis_ProjectManager-ejb_ejb_devPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersToModulesFacade() {
        super(UsersToModules.class);
    }
    
}
