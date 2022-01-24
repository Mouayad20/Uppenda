package com.example.demo.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;


public class GroupModel {

    private long id;
    private String name;
    private String imgPath;
    private String description;
    private UserModel admin;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    private List<PostModel> postModels;
    private List<UserModel> members;


    public GroupModel(){
        id = 0;
        name = "alaa";
        imgPath = "c:\\sdfssgjznfSHJVbhbbagxyj";
        description = "this is the first group in my data base";
        createdAt = new Date();
        members = new ArrayList<>();
    }


    public GroupModel(long id, String name, String imgPath, String description, Date createdAt, List<PostModel> postModels, UserModel admin, List<UserModel> members) {
        this.id = id;
        this.name = name;
        this.imgPath = imgPath;
        this.description = description;
        this.createdAt = createdAt;
        this.postModels = postModels;
        this.admin = admin;
        this.members = members;
    }


    public List<PostModel> getPostModels() {
        return this.postModels;
    }

    public void setPostModels(List<PostModel> postModels) {
        this.postModels = postModels;
    }

    public UserModel getAdmin() {
        return this.admin;
    }

    public void setAdmin(UserModel admin) {
        this.admin = admin;
    }

    public List<UserModel> getMembers() {
        return this.members;
    }

    public void setMembers(List<UserModel> members) {
        this.members = members;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return this.imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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