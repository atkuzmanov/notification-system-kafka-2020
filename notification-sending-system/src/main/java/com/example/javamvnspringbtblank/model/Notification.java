package com.example.javamvnspringbtblank.model;

/**
 * Interface to allow extensibility and support of different kind of notifications.
 * This interface provides a basic contract which any implementations need to adhere to.
 */
public interface Notification {

    String getMessage();

    long getNotificationId();

    void setMessage(String message);
}
