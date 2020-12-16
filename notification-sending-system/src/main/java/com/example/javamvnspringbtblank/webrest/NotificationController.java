package com.example.javamvnspringbtblank.webrest;

import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.channel.Channel;
import com.example.javamvnspringbtblank.service.channel.ChannelFactory;
import com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
// @RequestMapping("/notification-service")
public class NotificationController {

    @Autowired
    private ChannelNotificationService service;




//    NotificationController() {
//        List<Channel> channelList = new ArrayList<>();
//        for(String s : supportedChannelListConfig) {
//            channelList.add(NotificationChannelType.)
//        }
//        ChannelFactory channelFactory = new ChannelFactory(channelList);
//        service = new ChannelNotificationService(channelFactory);
//    }


    @PostMapping(path = "/notify")
    public long notify(@RequestParam NotificationChannelType channelType, @RequestBody BasicNotification notification) {
        return service.notify(channelType, notification);
    }

    @PostMapping("/notifyAll")
    public long notifyAll(@RequestBody BasicNotification notification) {
        return service.notifyAll(notification);
    }
}
