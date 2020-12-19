package com.example.javamvnspringbtblank.service.channel;

import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
public class EmailChannel implements Channel {

    private final Logger logger = LoggerFactory.getLogger(EmailChannel.class);

    @Override
    public void notify(Producer producer, Notification notification) {
        Notification noti = Optional.ofNullable(notification).orElse(new BasicNotification(1L, ""));

        // todo: remove it
        System.out.println(">>> " + noti.getMessage());

//        ListenableFuture<SendResult<String, String>> listenableFuture = this.producer.sendMessage("INPUT_DATA", "IN_KEY", noti.getMessage());
        ListenableFuture<SendResult<String, String>> listenableFuture = producer.sendMessage("INPUT_DATA", "IN_KEY", noti.getMessage());

        SendResult<String, String> result = null;
        try {
            result = listenableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
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
