package com.example.javamvnspringbtblank.service.channel;

import com.example.javamvnspringbtblank.model.NotificationChannelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChannelFactory {
    private final List<Channel> channelList;

    @Autowired
    public ChannelFactory(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public Channel get(NotificationChannelType c) {
        return channelList
                .stream()
                .filter(service -> service.supports(c))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found: channel with type: " + c));
    }

    public List<Channel> getChannels() {
        return channelList;
    }
}
