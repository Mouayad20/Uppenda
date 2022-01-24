package com.example.demo.Models;

import java.util.Date;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CommentModel {

    private Long id ;
    private String content ;
    private String imagePath ;
    private Date createdAt;
    private PostModel postModel;
    private UserModel userModel;

    public CommentModel() {
    }

    public CommentModel(Long id, String content, Date createdAt, PostModel postModel, UserModel userModel) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.postModel = postModel;
        this.userModel = userModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public PostModel getPostModel() {
        return postModel;
    }

    public void setPostModel(PostModel postModel) {
        this.postModel = postModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
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
