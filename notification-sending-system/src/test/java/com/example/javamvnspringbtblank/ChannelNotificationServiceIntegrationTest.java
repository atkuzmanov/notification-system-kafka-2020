package com.example.javamvnspringbtblank;

import com.example.javamvnspringbtblank.kafka.Consumer;
import com.example.javamvnspringbtblank.kafka.Producer;
import com.example.javamvnspringbtblank.model.NotificationChannelType;
import com.example.javamvnspringbtblank.service.channel.EmailChannel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static com.example.javamvnspringbtblank.ChannelNotificationServiceTest.generateNotification;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {App.class, Producer.class, Consumer.class, EmailChannel.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
//@DirtiesContext
//@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class ChannelNotificationServiceIntegrationTest {
    private static final String BASE_URL = "/notification-service";
    private static final String HOST = "http://localhost:";

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;


//    @Autowired
//    private Consumer consumer;
//
//    @Autowired
//    private Producer producer;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testNotifyEmailSuccessHappyPath() throws Exception {
        String url = prepareUrl(NotificationChannelType.email);
        mvc.perform(post(url)
                .content(generateMessageJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String prepareUrl(NotificationChannelType type) {
        // http://localhost:8080/notification-service/notify/{type}
        StringBuilder url = new StringBuilder().append("/notify/").append(type);
        return prepareURLWithPort(url.toString());
    }

    private String prepareURLWithPort(String uri) {
        return new StringBuilder(HOST).append(port).append(BASE_URL).append(uri).toString();
    }

    private String generateMessageJson() throws IOException {
        return objectMapper.writeValueAsString(generateNotification());
    }
}
