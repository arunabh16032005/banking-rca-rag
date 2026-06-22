package com.example.demo.kb;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class KnowledgeRepository {

    public List<KnowledgeDocument> getDocuments() {

        return List.of(

            new KnowledgeDocument(
                "Database Pool Exhaustion",
                "database timeout connection refused slow transaction",
                "hikaricp pool exhausted",
                "increase pool size"
            ),

            new KnowledgeDocument(
                "Kafka Consumer Lag",
                "consumer lag delayed processing",
                "consumer crash",
                "restart consumer"
            ),

            new KnowledgeDocument(
                "Redis Connection Failure",
                "cache unavailable timeout",
                "redis node down",
                "restart redis"
            )
        );
    }
}