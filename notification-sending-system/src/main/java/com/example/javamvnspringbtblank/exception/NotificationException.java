package com.example.javamvnspringbtblank.exception;

/**
 * Custom exception class.
 */
public class NotificationException extends RuntimeException {
    public NotificationException(String errorMsg) {
        super(errorMsg);
    }
}
