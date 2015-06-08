package pl.agh.wfiis.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.agh.wfiis.database.TechnologiesToModules;

@Stateless
public class TechnologiesToModulesFacade extends AbstractFacade<TechnologiesToModules> {
    @PersistenceContext(unitName = "pl.agh.wfiis_ProjectManager-ejb_ejb_devPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TechnologiesToModulesFacade() {
        super(TechnologiesToModules.class);
    }
    
}
