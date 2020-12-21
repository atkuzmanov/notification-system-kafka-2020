package com.example.javamvnspringbtblank.service.channel;

import com.example.javamvnspringbtblank.exception.NotificationException;
import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;

/**
 * An interface allowing for the code base to be easily extensible to support more channels in the future.
 * It also comes with a couple of default methods which make use of the custom exception
 * `com.example.javamvnspringbtblank.exception.NotificationException`.
 * This is an elegant way of providing an Interface Contract and handling channels which have not provided
 * concrete implementation yet. If they want to avoid the exceptions thrown by these default methods, they will have to
 * honour the Interface Contract and provide concrete implementations.
 */
public interface Channel {
    default void notify(Producer producerm, Notification notification) {
        throw new NotificationException("Notify method is not implemented yet.");
    }

    default boolean supports(NotificationChannelType type) {
        throw new NotificationException("Notify method is not implemented yet.");
    }
}
