package com.example.demo.Entities;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.persistence.*;

@Entity(name = "media")
public class MediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String path;
    String type;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    public MediaEntity() {

    }

    public MediaEntity(long id, String path, String type, PostEntity postEntity) {
        this.id = id;
        this.path = path;
        this.type = type;
        this.postEntity = postEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PostEntity getPostEntity() {
        return postEntity;
    }

    public void setPostEntity(PostEntity postEntity) {
        this.postEntity = postEntity;
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
