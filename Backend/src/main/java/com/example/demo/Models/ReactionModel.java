package com.example.demo.Models;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ReactionModel {

    private Long id;
    private PostModel postModel;
    private UserModel userModel;
    private ReactionTypeModel reactionTypeModel;

    public ReactionModel() {
    }

    public ReactionModel(Long id, PostModel postModel, UserModel userModel, ReactionTypeModel reactionTypeModel) {
        this.id = id;
        this.postModel = postModel;
        this.userModel = userModel;
        this.reactionTypeModel = reactionTypeModel;
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

    public ReactionTypeModel getReactionTypeModel() {
        return this.reactionTypeModel;
    }

    public void setReactionTypeModel(ReactionTypeModel reactionTypeModel) {
        this.reactionTypeModel = reactionTypeModel;
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
