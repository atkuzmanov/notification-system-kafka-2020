package com.example.javamvnspringbtblank.webrest;

import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/", "/notification-service"})
public class NotificationController {

    private final Producer producer;

    @Autowired
    NotificationController(Producer producer){
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
        long notifId = service.notify(producer, channelType, notification);
        return new ResponseEntity<>("Notification [" + notifId + "] successfully sent to channel [" + channelType + "]." , HttpStatus.OK);
    }

    @PostMapping("/notifyAll")
    public @ResponseBody
    ResponseEntity<String> notifyAll(@RequestBody BasicNotification notification) {
        long notifId = service.notifyAll(notification);
        return new ResponseEntity<>("Notification [" + notifId + "] successfully sent to all channels." , HttpStatus.OK);
    }
}
