# notification service notes

## Task:

Create a notification sending system.

## Requirements:

1. The system needs to be able to send notifications via several different channels (email,
sms, slack) and be easily extensible to support more channels in the future.

2. The system needs to be horizontally scalable.

3. The system must guarantee an "at least once" SLA for sending the message.
The interface for accepting notifications to be sent can be chosen on your own discretion.

## Analysis & breakdown

- Tech stack:
    - Java
    - Spring Boot
    - Docker

- Requirement 1. :
    - Build extensible hierarchical data model
    - Build required channel functionality
    - JSON serialisation?
    - Unit Testing
    - Security
        - Encapsulation
    - Exception handling
    - Thread safe
    - Logging
    - Distributed Tracing
    - Design Patterns - Builder, Factory, ...

- Requirement 2. :    
    - Containerize with Docker, so it can be scaled in Kubernetes

- Requirement 3. :
    - Implement "at least once" logic to guarantee SLA

- Documentation
 - System Design Diagram
 - Further development
    - Technologies
        - Kafka
        - AWS SNS + SQS
        - JMS
        - RabbitMQ
