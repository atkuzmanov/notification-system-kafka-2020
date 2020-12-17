package com.example.javamvnspringbtblank;

import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.channel.*;
import com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService;
import com.example.javamvnspringbtblank.service.outbound.NotificationService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.assertThat;

@SpringBootTest
//@RunWith(SpringRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ChannelNotificationTest {
    private NotificationService service;
    private ChannelFactory factory;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
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
    public void testNotifyHappyPath1() {
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
