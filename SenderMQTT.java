import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

//kamel run -d camel-jackson -d camel-mqtt SenderMQTT.java --dev

public class SenderMQTT extends RouteBuilder {

  
  @Override
  public void configure() throws Exception {

    JacksonDataFormat jacksonDataFormat = new JacksonDataFormat();
    jacksonDataFormat.setUnmarshalType(SingalInput.class);
    

    from("timer:tick?fixedRate=true&period=2000")
      .setBody(method(this, "genRandoSingalInput()"))
      .marshal(jacksonDataFormat)
      .to("mqtt:singal?host=tcp://broker-amq-mqtt.usecase.svc:1883&userName=amq&password=password&publishTopicName=mytopic");
  }

  public static SingalInput genRandoSingalInput(){

      SingalInput input = new SingalInput();
      int ranvalue = (int)(Math.random()*9)%3;

      input.setValue((int)(Math.random()*600));
       if(ranvalue == 0)
        input .setType("HR");
       if (ranvalue == 1)
        input.setType("BT");
      else
        input.setType("BP");

      return input;
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
