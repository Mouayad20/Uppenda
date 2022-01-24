package com.example.demo.Models;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

public class MediaModel {

    long id;

    
    String path;
    String type;
    PostModel postModel;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PostModel getPostModel() {
        return this.postModel;
    }

    public void setPostModel(PostModel postModel) {
        this.postModel = postModel;
    }

    public MediaModel() {

    }

    public MediaModel(String path, String type) {
        this.path = path;
        this.type = type;
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
