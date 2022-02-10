package com.example.demo.Models;

import java.util.List;
import java.util.Objects;

public class ChatModel {

    private Long id;
    private String tittleGroup;
    private String imageGroup;
    private MessageModel lastMessage;
    private UserModel receiver;
    private List<UserModel> users;
    private List<MessageModel> messages;

    private List<UserModel> usersHiddenChats;

    public ChatModel() {
        this.id = null;
        this.tittleGroup = null;
        this.imageGroup = null;
        this.users = null;
        this.usersHiddenChats = null;
        this.messages = null;
    }

    public ChatModel(Long id, String tittleGroup, String imageGroup, List<UserModel> users,
            List<UserModel> usersHiddenChats, List<MessageModel> messages) {
        this.id = id;
        this.tittleGroup = tittleGroup;
        this.imageGroup = imageGroup;
        this.users = users;
        this.usersHiddenChats = usersHiddenChats;
        this.messages = messages;
    }

    public UserModel getReceiver() {
        return receiver;
    }

    public void setReceiver(UserModel receiver) {
        this.receiver = receiver;
    }

    public MessageModel getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageModel lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittleGroup() {
        return tittleGroup;
    }

    public void setTittleGroup(String tittleGroup) {
        this.tittleGroup = tittleGroup;
    }

    public String getImageGroup() {
        return imageGroup;
    }

    public void setImageGroup(String imageGroup) {
        this.imageGroup = imageGroup;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public List<UserModel> getUsersHiddenChats() {
        return usersHiddenChats;
    }

    public void setUsersHiddenChats(List<UserModel> usersHiddenChats) {
        this.usersHiddenChats = usersHiddenChats;
    }

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ChatModel)) {
            return false;
        }
        ChatModel chatModel = (ChatModel) o;
        return Objects.equals(id, chatModel.id) && Objects.equals(tittleGroup, chatModel.tittleGroup)
                && Objects.equals(imageGroup, chatModel.imageGroup)
                && Objects.equals(lastMessage, chatModel.lastMessage) && Objects.equals(receiver, chatModel.receiver)
                && Objects.equals(users, chatModel.users) && Objects.equals(messages, chatModel.messages)
                && Objects.equals(usersHiddenChats, chatModel.usersHiddenChats);
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", tittleGroup='" + tittleGroup + '\'' + ", imageGroup='" + imageGroup + '\''
                + ", lastMessage=" + lastMessage + '}';
    }
}
