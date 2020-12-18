package com.example.javamvnspringbtblank.service.channel;

import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

@Component
public class EmailChannel implements Channel {

    private final Logger logger = LoggerFactory.getLogger(EmailChannel.class);

    private final Producer producer;

    public EmailChannel(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void notify(Notification notification) {
        System.out.println(notification.getMessage());

        ListenableFuture<SendResult<String, String>> listenableFuture = this.producer.sendMessage("INPUT_DATA", "IN_KEY", LocalDate.now().toString());

        SendResult<String, String> result = null;
        try {
            result = listenableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        logger.info(String.format("Produced:\ntopic: %s\noffset: %d\npartition: %d\nvalue size: %d", result.getRecordMetadata().topic(),
                result.getRecordMetadata().offset(),
                result.getRecordMetadata().partition(), result.getRecordMetadata().serializedValueSize()));
    }

    @Override
    public boolean supports(NotificationChannelType channelType) {
        return NotificationChannelType.email == channelType;
    }
}
