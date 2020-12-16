package com.example.javamvnspringbtblank.service.channel;

import com.example.javamvnspringbtblank.model.NotificationChannelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.javamvnspringbtblank.model.NotificationChannelType.*;

@Component
public class ChannelFactory {
        private final List<Channel> channelList;
//    private final List<NotificationChannelType> channelTypeList;
//    List<Channel> channelList = new ArrayList<>();

//    @Value("#{'${supported.channel.list}'.split(',')}")
//    @Value("#{${supported.channel.list}}")
//    @Value("#{'${supported.channel.list}'.replace(' ', '').split(',')}")
//    private List<String> supportedChannelListConfig;


    @Autowired
    public ChannelFactory(@Value("#{'${supported.channel.list}'.replace(' ', '').split(',')}") List<String> supportedChannelListConfig) {
        this.channelList = configureFactory(supportedChannelListConfig);
//        this.channelList = configureFactory();
//        System.out.println(supportedChannelListConfig.toArray().toString());
//        System.out.println(Arrays.asList(supportedChannelListConfig));
        System.out.println("<> " + Arrays.asList(channelList));
    }

//    @Autowired
//    public ChannelFactory(List<NotificationChannelType> channelTypeList) {
//        this.channelTypeList = channelTypeList;
//    }

//    @Autowired
//    public ChannelFactory(List<Channel> channelList) {
//        this.channelList = channelList;
//    }

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
                    channelList.add(new EmailChannel());
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
