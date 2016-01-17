package com.vinod.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class CamelTransformation {
	public static void main(String... args) throws Exception {
		Main main = new Main();
		main.enableHangupSupport();
		main.addRouteBuilder(new MyTransformRoute());
		main.run(args);
	}
}

class MyTransformRoute extends RouteBuilder {
	public void configure() {
		System.out.println("My Routing Started");
		from("file:source?noop=true").process(new MyTransformProcessor()).to("file:destination?fileName=output.csv");

		System.out.println("My Routing complete");

	}
}

class MyTransformProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		System.out.println("MyProcessor started");
		String myString = exchange.getIn().getBody(String.class);
		String[] myArray = myString.split(System.getProperty("line.separator"));
		StringBuffer sb = new StringBuffer();
		for (String s : myArray) {
			sb.append(s).append(",");
		}
		System.out.println("MyProcessor complete");
		exchange.getIn().setBody(sb.toString());
	}
}