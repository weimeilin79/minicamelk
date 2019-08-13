import org.apache.camel.builder.RouteBuilder;

//kamel run ChannelHR.java --dev

public class ChannelHR extends RouteBuilder {

  private static final String STREAMS_URL = "my-cluster-kafka-bootstrap.streams.svc:9092";
  
  
  @Override
  public void configure() throws Exception {

    from("knative:channel/heartrate")
    .log("--> HR ${body}");
    
    
  }

  
}
