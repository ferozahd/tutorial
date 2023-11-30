package com.kafka.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private KafkaTemplate<String, MessageRequest> kafkaTemplate;


    @PostMapping("/")
    void send(@RequestBody MessageRequest messageRequest){
        kafkaTemplate.send("test-topic", messageRequest);
    }
}
