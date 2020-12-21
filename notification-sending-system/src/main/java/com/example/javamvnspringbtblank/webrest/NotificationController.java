package com.example.javamvnspringbtblank.webrest;

import com.example.javamvnspringbtblank.dao.NotificationDao;
import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * - A REST controller providing the application with REST endpoints.
 * - It makes use of `com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService` to send
 * the relevant notifications.
 * - It makes use of `com.example.javamvnspringbtblank.dao.NotificationDao` to persist, in the `MySQL database`,
 * any notifications which have gone through the system.
 */
@RestController
@RequestMapping({"/", "/notification-service"})
public class NotificationController {

    @Autowired
    private NotificationDao notificationDao;

    private final Producer producer;

    @Autowired
    NotificationController(Producer producer) {
        this.producer = producer;
    }

    @Autowired
    private ChannelNotificationService service;

    @RequestMapping({"/", "/index", "/index.html", "/index.htm"})
    public String returnIndexPage(Model model) {
        return "Please see documentation for correct use of this service.";
    }

    @PostMapping(path = "/notify/{channelType}")
    public @ResponseBody
    ResponseEntity<String> notify(@PathVariable NotificationChannelType channelType, @RequestBody BasicNotification notification) {
        service.notify(producer, channelType, notification);
        long persistedNotifId = notificationDao.save(notification).getNotificationId();
        return new ResponseEntity<>("Notification [" + persistedNotifId + "] successfully sent to channel [" + channelType
                + "] and persisted in database.", HttpStatus.OK);
    }

    @PostMapping("/notifyAll")
    public @ResponseBody
    ResponseEntity<String> notifyAll(@RequestBody BasicNotification notification) {
        service.notifyAll(producer, notification);
        long persistedNotifId = notificationDao.save(notification).getNotificationId();
        return new ResponseEntity<>("Notification [" + persistedNotifId +
                "] successfully sent to all channels and persisted in database.", HttpStatus.OK);
    }
}
