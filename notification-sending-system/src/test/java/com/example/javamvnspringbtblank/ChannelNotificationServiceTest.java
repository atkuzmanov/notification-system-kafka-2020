package com.example.javamvnspringbtblank;

import com.example.javamvnspringbtblank.kafka.Consumer;
import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.channel.ChannelFactory;
import com.example.javamvnspringbtblank.service.channel.EmailChannel;
import com.example.javamvnspringbtblank.service.outbound.ChannelNotificationService;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ChannelNotificationServiceTest {
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

    @Mock
    private SendResult<String, String> sendResult;

    @Mock
    private ListenableFuture<SendResult<String, String>> responseFuture;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
//        sendResult = mock(SendResult.class);
//        responseFuture = mock(ListenableFuture.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void canPublishToKafka() throws ExecutionException, InterruptedException {
        String key = "IN_KEY";
        String topic = "INPUT_DATA";
        long offset = 1L;
        int partition = 1;
        String testMsg = "Test message 1.";

        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition(topic, partition), offset, 0L, 0L, 0L, 0, 0);

        when(channelFactory.get(NotificationChannelType.email)).thenReturn(emailChannel);
        when(sendResult.getRecordMetadata()).thenReturn(recordMetadata);
        when(kafkaTemplate.send(topic, key, testMsg)).thenReturn(responseFuture);
        when(producer.sendMessage(topic, key, testMsg)).thenReturn(responseFuture);
        when(responseFuture.get()).thenReturn(sendResult);

        long resultId = service.notify(producer, NotificationChannelType.email, TestUtils.generateNotification());

        assertThat("Correct notification id is returned.", resultId == 2L);
        verify(kafkaTemplate, times(1)).send(topic, key, testMsg);
    }

    @Test
    public void canPublishToKafkaAscii() throws ExecutionException, InterruptedException {
        String key = "IN_KEY";
        String topic = "INPUT_DATA";
        long offset = 1L;
        int partition = 1;
        String testMsg = "Test message 1.\t™";

        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition(topic, partition), offset, 0L, 0L, 0L, 0, 0);

        when(channelFactory.get(NotificationChannelType.email)).thenReturn(emailChannel);
        when(sendResult.getRecordMetadata()).thenReturn(recordMetadata);
        when(kafkaTemplate.send(topic, key, testMsg)).thenReturn(responseFuture);
        when(producer.sendMessage(topic, key, testMsg)).thenReturn(responseFuture);
        when(responseFuture.get()).thenReturn(sendResult);

        long resultId = service.notify(producer, NotificationChannelType.email, TestUtils.generateNotification(testMsg));

        assertThat("Correct notification id is returned.", resultId == 2L);
        verify(kafkaTemplate, times(1)).send(topic, key, testMsg);
    }

    @Test(expected = RuntimeException.class)
//    public void canPublishToKafkaNull() throws ExecutionException, InterruptedException {
    public void canPublishToKafkaNull() {
        String key = "IN_KEY";
        String topic = "INPUT_DATA";
        long offset = 1L;
        int partition = 1;
        String testMsg = "Test message 1.\t™";

        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition(topic, partition), offset, 0L, 0L, 0L, 0, 0);

//        when(channelFactory.get(NotificationChannelType.email)).thenReturn(emailChannel);
//        when(sendResult.getRecordMetadata()).thenReturn(recordMetadata);
        when(kafkaTemplate.send(topic, key, testMsg)).thenReturn(responseFuture);
        when(producer.sendMessage(topic, key, testMsg)).thenReturn(responseFuture);
//        when(responseFuture.get()).thenReturn(sendResult);

        long resultId = service.notify(producer, NotificationChannelType.sms, TestUtils.generateNotification(testMsg));

//        assertThat("Correct notification id is returned.", resultId == 2L);
        verify(kafkaTemplate, times(0)).send(topic, key, testMsg);
    }
}
