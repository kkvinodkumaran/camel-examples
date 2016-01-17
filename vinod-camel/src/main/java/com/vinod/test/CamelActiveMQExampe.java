package com.vinod.test;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.main.Main;

public class CamelActiveMQExampe {
	public static void main(String... args) throws Exception {

		Main main = new Main();
		main.addRouteBuilder(new ActiveMQRoute());
		main.run(args);
	}
}

class ActiveMQRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin",
				ActiveMQConnection.DEFAULT_BROKER_URL);
		getContext().addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		from("test-jms:testQ1").to("test-jms:testQ2");

	}
}