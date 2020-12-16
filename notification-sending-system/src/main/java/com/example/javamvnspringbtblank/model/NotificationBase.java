package com.example.javamvnspringbtblank.model;

public abstract class NotificationBase implements Notification {

    // prevent external subclasses
    NotificationBase() {}

    @Override
    public abstract String message();
}
