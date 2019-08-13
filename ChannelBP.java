import org.apache.camel.builder.RouteBuilder;

//kamel run ChannelBP.java --dev

public class ChannelBP extends RouteBuilder {

  private static final String STREAMS_URL = "my-cluster-kafka-bootstrap.streams.svc:9092";
  
  
  @Override
  public void configure() throws Exception {
    from("knative:channel/bloodpressure")
    .log("--> BP ${body}");
    
  

  }

  
}
