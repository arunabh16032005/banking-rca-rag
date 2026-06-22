package com.example.demo.kb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeService {

    @Autowired
    private KnowledgeRepository repository;

    public List<KnowledgeDocument> getAllDocuments() {

        return repository.getDocuments();
    }
    
    public KnowledgeDocument findMatchingDocument(
            String errorMessage) {

        return repository.getDocuments()
                .stream()
                .filter(doc ->
                        doc.getSymptoms()
                                .toLowerCase()
                                .contains(errorMessage.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}

