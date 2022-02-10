package com.example.demo.Models;


import java.util.Date;

public class MessageModel {

    private Long id;
    private String content ;
    private Date   dateOfSent  ;
    private Long s_id;
    private Long c_id;
    private ChatModel chatModel ;
    private UserModel sender ;


    public MessageModel() {
    }

    public MessageModel(String content , Date dateOfSent){
        this.content = content ;
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

    public Long getS_id() {
        return s_id;
    }

    public void setS_id(Long s_id) {
        this.s_id = s_id;
    }

    public Long getC_id() {
        return c_id;
    }

    public void setC_id(Long c_id) {
        this.c_id = c_id;
    }

    @Override
    public String toString() {
        return "{\"id\":"+getId()+"" +
                ",\"content\":\""+getContent()+"\"" +
                ",\"dateOfSent\":\""+getDateOfSent()+"\"" +
                ",\"s_id\":\""+getS_id()+"\""+
                ",\"sender\":\""+getSender()+"\""+
                ",\"c_id\":"+getC_id()+"}";


    }


   
}
