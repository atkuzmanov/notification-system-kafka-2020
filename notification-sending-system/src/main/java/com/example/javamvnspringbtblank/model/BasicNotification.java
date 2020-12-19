package com.example.javamvnspringbtblank.model;

public class BasicNotification extends NotificationBase {
    private long notificationId;

    private String message;

    public long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BasicNotification() {
    }

    public BasicNotification(String msg) {
        this.message = msg;
    }

    public BasicNotification(long notificationId, String msg) {
        this.notificationId = notificationId;
        this.message = msg;
    }

    @Override
    public String message() {
        return message;
    }

    // todo: toString()
    // todo: override equals() & hashCode() methods
}
