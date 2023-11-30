package com.kafka.test;

public class MessageRequest {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public MessageRequest(String message){
        this.message=message;
    }

    public MessageRequest(){

    }

    @Override
    public String toString() {
        return "MessageRequest{" +
            "message='" + message + '\'' +
            '}';
    }
}
