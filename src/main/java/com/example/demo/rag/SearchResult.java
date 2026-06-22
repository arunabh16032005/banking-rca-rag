package com.example.demo.rag;

public class SearchResult {

    private String file;
    private String content;
    private double score;

    public SearchResult() {
    }

    public SearchResult(
            String file,
            String content,
            double score) {

        this.file = file;
        this.content = content;
        this.score = score;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(
            String content) {

        this.content = content;
    }

    public double getScore() {
        return score;
    }

    public void setScore(
            double score) {

        this.score = score;
    }
}