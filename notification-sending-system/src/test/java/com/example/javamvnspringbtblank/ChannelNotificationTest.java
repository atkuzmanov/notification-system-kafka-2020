package com.example.javamvnspringbtblank;

import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.channel.*;
import com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService;
import com.example.javamvnspringbtblank.service.outbound.NotificationService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest
class ChannelNotificationTest {
    private NotificationService service;
    private ChannelFactory factory;

    @Before
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        List<Channel> channelList = new ArrayList<>(2);
//        channelList.add(new SlackChannel());
//        channelList.add(new EmailChannel());
//        channelList.add(new SMSChannel());
        List<String> supportedChannelList = new ArrayList<>();
        supportedChannelList.add("email");
        supportedChannelList.add("slack");
        supportedChannelList.add("sms");
        factory = new ChannelFactory(supportedChannelList);
        service = new ChannelNotificationService(factory);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    void testNotifyHappyPath1() {
        Notification notification = generateNotification();

        assertThat(service.notify(NotificationChannelType.email, notification), is(2L));
    }

    private Notification generateNotification() {
        Notification notification = new BasicNotification();
        notification.setNotificationId(1);
        notification.setMessage("Test message 1.");
        return notification;
    }
}
