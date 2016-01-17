package com.vinod.test;

import org.apache.camel.builder.RouteBuilder;

public class CamelMongoRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("jetty:http://localhost:8181/mongoSelect")
				.to("mongodb:myDb?database=customerdb&collection=customer&operation=findAll");
		from("jetty:http://localhost:8181/mongoInsert")
				.to("mongodb:myDb?database=customerdb&collection=customer&operation=insert");
	}
}