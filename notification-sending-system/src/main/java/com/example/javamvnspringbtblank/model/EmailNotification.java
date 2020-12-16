package com.example.javamvnspringbtblank.model;

import java.util.concurrent.atomic.AtomicInteger;

public class EmailNotification extends NotificationBase {
    private AtomicInteger notificationId;

    private String msg;

    public AtomicInteger getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(AtomicInteger notificationId) {
        this.notificationId = notificationId;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EmailNotification() {
    }

    public EmailNotification(String msg) {
        this.msg = msg;
    }

    @Override
    public String message() {
        return msg;
    }

    // todo: toString()
    // todo: override equals() & hash() methods
}
