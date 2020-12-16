package com.example.javamvnspringbtblank.service;

import com.example.javamvnspringbtblank.model.NotificationChannelType;

public interface NotificationService {
    void notifyAll(String message);
//    void notify(NotificationChannelType channelType, String msg);
}
