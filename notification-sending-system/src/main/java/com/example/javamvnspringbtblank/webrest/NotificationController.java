package com.example.javamvnspringbtblank.webrest;

import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
 @RequestMapping("/notification-service")
public class NotificationController {

    @Autowired
    private ChannelNotificationService service;

    @PostMapping(path = "/notify/{channelType}")
    public long notify(@PathVariable NotificationChannelType channelType, @RequestBody BasicNotification notification) {
        return service.notify(channelType, notification);
    }

    @PostMapping("/notifyAll")
    public long notifyAll(@RequestBody BasicNotification notification) {
        return service.notifyAll(notification);
    }
}
