package com.example.demo.kb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kb")
public class ChunkController {

    @Autowired
    private KnowledgeLoaderService loader;

    @Autowired
    private ChunkingService chunkingService;

    @GetMapping("/chunk-count")
    public String getChunkCount()
            throws IOException {

        int count = 0;

        for(String doc :
                loader.loadDocuments()) {

            count += chunkingService
                        .chunkDocument(doc)
                        .size();
        }

        return "Total Chunks = " + count;
    }
}