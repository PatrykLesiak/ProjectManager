/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.agh.wfiis;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Patryk
 */
@MessageDriven(mappedName = "jms/UserMessage", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class UserMessage implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;
    @PersistenceContext(unitName="pl.agh.wfiis_ProjectManager-ejb_ejb_devPU")
    private EntityManager em;

    public UserMessage() {
    }

    @Override
    public void onMessage(Message message) {
             ObjectMessage msg = null;
     try {
          if (message instanceof ObjectMessage) {
          msg = (ObjectMessage) message;
              User e = (User) msg.getObject();
              save(e);
          }
     } catch (JMSException e) {
          e.printStackTrace();
          mdc.setRollbackOnly();
     } catch (Throwable te) {
          te.printStackTrace();
     }
    }
    
    private void save(Object object) {
    em.persist(object);
}

}
