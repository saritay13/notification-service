package com.notification.task;


import com.notification.task.service.KafkaConsumerService;
import com.notification.task.service.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"test-topic"})
@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.consumer.group-id=test-group",
        "spring.kafka.consumer.auto-offset-reset=earliest"
})
class KafkaIntegrationTest {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;


    @Test
    void shouldPublishAndConsumeMultipleMessages() throws Exception {
        List<String> toSend = List.of("msg1", "msg2", "msg3");
        kafkaConsumerService.resetLatch(toSend.size());

        toSend.forEach(kafkaProducerService::sendMessage);

        boolean allMessagesConsumed = kafkaConsumerService.getLatch().await(10, TimeUnit.SECONDS);


        assertTrue(allMessagesConsumed, "Timed out waiting for messages");

        assertThat(kafkaConsumerService.getMessages())
                .containsExactlyElementsOf(toSend);

    }



}