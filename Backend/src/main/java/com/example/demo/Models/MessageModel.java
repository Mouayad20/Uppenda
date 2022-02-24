package com.example.demo.Models;


import java.util.Date;

public class MessageModel {

    private Long id;
    private String content;
    private Date dateOfSent;
    private ChatModel chatModel;
    private UserModel sender;

    public MessageModel() {
        this.dateOfSent = new Date();
    }

    public MessageModel(String content, Date dateOfSent) {
        this.content = content;
        this.dateOfSent = dateOfSent;
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

    public Date getDateOfSent() {
        return dateOfSent;
    }

    public void setDateOfSent(Date dateOfSent) {
        this.dateOfSent = dateOfSent;
    }

    public ChatModel getChatModel() {
        return chatModel;
    }

    public void setChatModel(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public UserModel getSender() {
        return sender;
    }

    public void setSender(UserModel sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "{\"id\":" + getId() + "" +
                ",\"content\":\"" + getContent() + "\"" +
                ",\"dateOfSent\":\"" + getDateOfSent() + "\"" +
                ",\"sender\":\"" + getSender() + "}";
    }
}