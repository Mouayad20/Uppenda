package com.example.demo.Models;

public class AnswerModel {

    private Long id;
    private String answer;
    private UserModel user;
    private QuestionModel question;

    public AnswerModel() {
    }

    public AnswerModel(Long id, String answer, UserModel user, QuestionModel question) {
        this.id = id;
        this.answer = answer;
        this.user = user;
        this.question = question;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public UserModel getUser() {
        return this.user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public QuestionModel getQuestion() {
        return this.question;
    }

    public void setQuestion(QuestionModel question) {
        this.question = question;
    }

}
