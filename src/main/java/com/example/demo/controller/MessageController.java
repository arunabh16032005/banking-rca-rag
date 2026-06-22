package com.example.demo.controller;

import model.LogEvent;
import com.example.demo.producer.KafkaProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private KafkaProducerService producerService;

    @PostMapping("/send")
    public String sendLog(@RequestBody LogEvent event) {

        event.setTimestamp(LocalDateTime.now().toString());

        producerService.sendLog(event);

        return "Log Event Sent Successfully";
    }
}