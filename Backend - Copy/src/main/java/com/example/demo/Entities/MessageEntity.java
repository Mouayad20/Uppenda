package com.example.demo.Entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table( name = "messages")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MessageEntity {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String content ;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date   dateOfSent  ;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatEntity chatEntity ;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender ;


    public MessageEntity() {
    }

    public MessageEntity(Long id, String content, Date dateOfSent, ChatEntity chatEntity, UserEntity sender) {
        this.id = id;
        this.content = content;
        this.dateOfSent = dateOfSent;
        this.chatEntity = chatEntity;
        this.sender = sender;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateOfSent() {
        return this.dateOfSent;
    }

    public void setDateOfSent(Date dateOfSent) {
        this.dateOfSent = dateOfSent;
    }

    public ChatEntity getChatEntity() {
        return this.chatEntity;
    }

    public void setChatEntity(ChatEntity chatEntity) {
        this.chatEntity = chatEntity;
    }

    public UserEntity getSender() {
        return this.sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", content='" + getContent() + "'" +
            ", dateOfSent='" + getDateOfSent() + "'" +
            ", chatEntity='" + getChatEntity() + "'" +
            ", sender='" + getSender() + "'" +
            "}";
    }


    


}
