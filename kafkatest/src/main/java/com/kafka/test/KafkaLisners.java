package com.kafka.test;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class KafkaLisners {
    private Logger log=Logger.getLogger(KafkaListener.class.getName());
    @KafkaListener(
            topics = "amigoscode",
            groupId = "groupId"
    )
  void listener(String data){
        System.out.println(data);
    }
}
