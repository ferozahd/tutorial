package com.kafka.test.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.test.MessageRequest;
import org.apache.kafka.common.serialization.Serializer;

public class MessageSerializer implements Serializer<MessageRequest> {

    @Override
    public byte[] serialize(String topic, MessageRequest messageRequest) {
        ObjectMapper objectMapper =new ObjectMapper();
        try {
             return objectMapper.writeValueAsBytes(messageRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Serialize problem------------------");
        }
    }
}
