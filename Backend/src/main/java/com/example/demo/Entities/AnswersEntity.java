package com.example.demo.Entities;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class AnswersEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    public AnswersEntity() {
    }

    public AnswersEntity(Long id, String answer, UserEntity user, QuestionEntity question) {
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

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public QuestionEntity getQuestion() {
        return this.question;
    }

    public void setQuestion(QuestionEntity question) {
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