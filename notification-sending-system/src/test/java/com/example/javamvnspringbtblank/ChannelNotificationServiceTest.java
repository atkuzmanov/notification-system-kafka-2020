package com.example.javamvnspringbtblank;

import com.example.javamvnspringbtblank.kafka.Consumer;
import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.BasicNotification;
import com.example.javamvnspringbtblank.model.Notification;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.channel.ChannelFactory;
import com.example.javamvnspringbtblank.service.channel.EmailChannel;
import com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class ChannelNotificationServiceTest {
    @Mock
    ChannelFactory channelFactory;

    @InjectMocks
    EmailChannel emailChannel;

    @InjectMocks
    private ChannelNotificationService service;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private Producer producer;

    @InjectMocks
    private Consumer consumer;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testNotifyEmailTestNullsUnhappyPath() {
    }

    @Test
    public void can_publishDataToKafka() throws ExecutionException, InterruptedException {
        String key = "IN_KEY";
        String topic = "INPUT_DATA";
        long offset = 1L;
        int partition = 1;
        String testMsg = "Test message 1.";

        SendResult<String, String> sendResult = mock(SendResult.class);
        ListenableFuture<SendResult<String, String>> responseFuture = mock(ListenableFuture.class);
        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition(topic, partition), offset, 0L, 0L, 0L, 0, 0);

        when(channelFactory.get(NotificationChannelType.email)).thenReturn(emailChannel);

        when(sendResult.getRecordMetadata()).thenReturn(recordMetadata);
        when(kafkaTemplate.send(topic, key, testMsg)).thenReturn(responseFuture);
//        when(kafkaTemplate.send(anyString(), anyString(), anyString())).thenReturn(responseFuture);
        when(producer.sendMessage(topic, key, testMsg)).thenReturn(responseFuture);
//        when(producer.sendMessage(anyString(), anyString(), anyString())).thenReturn(responseFuture);
        when(responseFuture.get()).thenReturn(sendResult);

        long resultId = service.notify(producer, NotificationChannelType.email, generateNotification());

        assertThat("Correct notification id is returned.", resultId == 2L);
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
