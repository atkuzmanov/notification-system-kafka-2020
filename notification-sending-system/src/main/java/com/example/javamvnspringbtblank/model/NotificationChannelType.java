package com.example.javamvnspringbtblank.model;

public enum NotificationChannelType {
    email("email"), sms("sms"), slack("slack");

    private String name;

    NotificationChannelType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public NotificationChannelType getChannelTypeByName(String name) {
        for (NotificationChannelType channelType : NotificationChannelType.values()) {
            if (channelType.name().equals(name)) {
                return channelType;
            }
        }
        return null;
    }
}
