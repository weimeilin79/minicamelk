import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

//kamel run -d camel-jackson -d camel-kafka SenderChannels.java --dev

public class SenderChannels extends RouteBuilder {

  private static final String STREAMS_URL = "my-cluster-kafka-bootstrap.streams.svc:9092";
  
  
  @Override
  public void configure() throws Exception {

    JacksonDataFormat jacksonDataFormat = new JacksonDataFormat();
    jacksonDataFormat.setUnmarshalType(SingalInput.class);
    
    from("kafka:my-topic?brokers="+STREAMS_URL)
    .unmarshal(jacksonDataFormat)
    .choice()
      .when().simple("${body.type} == 'BP'")
          .marshal(jacksonDataFormat)
          .log("BP - ${body}")
          .to("knative:channel/bloodpressure")
      .otherwise()
      .marshal(jacksonDataFormat)
          .log("HR - ${body}")
          .to("knative:channel/heartrate")
          ;

  }

  

  public static class SingalInput {

    String type;
    int value;

    public String getType(){
      return type;
    }
    public int getValue(){
      return value;
    }

    public void setType(String type){
      this.type = type;
    }

    public void setValue(int value){
      this.value = value;
    }
  }
}
