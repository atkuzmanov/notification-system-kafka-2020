package com.example.javamvnspringbtblank.model;

public enum NotificationChannelType {
    email, sms, slack;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
