package com.example.javamvnspringbtblank.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Kafka Producer implementation.
 */
@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ListenableFuture<SendResult<String, String>> sendMessage(String topic, String key, String message) {
        return this.kafkaTemplate.send(topic, key, message);
    }
}
