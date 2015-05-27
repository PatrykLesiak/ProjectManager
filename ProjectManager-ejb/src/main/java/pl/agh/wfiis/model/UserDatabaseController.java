package pl.agh.wfiis.model;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.facades.UserFacade;

@Stateful
@LocalBean
public class UserDatabaseController {

    @EJB
    private UserFacade userFacade;
    
    public void registerUserInDatabase(User user) {
        userFacade.create(user);
    }
}
