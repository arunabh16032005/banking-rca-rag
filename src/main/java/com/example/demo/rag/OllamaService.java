package com.example.demo.rag;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OllamaService {

    private final RestTemplate restTemplate =
            new RestTemplate();

    public String askLlama(String prompt) {

        OllamaRequest requestBody =
                new OllamaRequest();

        requestBody.setModel("llama3");
        requestBody.setPrompt(prompt);
        requestBody.setStream(false);

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON);

        HttpEntity<OllamaRequest> request =
                new HttpEntity<>(
                        requestBody,
                        headers
                );

        ResponseEntity<OllamaResponse> response =
                restTemplate.postForEntity(
                        "http://localhost:11434/api/generate",
                        request,
                        OllamaResponse.class
                );

        return response.getBody()
                .getResponse();
    }
}