# notification-sending-system

<!-- TOC -->

- [notification-sending-system](#notification-sending-system)
  - [README](#readme)
  - [Initial client requirements and task](#initial-client-requirements-and-task)
  - [Initial breakdown and analysis](#initial-breakdown-and-analysis)
  - [Additional requirements clarifications](#additional-requirements-clarifications)
  - [Description](#description)
  - [System components and more technical details and functionalities](#system-components-and-more-technical-details-and-functionalities)
  - [Challenges](#challenges)
  - [Install, Setup, Running and Deployment instructions](#install-setup-running-and-deployment-instructions)
  - [Demo](#demo)
  - [Further development](#further-development)
  - [References](#references)

<!-- /TOC -->
<!-- /TOC -->

---

## README

## Initial client requirements and task

- Please see: [Initial client requirements task](Tech%20Assignment_Verification_SSE.pdf)

## Initial breakdown and analysis
  
- This is based on [Initial client requirements and task](#initial-client-requirements-and-task), please see:

  - [Initial breakdown and analysis](ANALYSIS_V1.0.md)

## Additional requirements clarifications

- Please see: [Additional requirements clarifications](ADDITIONAL_REQ_CLARIFICATION.md)

## Description

This is an extensible and scalable minimum viable product (MVP) of a message sending system .

1. `Requirement 1.` -  The system needs to be able to send notifications via several different channels (email,
sms, slack) and be easily extensible to support more channels in the future.

The system is able to send notifications via several different channels (email, sms, slack) and is easily extensible to support more channels in the future.

For the purposes of the MVP only the `email` channel is implemented.

The system contains a `Channel` interface which allows extensibility and support of more channels in the future. 
The `Channel` interface is implemented by the `EmailChannel`, `SlackChannel`, `SMSChannel` and can be implemented by more channels in the future as required.

Please see class diagram for more information:

![Class Diagram 1](ClassDiag1.png)

2. `Requirement 2.` The system needs to be horizontally scalable.

The system is designed to be horizontally scalable by using technologies such as `Docker`, `Docker Compose` and `Apache Kafka`.

This design allows when the system is deployed to production to be deployed to `Kubernetes` and for each component to be scaled according to load.

3. `Requirement 3.` The system must guarantee an *"at least once"* SLA for sending the message.

The system is designed to guarantee `at least once` SLA for sending messages by utilizing `Apache Kafka's` Producer functionality.
The configuraiton which allows us to achieve this is setting the `acks: 1` and `retries: 3` parameters.
Here is an excerpt from the full configuration file:

```yml
    producer:
      client-id: example
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
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
      acks: 1
      retries: 3
```

Please see the full configuration file for more details:

- [kafka.yml](src/main/resources/kafka.yml)

4. `Requirement 4.` The interface for accepting notifications to be sent can be chosen on your own discretion.

For the purpose of the MVP the chosen interface is REST endpoints.

- `/notify/{channelType}` endpoint
  
  - Used in this format for example, where `channelType` is replaced with `email: <http://localhost:8081/notification-service/notify/email>
  - Example request:
  
      ```sh
      curl --location --request POST 'http://localhost:8081/notification-service/notify/email' \
      --header 'Content-Type: application/json' \
      --data-raw '{  
      "message": "Body of message 1."
      }'
      ```

- `/notifyAll` endpoint
  
  - Currently not implemented as per [Additional requirements clarifications](#additional-requirements-clarifications)
  - As a good practice it currently returns a REST response:

      ```sh
      501 Not Implemented

      Notify method is not implemented yet.
      ```

---

## System components and more technical details and functionalities

---

## Challenges

---

## Install, Setup, Running and Deployment instructions

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

For Maven help, please see:

- [Maven help](HELP.md)

---

## Demo

---

## Further development

---

## References

<https://dzone.com/articles/kafka-producer-delivery-semantics>

<http://kafka.apache.org/090/documentation.html#producerconfigs>

---
