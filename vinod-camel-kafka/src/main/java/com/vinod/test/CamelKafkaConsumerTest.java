
package com.vinod.test;

import org.apache.camel.Exchange;
import org.apache.camel.Main;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;

public class CamelKafkaConsumerTest {

    public static void main(String... args) throws Exception {
	Main main = new Main();
	main.enableHangupSupport();
	main.addRouteBuilder(new MyCamelJettyBuilder());
	main.run(args);
    }
}

class MyCamelJettyBuilder extends RouteBuilder {
    String topicName = "topic=test";
    String kafkaServer = "kafka:localhost:9092";
    String zooKeeperHost = "zookeeperHost=localhost&zookeeperPort=2181";
    String serializerClass = "serializerClass=kafka.serializer.StringEncoder";
    String autoOffsetOption = "autoOffsetReset=smallest";
    String groupId = "groupId=testing";

    String toKafka = new StringBuilder().append(kafkaServer).append("?").append(
	    topicName).append("&").append(zooKeeperHost).append("&").append(
		    serializerClass).toString();

    String fromKafka = new StringBuilder().append(toKafka).append("&").append(
	    autoOffsetOption).append("&").append(groupId).toString();

    public void configure() {
	from("jetty:http://localhost:8182/mytestservice").process(
		new Processor() {
		    public void process(Exchange exchange) throws Exception {
			String message = exchange.getIn().getBody(String.class);
			exchange.getIn().setBody(message, String.class);
			exchange.getIn().setHeader(KafkaConstants.PARTITION_KEY,
				0);
			exchange.getIn().setHeader(KafkaConstants.KEY, "1");
		    }
		}).to(toKafka);
	from(fromKafka).process(new Processor() {
	    public void process(Exchange exchange) throws Exception {
		if (exchange.getIn() != null) {
		    Message message = exchange.getIn();
		    String data = message.getBody(String.class);
		    System.out.println("Data  =" + data.toString());
		}
	    } 
	});

    }
}
