package com.example.demo.kb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

@Service
public class FileReaderService {

    public String readFile(Path path)
            throws IOException {

        return Files.readString(path);
    }
}