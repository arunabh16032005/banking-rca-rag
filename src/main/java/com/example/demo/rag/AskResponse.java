package com.example.demo.rag;

public class AskResponse {

    private String question;
    private String answer;

    public AskResponse() {
    }

    public AskResponse(
            String question,
            String answer) {

        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(
            String question) {

        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(
            String answer) {

        this.answer = answer;
    }
}