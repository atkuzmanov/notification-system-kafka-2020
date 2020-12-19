package com.example.javamvnspringbtblank.service.channel;

import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;

public interface Channel {
    default void notify(Producer producerm, Notification notification) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }

    default boolean supports(NotificationChannelType type) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }
}
