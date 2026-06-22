package com.example.demo.rag;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmbeddingService {

    private final RestTemplate restTemplate =
            new RestTemplate();

    public List<Double> getEmbedding(
            String text) {

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,String>> request =
                new HttpEntity<>(
                        Map.of("text", text),
                        headers);

        ResponseEntity<EmbeddingResponse>
                response =
                restTemplate.postForEntity(
                        "http://localhost:5000/embed",
                        request,
                        EmbeddingResponse.class);

        return response.getBody()
                .getEmbedding();
    }
}