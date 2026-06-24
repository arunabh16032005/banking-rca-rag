package com.example.demo.rag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RagAskService {

    @Autowired
    private RagSearchService ragSearchService;

    @Autowired
    private OllamaService ollamaService;
    
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
        		You are an expert Banking Operations and RCA Assistant.

        		User Question:
        		%s

        		Knowledge Context:

        		%s

        		Instructions:

        		1. Use the provided knowledge context as your primary source of information.
        		2. Use additional banking, software operations, and incident management knowledge whenever needed.
        		3. Never mention file names, document names, document sources, knowledge base names, RCA records, or retrieved documents.
        		4. Never explain where the information came from.
        		5. Answer the user's question directly and naturally.
        		6. Use simple, professional, customer-friendly language.
        		7. Focus on what is most likely happening and what should be done next.
        		8. Avoid technical jargon unless necessary.
        		9. Do not say phrases such as:
        		   - "According to the RCA documents"
        		   - "Based on the retrieved documents"
        		   - "The knowledge base suggests"
        		   - "The files indicate"
        		10. Present the answer as a confident analysis combining internal operational knowledge and domain expertise.
        		11. If the exact situation is not covered by the context, provide the most reasonable explanation using general banking and operational knowledge.
        		12. Keep answers concise and practical.

        		Return the answer in the following format:

        		Summary:
        		<short direct answer>

        		Possible Cause:
        		<most likely cause>

        		Recommended Action:
        		<clear next steps>

        		Additional Notes:
        		<any useful information if needed>

        		""".formatted(
        		        question,
        		        context.toString()
        		);

        String answer =
                ollamaService.askLlama(
                        prompt
                );

        return new AskResponse(
                question,
                answer
        );
    }
}