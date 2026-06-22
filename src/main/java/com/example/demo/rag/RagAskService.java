package com.example.demo.rag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RagAskService {

    @Autowired
    private RagSearchService ragSearchService;

    public AskResponse ask(String question) {

        List<SearchResult> results =
                ragSearchService.search(question);

        StringBuilder context =
                new StringBuilder();

        for(SearchResult result : results) {

            context.append("\n\n");
            context.append("File: ")
                    .append(result.getFile());

            context.append("\n");

            context.append(result.getContent());
        }

        String prompt =
                """
                You are an expert banking RCA assistant.

                User Question:
                %s

                Relevant RCA Documents:

                %s

                Based ONLY on the above RCA documents:

                1. Identify the likely incident.
                2. Identify the root cause.
                3. Suggest a resolution.
                4. Explain your reasoning.
                """
                .formatted(
                        question,
                        context.toString()
                );

        return new AskResponse(
                question,
                prompt
        );
    }
}