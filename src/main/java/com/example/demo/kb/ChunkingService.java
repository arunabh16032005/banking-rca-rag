package com.example.demo.kb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChunkingService {

    public List<String> chunkDocument(
            String document) {

        List<String> chunks =
                new ArrayList<>();

        String[] sections =
                document.split(
                        "(?=Symptoms:)|(?=Root Cause:)|(?=Resolution:)|(?=Affected Services:)");

        for(String section : sections) {

            String chunk = section.trim();

            if(!chunk.isEmpty()) {

                chunks.add(chunk);
            }
        }

        return chunks;
    }
}