package com.vinod.test;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
public class SpringJMSProducer {

	public static void main(String[] args) {
		  ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		  JmsTemplate jmsTemplate=(JmsTemplate) context.getBean("jmsTemplate");
		  jmsTemplate.send(
      		new MessageCreator() {
      	          public ObjectMessage  createMessage(Session session) throws JMSException {
      	        	  ObjectMessage message = session.createObjectMessage();
      	        	  message.setObject("My first Message");       	              
      	               return message;
      	          }
      }  );
		  		  System.out.println("MESSAGE SENT TO myMessageQueue");
	}

}
