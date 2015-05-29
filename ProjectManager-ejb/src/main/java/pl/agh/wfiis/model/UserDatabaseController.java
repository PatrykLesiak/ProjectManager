package pl.agh.wfiis.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytesPassword = digest.digest(user.getPassword().getBytes("UTF-8"));
            StringBuffer result = new StringBuffer();
            for (byte byt : bytesPassword) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
            user.setPassword(result.toString());
            userFacade.create(user);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    
}
