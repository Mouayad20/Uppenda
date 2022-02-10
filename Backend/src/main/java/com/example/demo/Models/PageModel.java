package com.example.demo.Models;


import com.example.demo.DemoApplication;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PageModel {

    private long id;
    private String name;
    private String description;
    private String imgPath;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    private UserModel admin;
    private List<UserModel> members;
    private List<PostModel> postModels;


    public PageModel() {
        id = 0;
        name = description = imgPath = "test";
        createdAt = new Date();
        admin = null;
        members = new ArrayList<>();
    }

    public List<PostModel> getPostModels() {
        return this.postModels;
    }

    public void setPostModels(List<PostModel> postModels) {
        this.postModels = postModels;
    }

    public List<UserModel> getMembers() {
        return this.members;
    }

    public void setMembers(List<UserModel> members) {
        this.members = members;
    }

    public UserModel getAdmin() {
        return this.admin;
    }

    public void setAdmin(UserModel admin) {
        this.admin = admin;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return this.imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
        return "";
    }

}
