package com.example.javamvnspringbtblank.model;

public class BasicNotification extends NotificationBase {
    private long notificationId;

    private String msg;

    public long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BasicNotification() {
    }

    public BasicNotification(String msg) {
        this.msg = msg;
    }

    @Override
    public String message() {
        return msg;
    }

    // todo: toString()
    // todo: override equals() & hashCode() methods
}
