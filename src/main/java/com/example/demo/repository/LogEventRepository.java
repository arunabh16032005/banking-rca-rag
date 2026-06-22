package com.example.demo.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import model.LogEvent;

public interface LogEventRepository
        extends ElasticsearchRepository<LogEvent, String> {

    List<LogEvent> findByService(String service);

    List<LogEvent> findByLevel(String level);

    List<LogEvent> findByMessageContaining(String keyword);
}