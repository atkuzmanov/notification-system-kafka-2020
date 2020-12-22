package com.example.javamvnspringbtblank.model;

/**
 * Abstract class to allow extensibility and support of different kind of notifications and prevent creation
 * of notification implementations outside of this allowed package structure. This is achieved by a constructor
 * with `default` access encapsulation.
 */
public abstract class NotificationBase implements Notification {

    /**
     * Default access constructor to prevent external subclasses
     */
    NotificationBase() {
    }

    public abstract String message();
}
