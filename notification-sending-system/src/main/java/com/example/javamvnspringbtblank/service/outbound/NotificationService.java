package com.example.javamvnspringbtblank.service.outbound;

import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;

public interface NotificationService {
    long notify(NotificationChannelType channelType, Notification notification);
    long notifyAll(Notification notification);
}
