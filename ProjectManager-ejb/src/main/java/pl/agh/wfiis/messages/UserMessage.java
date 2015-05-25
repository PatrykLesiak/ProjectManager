package pl.agh.wfiis.messages;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/UserMessage", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class UserMessage implements MessageListener {
    
    public UserMessage() {
    }
    
    @Override
    public void onMessage(Message message) {
    }
    
}
