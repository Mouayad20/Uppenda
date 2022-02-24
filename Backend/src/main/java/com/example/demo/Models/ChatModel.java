package com.example.demo.Models;

import java.util.List;

public class ChatModel {

    private Long id;
    private boolean isHidden1;
    private boolean isHidden2;
    private GroupModel groupModel;
    private List<MessageModel> messages;
    private UserModel user1;
    private UserModel user2;

    public ChatModel() {
    }

    public ChatModel(Long id, boolean isHidden1,boolean isHidden2, GroupModel groupModel, List<MessageModel> messages, UserModel user1, UserModel user2) {
        this.id = id;
        this.isHidden1 = isHidden1;
        this.isHidden2 = isHidden2;
        this.groupModel = groupModel;
        this.messages = messages;
        this.user1 = user1;
        this.user2 = user2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isHidden1() {
        return isHidden1;
    }

    public void setHidden1(boolean hidden1) {
        isHidden1 = hidden1;
    }

    public boolean isHidden2() {
        return isHidden2;
    }

    public void setHidden2(boolean hidden2) {
        isHidden2 = hidden2;
    }

    public GroupModel getGroupModel() {
        return groupModel;
    }

    public void setGroupModel(GroupModel groupModel) {
        this.groupModel = groupModel;
    }

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

    public UserModel getUser1() {
        return user1;
    }

    public void setUser1(UserModel user1) {
        this.user1 = user1;
    }

    public UserModel getUser2() {
        return user2;
    }

    public void setUser2(UserModel user2) {
        this.user2 = user2;
    }
}
