package com.example.demo.ai;

import java.util.List;

import model.LogEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin("*")
public class AIQueryController {

    @Autowired
    private RetrievalService retrievalService;

    @Autowired
    private OpenAIService openAIService;

    // =====================================
    // Get logs by service
    // =====================================
    @GetMapping("/service/{service}")
    public List<LogEvent> queryServiceLogs(
            @PathVariable String service) {

        return retrievalService
                .getLogsByService(service);
    }

    // =====================================
    // Get logs by level
    // =====================================
    @GetMapping("/level/{level}")
    public List<LogEvent> queryByLevel(
            @PathVariable String level) {

        return retrievalService
                .getLogsByLevel(level);
    }

    // =====================================
    // Semantic keyword search
    // =====================================
    @GetMapping("/search/{keyword}")
    public List<LogEvent> semanticSearch(
            @PathVariable String keyword) {

        return retrievalService
                .searchLogs(keyword);
    }

    // =====================================
    // AI RCA Endpoint
    // =====================================
    @GetMapping("/rca/{service}")
    public String generateRCA(
            @PathVariable String service) {

        List<LogEvent> logs =
                retrievalService.getLogsByService(service);

        StringBuilder logText =
                new StringBuilder();

        for (LogEvent log : logs) {

            logText.append("Service: ")
                    .append(log.getService())
                    .append(", Level: ")
                    .append(log.getLevel())
                    .append(", Message: ")
                    .append(log.getMessage())
                    .append(", Timestamp: ")
                    .append(log.getTimestamp())
                    .append("\n");
        }

        return openAIService
                .analyzeLogs(logText.toString());
    }
}