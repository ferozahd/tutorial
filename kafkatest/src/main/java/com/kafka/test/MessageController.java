package com.kafka.test;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private KafkaTemplate<String,String > kafkaTemplate;

    public MessageController(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    @PostMapping("/")
    void send(@RequestBody MessageRequest messageRequest){
        kafkaTemplate.send("amigoscode",messageRequest.getMessage());
    }
}
