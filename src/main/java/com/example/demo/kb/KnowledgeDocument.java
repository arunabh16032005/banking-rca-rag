package com.example.demo.kb;

public class KnowledgeDocument {

    private String title;
    private String symptoms;
    private String rootCause;
    private String resolution;

    public KnowledgeDocument(
            String title,
            String symptoms,
            String rootCause,
            String resolution) {

        this.title = title;
        this.symptoms = symptoms;
        this.rootCause = rootCause;
        this.resolution = resolution;
    }

    public String getTitle() {
        return title;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getRootCause() {
        return rootCause;
    }

    public String getResolution() {
        return resolution;
    }
}