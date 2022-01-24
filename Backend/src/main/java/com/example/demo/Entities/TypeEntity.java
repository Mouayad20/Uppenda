package com.example.demo.Entities;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.persistence.*;

@Entity(name = "type")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;

    public  TypeEntity(){}

    public TypeEntity(String type) {
        this.type = type;
    }

    public TypeEntity(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
