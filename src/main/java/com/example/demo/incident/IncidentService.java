package com.example.demo.incident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LogEventRepository;

import model.LogEvent;

import com.example.demo.kb.KnowledgeDocument;
import com.example.demo.kb.KnowledgeService;

@Service
public class IncidentService {

    @Autowired
    private LogEventRepository repository;

    @Autowired
    private KnowledgeService knowledgeService;
    
    public RcaResponse generateRca() {

        Map<String,Integer> errors =
                getFrequentErrors();

        String topError = "";

        int max = 0;

        for(Map.Entry<String,Integer> entry :
                errors.entrySet()) {

            if(entry.getValue() > max) {

                max = entry.getValue();
                topError = entry.getKey();
            }
        }

        KnowledgeDocument doc =
                knowledgeService
                        .findMatchingDocument(topError);

        if(doc == null) {

            return new RcaResponse(
                    topError,
                    "Unknown",
                    "No RCA Found",
                    "Manual Investigation Required"
            );
        }

        return new RcaResponse(
                topError,
                doc.getTitle(),
                doc.getRootCause(),
                doc.getResolution()
        );
    }
    
    
    // Get all ERROR logs
    public List<LogEvent> getAllErrors() {

        return repository.findByLevel("ERROR");
    }

    // Count errors by service
    public Map<String, Integer> getServiceErrorCounts() {

        List<LogEvent> errors =
                repository.findByLevel("ERROR");

        Map<String, Integer> errorMap =
                new HashMap<>();

        for (LogEvent event : errors) {

            String service = event.getService();

            errorMap.put(
                    service,
                    errorMap.getOrDefault(service, 0) + 1
            );
        }

        return errorMap;
    }

    // Detect repeated message patterns
    public Map<String, Integer> getFrequentErrors() {

    	List<LogEvent> logs =
    	        repository.findByLevel("ERROR");

        Map<String, Integer> frequencyMap =
                new HashMap<>();

        for (LogEvent event : logs) {

            String message = event.getMessage();

            frequencyMap.put(
                    message,
                    frequencyMap.getOrDefault(message, 0) + 1
            );
        }

        return frequencyMap;
    }
}