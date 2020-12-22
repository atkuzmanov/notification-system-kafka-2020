package com.example.javamvnspringbtblank.service.outbound;

import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.channel.Channel;
import com.example.javamvnspringbtblank.service.channel.ChannelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A concrete implementation of the `com.example.javamvnspringbtblank.service.outbound.NotificationService` interface.
 * This service provides functionality which can be triggered by the system's REST endpoints to send notifications.
 */
@Service
public class ChannelNotificationService implements NotificationService {

    @Autowired
    ChannelFactory factory;

    public ChannelNotificationService(ChannelFactory factory) {
        this.factory = factory;
    }

    /**
     * Notifies all available channels with the specified notification
     *
     * @param notification
     * @return the notification id
     */
    @Override
    public long notifyAll(Producer producer, Notification notification) {
        for (Channel c : factory.getChannels()) {
            c.notify(producer, notification);
        }
        return notification.getNotificationId();
    }

    /**
     * Notifies the specified channel type with a specified notification
     *
     * @param channelType
     * @param notification
     * @return the notification id
     */
    public long notify(Producer producer, NotificationChannelType channelType, Notification notification) {
        Channel channelToNotify = factory.get(channelType);
        channelToNotify.notify(producer, notification);
        return notification.getNotificationId();
    }
}
