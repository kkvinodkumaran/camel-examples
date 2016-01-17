package com.vinod.test;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class CamelDatatransformation {
	public static void main(String... args) throws Exception {
		Main main = new Main();
		main.enableHangupSupport();
		main.addRouteBuilder(new MyRoute());
		main.run(args);
	}
}

class MyRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("file:source").transform(new Expression() {
			public <T> T evaluate(Exchange exchange, Class<T> type) {
				String body = exchange.getIn().getBody(String.class);
				body = body.replaceAll("\n", "<br/>");
				body = "<html><body>" + body + "</body></html>";
				return (T) body;
			}
		}).to("file:destination");
	}
}