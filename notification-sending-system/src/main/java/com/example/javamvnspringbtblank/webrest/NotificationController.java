package com.example.javamvnspringbtblank.webrest;

import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/notification-service")
public class NotificationController {

    @Autowired
    private ChannelNotificationService service;

    @PostMapping(path = "/notify")
    public long notify(@RequestParam NotificationChannelType channelType, @RequestBody BasicNotification notification) {
        return service.notify(channelType, notification);
    }

    @PostMapping("/notifyAll")
    public long notifyAll(@RequestBody BasicNotification notification) {
        return service.notifyAll(notification);
    }
}
