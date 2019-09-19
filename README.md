# Simple Camel K demo

Make sure you have installed KNative on OpenShift 

1. Deploy Camel K operator
2. Setup Blood Pressure
```
oc create -f bp-channel.yaml

```
3. Setup Heart Rate Channel  

```
oc create -f hr-channel.yaml

```
4. Create AMQ Broker for MQTT 

```
oc new-app --template=amq63-basic --param=MQ_USERNAME=amq --param=MQ_PASSWORD=password --param=MQ_PROTOCOL=mqtt,amqp,openwire

```
5. Setup Strimzi with Topic name my-topic

6. Run the integration that moves Kafka streams to Channel 
```
kamel run -d camel-jackson -d camel-kafka SenderChannels.java 

```
7. Run the integration that moves MQTT streams to Kafka
```
kamel run -d camel-kafka -d camel-kafka -d camel-mqtt ReceiverMQTT.java
```
8. Run the listener to Blood Pressure Signal
```
kamel run ChannelBP.java
```
9. Run the listener to Heart Rate Signal
```
kamel run ChannelHR.java
```
10.Run the simulator that sends MQTT signal
```
kamel run -d camel-jackson -d camel-mqtt SenderMQTT.java 
```
