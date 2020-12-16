package com.example.javamvnspringbtblank.model;

import java.util.concurrent.atomic.AtomicInteger;

public interface Notification {
    long getNotificationId();

    void setNotificationId(long notificationId);

    String getMessage();

    void setMessage(String message);
}
