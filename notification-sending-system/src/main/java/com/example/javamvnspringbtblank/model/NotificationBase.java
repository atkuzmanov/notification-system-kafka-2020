package com.example.javamvnspringbtblank.model;

public abstract class NotificationBase implements Notification {

    // Default access constructor to prevent external subclasses
    NotificationBase() {}

    public abstract String message();
}
