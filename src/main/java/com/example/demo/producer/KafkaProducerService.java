package com.example.demo.producer;

import model.LogEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "banking-logs";

    @Autowired
    private KafkaTemplate<String, LogEvent> kafkaTemplate;

    public void sendLog(LogEvent event) {

        kafkaTemplate.send(TOPIC, event);

        System.out.println("Log Sent: " + event.getMessage());
    }
}