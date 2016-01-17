package com.vinod.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class OnExceptionTest {
	public static void main(String... args) throws Exception {
		Main main = new Main();
		main.enableHangupSupport();
		main.addRouteBuilder(new TestExceptionRoute());
		main.run(args);
	}
}

class TestExceptionRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		onException(RuntimeException.class).to("file:exceptionDirectory");
		from("file:source").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				throw new RuntimeException();
			}
		}).to("file:destination");
	}

}
