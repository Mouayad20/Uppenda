package com.example.demo.Models;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

public class QuestionModel {

    private Long id;
    private String question;

    public QuestionModel() {
    }

    public QuestionModel(Long id, String question) {
        this.id = id;
        this.question = question;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        try {
            return DemoApplication.objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}