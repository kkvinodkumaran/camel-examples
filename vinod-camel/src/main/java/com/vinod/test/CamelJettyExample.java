package com.vinod.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class CamelJettyExample {
	public static void main(String... args) throws Exception {
		Main main = new Main();
		main.enableHangupSupport();
		main.addRouteBuilder(new MyCamelJettyBuilder());
		main.run(args);
	}
}

class MyCamelJettyBuilder extends RouteBuilder {
	public void configure() {
		from("jetty:http://localhost:8181/mytestservice").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				String message = exchange.getIn().getBody(String.class);
				System.out.println("Hello Mr :" + message);
				exchange.getOut().setBody("Hello world Mr " + message);
			}
		});
	}
}