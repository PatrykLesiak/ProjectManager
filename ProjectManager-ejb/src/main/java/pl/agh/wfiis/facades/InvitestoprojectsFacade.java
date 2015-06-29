package pl.agh.wfiis.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.agh.wfiis.database.Invitestoprojects;

/**
 *
 * @author Patryk
 */
@Stateless
public class InvitestoprojectsFacade extends AbstractFacade<Invitestoprojects> {
    @PersistenceContext(unitName = "pl.agh.wfiis_ProjectManager-ejb_ejb_devPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InvitestoprojectsFacade() {
        super(Invitestoprojects.class);
    }
    
}
