package com.example.demo.incident;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.incident.RcaResponse;

import model.LogEvent;

@RestController
@RequestMapping("/api/incident")
@CrossOrigin("*")
public class IncidentController {

    @Autowired
    private IncidentService service;

    @GetMapping("/rca")
    public RcaResponse getRca() {

        return service.generateRca();
    }
    
    
    // Get all ERROR logs
    @GetMapping("/errors")
    public List<LogEvent> getErrors() {

        return service.getAllErrors();
    }

    // Error count by service
    @GetMapping("/service-health")
    public Map<String, Integer> serviceHealth() {

        return service.getServiceErrorCounts();
    }

    // Frequent/repeated errors
    @GetMapping("/frequent-errors")
    public Map<String, Integer> frequentErrors() {

        return service.getFrequentErrors();
    }
}