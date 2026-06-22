package com.example.demo.kb;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kb")
public class KnowledgeController {

    @Autowired
    private KnowledgeLoaderService loader;

    @GetMapping("/documents")
    public List<String> getDocuments()
            throws IOException {

        return loader.loadDocuments();
    }
}