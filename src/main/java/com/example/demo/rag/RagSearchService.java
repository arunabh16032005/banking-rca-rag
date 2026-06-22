package com.example.demo.rag;

import java.util.List;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RagSearchService {

    @Autowired
    private EmbeddingService embeddingService;

    private final RestTemplate restTemplate =
            new RestTemplate();

    public List<SearchResult> search(String query) {

        List<Double> vector =
                embeddingService.getEmbedding(query);

        Map<String,Object> knnRequest =
                Map.of(
                        "knn",
                        Map.of(
                                "field", "embedding",
                                "query_vector", vector,
                                "k", 5,
                                "num_candidates", 20
                        )
                );

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> request =
                new HttpEntity<>(
                        knnRequest,
                        headers
                );

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        "http://localhost:9200/knowledge-vectors/_search",
                        request,
                        String.class
                );

        try {

            ObjectMapper mapper =
                    new ObjectMapper();

            JsonNode root =
                    mapper.readTree(
                            response.getBody());

            JsonNode hits =
                    root.path("hits")
                            .path("hits");

            List<SearchResult> results =
                    new ArrayList<>();

            for(JsonNode hit : hits) {

                String file =
                        hit.path("_source")
                                .path("file")
                                .asText();

                String content =
                        hit.path("_source")
                                .path("content")
                                .asText();

                double score =
                        hit.path("_score")
                                .asDouble();

                results.add(
                        new SearchResult(
                                file,
                                content,
                                score
                        )
                );
            }

            return results;

        }
        catch(Exception e) {

            throw new RuntimeException(
                    e.getMessage()
            );
        }
    }
}