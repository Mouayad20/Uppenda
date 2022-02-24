package com.example.demo.Models;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Date;
import java.util.List;

public class UserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String gender;
    private String imagePath;
    private String password;
    private String email;
    private String studyLevel;
    private String location;
    private String ip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    private boolean onLine;
    private List<PostModel> postModels;
    private List<PostModel> savedPost;
    private List<PostModel> sharedPost;
    private List<MessageModel> messages;
    private List<AnswerModel> answerModels;
    private List<GroupModel> groups;
    private List<PageModel> pages;
    private List<UserModel> friends;
    private List<CommentModel> commentModels;
    private List<ReactionModel> reactionModels;


    public UserModel() {

//        firstName = "mahmood";
//        lastName = "shamieh";
//        mobile = password = "0945861450";
//        email = "mahmoodshamiehh2000@gmail.com";
//        studyLevel = "first year at ITE";
//        onLine = false;
//        gender = "male";
//        age = created_at = new Date();
//        friends = new ArrayList<>();
//        groups = new ArrayList<>();
//        pages = new ArrayList<>();
//        postModels = new ArrayList<>();
//        commentModels = new ArrayList<>();
//        reactionModels = new ArrayList<>();
//        location = "Damascus";

    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isOnLine() {
        return this.onLine;
    }

    public boolean getOnLine() {
        return this.onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudyLevel() {
        return this.studyLevel;
    }

    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getAge() {
        return this.age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<PostModel> getPostModels() {
        return this.postModels;
    }

    public void setPostModels(List<PostModel> postModels) {
        this.postModels = postModels;
    }

    public List<CommentModel> getCommentModels() {
        return this.commentModels;
    }

    public void setCommentModels(List<CommentModel> commentModels) {
        this.commentModels = commentModels;
    }

    public List<ReactionModel> getReactionModels() {
        return this.reactionModels;
    }

    public void setReactionModels(List<ReactionModel> reactionModels) {
        this.reactionModels = reactionModels;
    }

    public List<PostModel> getSavedPost() {
        return this.savedPost;
    }

    public void setSavedPost(List<PostModel> savedPost) {
        this.savedPost = savedPost;
    }

    public List<PostModel> getSharedPost() {
        return this.sharedPost;
    }

    public void setSharedPost(List<PostModel> sharedPost) {
        this.sharedPost = sharedPost;
    }

    public List<GroupModel> getGroups() {
        return this.groups;
    }

    public void setGroups(List<GroupModel> groups) {
        this.groups = groups;
    }

    public List<UserModel> getFriends() {
        return this.friends;
    }

    public void setFriends(List<UserModel> friends) {
        this.friends = friends;
    }

    public List<PageModel> getPages() {
        return this.pages;
    }

    public void setPages(List<PageModel> pages) {
        this.pages = pages;
    }

    public List<AnswerModel> getAnswerModels() {
        return this.answerModels;
    }

    public void setAnswerModels(List<AnswerModel> answerModels) {
        this.answerModels = answerModels;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<MessageModel> getMessages() {
        return this.messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
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