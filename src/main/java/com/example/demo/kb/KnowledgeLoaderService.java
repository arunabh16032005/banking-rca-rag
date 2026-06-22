package com.example.demo.kb;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeLoaderService {

    @Autowired
    private FileReaderService fileReaderService;

    public List<String> loadDocuments()
            throws IOException {

        List<String> documents =
                new ArrayList<>();

        Path folder =
                Paths.get("knowledge-base");

        DirectoryStream<Path> stream =
                Files.newDirectoryStream(
                        folder,
                        "*.txt");

        for(Path file : stream) {

            String content =
                    fileReaderService
                            .readFile(file);

            documents.add(content);
        }

        return documents;
    }
}