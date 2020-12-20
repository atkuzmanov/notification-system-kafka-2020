package com.example.javamvnspringbtblank.exception;

public class NotificationException extends RuntimeException {
    public NotificationException(String errorMsg) {
        super(errorMsg);
    }
}
