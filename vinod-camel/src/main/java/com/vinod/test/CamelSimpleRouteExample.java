package com.vinod.test;

import java.io.File;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelSimpleRouteExample {

	public static void main(String[] args) {
		try {
			CamelContext context = new DefaultCamelContext();
			context.addRoutes(new RouteBuilder() {
				public void configure() {
					from("file://source").to("file://destination");
				}
			});
			context.start();
			File ff = new File("source//Temp.txt");
			ff.createNewFile();
			Thread.sleep(1000);
			context.stop();
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
