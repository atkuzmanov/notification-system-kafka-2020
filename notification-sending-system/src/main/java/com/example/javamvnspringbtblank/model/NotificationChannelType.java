package com.example.javamvnspringbtblank.model;

public enum NotificationChannelType {
    EMAIL, SMS, SLACK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
