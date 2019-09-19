import java.text.Normalizer.Form;
import java.util.HashMap;

//kamel run -d camel-kafka -d camel-kafka -d camel-mqtt ReceiverMQTT.java --dev

import org.apache.camel.builder.RouteBuilder;

public class ReceiverMQTT extends RouteBuilder {

  private static final String STREAMS_URL = "my-cluster-kafka-bootstrap.usecase.svc:9092";
  private static final String BROKER_URL = "tcp://broker-amq-mqtt.usecase.svc:1883";
  @Override
  public void configure() throws Exception {
    
    from("mqtt:demo?subscribeTopicName=mytopic&userName=amq&password=password&host="+BROKER_URL)
    .log("From MQTT --- ${body}")
    .to("kafka:my-topic?brokers="+STREAMS_URL);
  }

  
}
