package com.example.javamvnspringbtblank.model;

/**
 * Enum type is preferred to using interfaces or other methods for containing CONSTANT values.
 * This also allows a good mechanism for implementing a `Factory Design Pattern`, done in the `ChannelFactory.class`.
 */
public enum NotificationChannelType {
    email("email"), sms("sms"), slack("slack");

    private final String name;

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
