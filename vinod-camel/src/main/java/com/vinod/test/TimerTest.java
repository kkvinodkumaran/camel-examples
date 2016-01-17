package com.vinod.test;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
public class TimerTest {
	public static void main(String... args) throws Exception {
		Main main = new Main();
		main.enableHangupSupport();
		main.addRouteBuilder(new TimerRoute());
		main.run(args);
	}
}

class TimerRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("timer://foo?period=5000").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				System.out.println("Hello world  :"
						+ System.currentTimeMillis());
			}
		});
	}
}