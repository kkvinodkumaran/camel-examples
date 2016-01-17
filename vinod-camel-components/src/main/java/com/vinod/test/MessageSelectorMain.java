package com.vinod.test;

import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageSelectorMain {

	public static void main(String[] args) throws Exception {

		// Sending Message to the order Queue
		ApplicationContext context = new ClassPathXmlApplicationContext("camel-application-context-msgselector.xml");
		ProducerTemplate camelTemplate = context.getBean("camelTemplate", ProducerTemplate.class);
		System.out.println("Order Message Sending started");
		camelTemplate.sendBodyAndHeader("jms:queue:ORDER", "Online Order Details", "METHOD", "ONLINE");
		camelTemplate.sendBodyAndHeader("jms:queue:ORDER", "Store Order Details", "METHOD", "STORE");
		camelTemplate.sendBodyAndHeader("jms:queue:ORDER", "Online Order Details", "METHOD", "ONLINE");
		System.out.println("Order Message sending completed");

	}

}
