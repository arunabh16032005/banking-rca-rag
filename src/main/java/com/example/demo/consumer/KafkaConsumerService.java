package com.example.demo.consumer;

import model.LogEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LogEventRepository;

@Service
public class KafkaConsumerService {

    @Autowired
    private LogEventRepository repository;

    @KafkaListener(
            topics = "banking-logs",
            groupId = "monitor-group"
    )
    public void consume(LogEvent event) {

        // Save event into Elasticsearch
        repository.save(event);

        System.out.println("Received Event:");

        System.out.println("Service: " + event.getService());

        System.out.println("Level: " + event.getLevel());

        System.out.println("Message: " + event.getMessage());

        System.out.println("Timestamp: " + event.getTimestamp());

        System.out.println("Saved To Elasticsearch Successfully");
    }
}