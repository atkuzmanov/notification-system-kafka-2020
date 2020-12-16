package com.example.javamvnspringbtblank.service.outbound;

import com.example.javamvnspringbtblank.model.Notification;

public interface NotificationService {
    long notifyAll(Notification notification);
}
