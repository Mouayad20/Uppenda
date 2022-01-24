package com.example.demo.Models;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LikeModel {

    private Long id ;
    private PostModel postModel;
    private UserModel userModel;
    private ReactionModel reactionModel;

    public LikeModel() {
    }

    public LikeModel(Long id, PostModel postModel, UserModel userModel, ReactionModel reactionModel) {
        this.id = id;
        this.postModel = postModel;
        this.userModel = userModel;
        this.reactionModel = reactionModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public ReactionModel getReactionModel() {
        return this.reactionModel;
    }

    public void setReactionModel(ReactionModel reactionModel) {
        this.reactionModel = reactionModel;
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
