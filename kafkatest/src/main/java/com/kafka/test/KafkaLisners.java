package com.kafka.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class KafkaLisners {
    private Logger log=Logger.getLogger(KafkaListener.class.getName());
    @KafkaListener(
        topics = "test-topic",
        groupId = "groupId",
        containerFactory = "messageListener"
    )
    void listener(ConsumerRecord<String, MessageRequest> data) {
        System.out.println(data.value());
    }
}
