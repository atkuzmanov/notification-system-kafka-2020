package com.example.javamvnspringbtblank.model;

public enum NotificationChannelType {
    email, sms, slack;

//    private String type;
//
//    private NotificationChannelType(String type) {
//        this.type = type;
//    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
