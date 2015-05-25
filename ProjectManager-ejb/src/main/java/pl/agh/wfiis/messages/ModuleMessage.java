package pl.agh.wfiis.messages;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/ModuleMessage", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ModuleMessage implements MessageListener {
    
    public ModuleMessage() {
    }
    
    @Override
    public void onMessage(Message message) {
    }
    
}
