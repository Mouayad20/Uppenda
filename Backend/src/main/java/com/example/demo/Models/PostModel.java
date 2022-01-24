package com.example.demo.Models;

import java.util.Date;
import java.util.List;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

public class PostModel {

    private Long id ;
    private String content ;
    private Date createdAt ;
    private UserModel userModel;
    private GroupModel groupModel ;
    private PageModel pageModel ;
    private TypeModel type;
    private List<LikeModel> likeModels;
    private List<CommentModel> commentModels;
    private List<MediaModel> media;
    private List<UserModel> savers;
    private List<UserModel> participants;

    public PostModel() {

    }

    public PostModel(Long id,String content, Date createdAt, UserModel userModel, List<CommentModel> commentModels, List<LikeModel> likeModels, GroupModel groupModel, PageModel pageModel, List<UserModel> savers, List<UserModel> participants) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.userModel = userModel;
        this.commentModels = commentModels;
        this.likeModels = likeModels;
        this.groupModel = groupModel;
        this.pageModel = pageModel;
        this.savers = savers;
        this.participants = participants;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<CommentModel> getCommentModels() {
        return commentModels;
    }

    public void setCommentModels(List<CommentModel> commentModels) {
        this.commentModels = commentModels;
    }

    public List<LikeModel> getLikeModels() {
        return likeModels;
    }

    public void setLikeModels(List<LikeModel> likeModels) {
        this.likeModels = likeModels;
    }

    public GroupModel getGroupModel() {
        return groupModel;
    }

    public void setGroupModel(GroupModel groupModel) {
        this.groupModel = groupModel;
    }

    public PageModel getPageModel() {
        return pageModel;
    }

    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }

    public List<UserModel> getSavers() {
        return savers;
    }

    public void setSavers(List<UserModel> savers) {
        this.savers = savers;
    }

    public List<UserModel> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserModel> participants) {
        this.participants = participants;
    }

    public TypeModel getType() {
        return type;
    }

    public void setType(TypeModel type) {
        this.type = type;
    }

    public List<MediaModel> getMedia() {
        return media;
    }

    public void setMedia(List<MediaModel> media) {
        this.media = media;
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
