package com.example.javamvnspringbtblank.service.outbound;

import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;

/**
 * An interface for `NotificationService` implementations defining a minimum set of requirements which need to be
 * implemented.
 */
public interface NotificationService {
    long notify(Producer producer, NotificationChannelType channelType, Notification notification);

    long notifyAll(Producer producer, Notification notification);
}
