package com.example.demo.Entities;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.persistence.*;

@Entity
@Table(name = "reactionType")
public class ReactionTypeEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String reactionType;
    private String colorName;

    public ReactionTypeEntity() {
    }

    public ReactionTypeEntity(Long id, String reactionType, String colorName) {
        this.id = id;
        this.reactionType = reactionType;
        this.colorName = colorName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReactionType() {
        return this.reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }

    public String getColorName() {
        return this.colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
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
