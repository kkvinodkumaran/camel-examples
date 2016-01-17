package com.vinod.test;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
public class ErrorHandlerTest {
	public static void main(String... args) throws Exception {
		Main main = new Main();
		main.enableHangupSupport();
		main.addRouteBuilder(new TestRoute());
		main.run(args);
	}
}

class TestRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		errorHandler(deadLetterChannel("file:deadletter"));
		from("file:source").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				throw new RuntimeException();
			}
		}).to("file:destination");
	}
}