package com.example.javamvnspringbtblank.service.channel;

import com.example.javamvnspringbtblank.model.NotificationChannelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.javamvnspringbtblank.model.NotificationChannelType.valueOf;

/**
 *   A basic implementation of a `Factory Design Pattern` for producing the relevant Channel objects
 *   based on the required type and hence providing the relevant functionality.
 *
 *   The `ChannelFactory` is configured with supported channels from the `application.properties` configuration file,
 *   thus making it more flexible and extensible for the future.
 */
@Component
public class ChannelFactory {
    private final List<Channel> channelList;

    @Autowired
    public ChannelFactory(
            @Value("#{'${supported.channel.list}'.replace(' ', '').split(',')}")
                    List<String> supportedChannelListConfig) {
        this.channelList = configureFactory(supportedChannelListConfig);
    }

    public Channel get(final NotificationChannelType channelType) {
        return channelList
                .stream()
                .filter(service -> service.supports(channelType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found: channel with type: " + channelType));
    }

    public List<Channel> getChannels() {
        return channelList;
    }

    private List<Channel> configureFactory(List<String> supportedChannelListConfig) {
        supportedChannelListConfig = Optional.ofNullable(supportedChannelListConfig).orElse(new ArrayList<>());
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
                default:
                    throw new IllegalArgumentException("Unsupported channel type.");
            }
        }
        return channelList;
    }
}
