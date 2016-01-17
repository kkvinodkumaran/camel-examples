package com.vinod.test;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class CamelTransformExample {
	public static void main(String... args) throws Exception {
		Main main = new Main();
		main.enableHangupSupport();
		main.addRouteBuilder(new MyTransRoute());
		main.run(args);
	}
}

class MyTransRoute extends RouteBuilder {
	public void configure() {
		System.out.println("My Routing Started");
		from("file:source?noop=true").transform(body().regexReplaceAll(System.getProperty("line.separator"), ","))
				.to("file:destination?fileName=output.csv");
		System.out.println("My Routing complete");
	}
}
