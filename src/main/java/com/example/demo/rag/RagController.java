package com.example.demo.rag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
public class RagController {

	@Autowired
	private RagSearchService ragSearchService;
	
	@Autowired
	private RagAskService ragAskService;
	
	@GetMapping("/search")
	public List<SearchResult> search(
	        @RequestParam String query) {

	    return ragSearchService.search(
	            query
	    );
	}	
	
	@GetMapping("/ask")
	public AskResponse ask(
	        @RequestParam String question) {

	    return ragAskService.ask(
	            question
	    );
	}
	
    @Autowired
    private EmbeddingService
            embeddingService;

    @GetMapping("/embed")
    public List<Double> embed(
            @RequestParam String query) {

        return embeddingService
                .getEmbedding(query);
    }
}