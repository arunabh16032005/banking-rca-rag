package com.example.demo.ai;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LogEventRepository;

import model.LogEvent;

@Service
public class RetrievalService {

    @Autowired
    private LogEventRepository repository;

    // Retrieve logs by service
    public List<LogEvent> getLogsByService(
            String service) {

        return repository.findByService(service);
    }

    // Retrieve logs by severity
    public List<LogEvent> getLogsByLevel(
            String level) {

        return repository.findByLevel(level);
    }

    // Retrieve logs by keyword
    public List<LogEvent> searchLogs(
            String keyword) {

        return repository
                .findByMessageContaining(keyword);
    }
}	