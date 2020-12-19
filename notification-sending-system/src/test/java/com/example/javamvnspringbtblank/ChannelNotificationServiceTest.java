package com.example.javamvnspringbtblank;

import com.example.javamvnspringbtblank.kafka.Consumer;
import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.channel.*;
import com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService;
import com.example.javamvnspringbtblank.service.outbound.NotificationService;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ChannelNotificationServiceTest {
    @Autowired
    private NotificationService service;
    @Autowired
    private ChannelFactory factory;
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;
    @InjectMocks
    private Producer producer;
    @Mock
    private Consumer consumer;

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
//        Notification testNoti = generateNotification();


//        when(producer.sendMessage(any(String.class), any(String.class), "Test message 1.")).thenReturn();
//        when(producer.sendMessage("test_topic_1", "test_key_1", "Test message 1.")).thenReturn();

//        long resultId = service.notify(producer, NotificationChannelType.email, testNoti);

//        assertThat("", resultId == 2L);
    }

    @Test
    public void can_publishDataToKafka() throws ExecutionException, InterruptedException {
        String key = "test_key_1";
        String topic = "test_topic_1";
        long offset = 1L;
        int partition = 1;
        String testMsg = "Test message 1.";

//        SiebelRecord siebelRecord = mock(SiebelRecord.class);
//        SendResult<String, Object> sendResult = mock(SendResult.class);
        SendResult<String, String> sendResult = mock(SendResult.class);
//        ListenableFuture<SendResult<String, Object>> responseFuture = mock(ListenableFuture.class);
        ListenableFuture<SendResult<String, String>> responseFuture = mock(ListenableFuture.class);
        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition(topic, partition), offset, 0L, 0L, 0L, 0, 0);

        given(sendResult.getRecordMetadata()).willReturn(recordMetadata);
//        when(sendResult.getRecordMetadata()).thenReturn(recordMetadata);
        when(producer.sendMessage(topic, key, testMsg)).thenReturn(responseFuture);
        when(responseFuture.get()).thenReturn(sendResult);
        when(kafkaTemplate.send(topic, key, testMsg)).thenReturn(responseFuture);
        doAnswer(invocationOnMock -> {
            ListenableFutureCallback listenableFutureCallback = invocationOnMock.getArgument(0);
            listenableFutureCallback.onSuccess(sendResult);
            assertEquals(sendResult.getRecordMetadata().offset(), offset);
            assertEquals(sendResult.getRecordMetadata().partition(), partition);
            return null;
        }).when(responseFuture).addCallback(any(ListenableFutureCallback.class));

//        service.publishDataToKafka(key, topic, siebelRecord);
        service.notify(producer, NotificationChannelType.email, generateNotification());

        verify(kafkaTemplate, times(1)).send(topic, key, testMsg);
    }


    // todo: can be extracted to a testing utility class if more such methods come about
    protected static Notification generateNotification() {
        Notification notification = new BasicNotification();
        notification.setNotificationId(1);
        notification.setMessage("Test message 1.");
        return notification;
    }
}
