package com.example.demo.rag;

public class AskResponse {

    private String question;
    private String prompt;

    public AskResponse() {
    }

    public AskResponse(
            String question,
            String prompt) {

        this.question = question;
        this.prompt = prompt;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(
            String question) {

        this.question = question;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(
            String prompt) {

        this.prompt = prompt;
    }
}