package com.example.demo.Entities;

import javax.persistence.*;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

@Entity
@Table(name = "reactionType")
public class ReactionEntity {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id ;
    private String reactionType ;
    private String colorName ;

    public ReactionEntity() {
    }

    public ReactionEntity(Long id, String reactionType, String colorName) {
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
        return null ;
    }

    


    
}
