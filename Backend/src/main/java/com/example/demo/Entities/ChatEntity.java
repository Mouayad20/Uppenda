package com.example.demo.Entities;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table( name = "chats")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
public class ChatEntity   {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String tittleGroup;
    private String imageGroup;


    @ManyToMany(mappedBy = "chats")
    private List<UserEntity>  users ;

    @ManyToMany(mappedBy = "hiddenChats")
    private List<UserEntity> usersHiddenChats ;

    @OneToMany(mappedBy = "chatEntity",cascade = CascadeType.ALL)
    private List<MessageEntity> messages ;

    public ChatEntity() {
        users = new ArrayList<>();
    }

    public ChatEntity(Long id, String tittleGroup, String imageGroup , List<UserEntity> users, List<UserEntity> usersHiddenChats, List<MessageEntity> messages) {
        this.id = id;
        this.tittleGroup = tittleGroup;
        this.imageGroup = imageGroup;
        this.users = users;
        this.usersHiddenChats = usersHiddenChats;
        this.messages = messages;
    }

    public  int giveMeLength(){
        return this.users.size();
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<UserEntity> getUsersHiddenChats() {
        return usersHiddenChats;
    }

    public void setUsersHiddenChats(List<UserEntity> usersHiddenChats) {
        this.usersHiddenChats = usersHiddenChats;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }
}
