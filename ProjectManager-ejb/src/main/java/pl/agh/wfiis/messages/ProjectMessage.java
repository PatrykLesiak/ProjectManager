package pl.agh.wfiis.messages;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/ProjectMessage", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ProjectMessage implements MessageListener {
    
    public ProjectMessage() {
    }
    
    @Override
    public void onMessage(Message message) {
    }
    
}
