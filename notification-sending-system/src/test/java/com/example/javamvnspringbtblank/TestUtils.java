package com.example.javamvnspringbtblank;

import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.Notification;

public class TestUtils {
    protected static Notification generateNotification() {
        Notification notification = new BasicNotification();
        notification.setNotificationId(1);
        notification.setMessage("Test message 1.");
        return notification;
    }

    protected static Notification generateNotification(String testMsg) {
        Notification notification = new BasicNotification();
        notification.setNotificationId(1);
        notification.setMessage(testMsg);
        return notification;
    }
}
