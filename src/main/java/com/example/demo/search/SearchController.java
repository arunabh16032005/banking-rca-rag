package com.example.demo.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repository.LogEventRepository;

import model.LogEvent;

@RestController
@RequestMapping("/api/search")
@CrossOrigin("*")
public class SearchController {

    @Autowired
    private LogEventRepository repository;

    // Get all logs
    @GetMapping("/all")
    public Iterable<LogEvent> getAllLogs() {

        return repository.findAll();
    }

    // Search by service
    @GetMapping("/service/{service}")
    public List<LogEvent> searchByService(
            @PathVariable String service) {

        return repository.findByService(service);
    }

    // Search by level
    @GetMapping("/level/{level}")
    public List<LogEvent> searchByLevel(
            @PathVariable String level) {

        return repository.findByLevel(level);
    }

    // Search by keyword in message
    @GetMapping("/message/{keyword}")
    public List<LogEvent> searchByMessage(
            @PathVariable String keyword) {

        return repository.findByMessageContaining(keyword);
    }
}