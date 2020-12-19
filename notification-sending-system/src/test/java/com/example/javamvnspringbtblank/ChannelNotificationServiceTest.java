package com.example.javamvnspringbtblank;

import com.example.javamvnspringbtblank.kafka.Consumer;
import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.channel.*;
import com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService;
import com.example.javamvnspringbtblank.service.outbound.NotificationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
class ChannelNotificationServiceTest {
    private NotificationService service;
    private ChannelFactory factory;
    @Mock private KafkaTemplate<String, String> kafkaTemplate;
    @Mock private Producer producer;
    @Mock private Consumer consumer;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        List<String> supportedChannelList = new ArrayList<>();
        supportedChannelList.add("email");
        supportedChannelList.add("slack");
        supportedChannelList.add("sms");
        factory = new ChannelFactory(supportedChannelList);
        service = new ChannelNotificationService(factory);
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testNotifyEmailTestNullsUnhappyPath() {
    }


    // todo: can be extracted to a testing utility class if more such methods come about
    protected static Notification generateNotification() {
        Notification notification = new BasicNotification();
        notification.setNotificationId(1);
        notification.setMessage("Test message 1.");
        return notification;
    }
}
