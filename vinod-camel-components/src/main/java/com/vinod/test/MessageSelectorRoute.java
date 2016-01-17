package com.vinod.test;

import org.apache.camel.builder.RouteBuilder;

public class MessageSelectorRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("jms:ORDER?selector=METHOD='ONLINE'").to("jms:queue:ORDERONLINEPROCESSOR");
		from("jms:ORDER?selector=METHOD='STORE'").to("jms:queue:ORDERSTOREPROCESSOR");
	}
}
