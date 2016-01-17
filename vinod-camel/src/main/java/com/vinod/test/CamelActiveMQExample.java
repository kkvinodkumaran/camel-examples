package com.vinod.test;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelActiveMQExample {
	public static void main(String[] args) {
		try {
			CamelContext context = new DefaultCamelContext();
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin",
					ActiveMQConnection.DEFAULT_BROKER_URL);
			context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
			context.addRoutes(new RouteBuilder() {
				public void configure() {
					from("test-jms:queue:testMQ").log("${body}").to("test-jms:queue:testMQDestination");
				}
			});
			context.start();
			Thread.sleep(1000);
			context.stop();
			System.out.println("Done");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
