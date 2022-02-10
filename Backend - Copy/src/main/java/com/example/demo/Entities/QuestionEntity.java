package com.example.demo.Entities;

import java.util.List;

import javax.persistence.*;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

@Entity
@Table( name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String question;

    @OneToMany(mappedBy = "question")
    private List<AnswersEntity> answersEntities;


    public QuestionEntity() {
    }

    public QuestionEntity(Long id, String question, List<AnswersEntity> answersEntities) {
        this.id = id;
        this.question = question;
        this.answersEntities = answersEntities;
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

    public List<AnswersEntity> getAnswersEntities() {
        return this.answersEntities;
    }

    public void setAnswersEntities(List<AnswersEntity> answersEntities) {
        this.answersEntities = answersEntities;
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
