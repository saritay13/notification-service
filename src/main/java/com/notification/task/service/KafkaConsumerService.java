package com.notification.task.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

@Service
public class KafkaConsumerService {

    private List<String> messages = new CopyOnWriteArrayList<>();
    private CountDownLatch latch;

    public void resetLatch(int expectedCount){
        this.latch = new CountDownLatch(expectedCount);
        this.messages.clear();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public List<String> getMessages() {
        return messages;
    }

    @KafkaListener(topics = "test-topic", groupId = "demo-group")
    public void listener1(String message){
        messages.add(message);
        if(latch != null){
            latch.countDown();
        }
        System.out.println("Message Consumed by Listener1: " + message);
    }



}
