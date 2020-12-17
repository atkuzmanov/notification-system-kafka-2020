package com.example.javamvnspringbtblank.webrest;

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

    @Autowired
    private ChannelNotificationService service;

    @RequestMapping({"/", "/index", "/index.html", "/index.htm"})
    public String returnIndexPage(Model model) {
        return "Please see documentation for correct use of this service.";
    }

    @PostMapping(path = "/notify/{channelType}")
    public @ResponseBody
    ResponseEntity<String> notify(@PathVariable NotificationChannelType channelType, @RequestBody BasicNotification notification) {
        long sentNotificationId = service.notify(channelType, notification);
        return new ResponseEntity<>("Notification", HttpStatus.OK);
    }

    @PostMapping("/notifyAll")
    public long notifyAll(@RequestBody BasicNotification notification) {
        return service.notifyAll(notification);
    }
}
