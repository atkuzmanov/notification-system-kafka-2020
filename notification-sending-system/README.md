# notification-sending-system

---

This is an extensible and scalable minimum viable product (MVP) of a message sending system .

The system is able to send notifications via several different channels (email, sms, slack) and is easily extensible to support more channels in the future.

For the purposes of the MVP only the `email` channel is implemented.




---


For Docker container, to be packaged well:
```shell
mvn clean package install spring-boot:repackage -DskipTests
```

```shell
docker-compose up --build --remove-orphans
```

For remote debugging in Dockerfile:

```shell
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -Djava.security.egd=file:/dev/./urandom
```

---

```shell
      # The following properties `acks: 1` and `retries: 3` allow us to achieve the desired guarantee of the system,
      # that an "at least once" SLA for sending the message, is met.
      # Please see below for more details:
      # "When this property is set to 1 you can achieve at least once delivery semantics.
      # A Kafka producer sends the record to the broker and waits for a response from the broker.
      # If no acknowledgment is received for the message sent, then the producer will retry sending the messages
      # based on a retry configuration. The retries property, by default, is set to 0;
      # make sure this is set to the desired number or Max.INT."
      # References:
      # https://dzone.com/articles/kafka-producer-delivery-semantics
      # http://kafka.apache.org/090/documentation.html#producerconfigs
```
---
