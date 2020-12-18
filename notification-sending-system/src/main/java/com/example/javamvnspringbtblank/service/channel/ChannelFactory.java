package com.example.javamvnspringbtblank.service.channel;

import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.example.javamvnspringbtblank.model.NotificationChannelType.*;

@Component
public class ChannelFactory {
    private final List<Channel> channelList;

    @Autowired
    private Producer producer;

    @Autowired
    public ChannelFactory(
            @Value("#{'${supported.channel.list}'.replace(' ', '').split(',')}")
                    List<String> supportedChannelListConfig) {
        this.channelList = configureFactory(supportedChannelListConfig);
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

    private List<Channel> configureFactory(List<String> supportedChannelListConfig) {
        List<Channel> channelList = new ArrayList<>();
        for (String suppChannel : supportedChannelListConfig) {
            NotificationChannelType type = valueOf(suppChannel);
            switch (type) {
                case email:
                    channelList.add(new EmailChannel(producer));
                    break;
                case sms:
                    channelList.add(new SMSChannel());
                    break;
                case slack:
                    channelList.add(new SlackChannel());
                    break;
            }
        }
        return channelList;
    }
}
