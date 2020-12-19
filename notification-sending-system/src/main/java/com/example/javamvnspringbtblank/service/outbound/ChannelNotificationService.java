package com.example.javamvnspringbtblank.service.outbound;

import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.channel.Channel;
import com.example.javamvnspringbtblank.service.channel.ChannelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ChannelNotificationService implements NotificationService {

    private AtomicInteger notificationId = new AtomicInteger(1);

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
    public long notifyAll(Notification notification) {
        for (Channel c : factory.getChannels()) {
            notification.setNotificationId(notificationId.getAndIncrement());
            c.notify(notification);
        }
        return notificationId.longValue();
    }

    /**
     * Notifies the specified channel type with a specified notification
     *
     * @param channelType
     * @param notification
     * @return notification id
     */
    public long notify(NotificationChannelType channelType, Notification notification) {
        notification.setNotificationId(notificationId.getAndIncrement());
        Channel channelToNotify = factory.get(channelType);
        channelToNotify.notify(notification);
        return notificationId.longValue();
    }
}
