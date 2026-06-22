package com.example.demo.ai;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OpenAIService {

    private final WebClient webClient =
            WebClient.builder()
                    .baseUrl("http://localhost:11434")
                    .build();

    public String analyzeLogs(String logs) {

        String prompt = """
                You are an enterprise banking AI operations expert.

                Analyze these logs and provide:

                1. Incident Summary
                2. Root Cause
                3. Severity
                4. Recommended Fix

                Logs:
                """ + logs;

        Map<String, Object> requestBody = Map.of(
                "model", "llama3",
                "prompt", prompt,
                "stream", false
        );

        try {

            Map response = webClient.post()
                    .uri("/api/generate")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            return response.get("response").toString();

        } catch (Exception e) {

            e.printStackTrace();

            return "AI Analysis Failed";
        }
    }
}