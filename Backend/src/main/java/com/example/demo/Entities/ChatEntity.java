package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chats")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ChatEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private boolean isHidden1;
    private boolean isHidden2;
    @OneToOne
    private GroupEntity groupEntity;

    @OneToMany(mappedBy = "chatEntity", cascade = CascadeType.ALL)
    private List<MessageEntity> messages;

    @ManyToOne()
    private UserEntity user1;

    @ManyToOne()
    private UserEntity user2;



    public ChatEntity() {
    }

    public ChatEntity(Long id,boolean isHidden1,boolean isHidden2, GroupEntity groupEntity, List<MessageEntity> messages, UserEntity user1, UserEntity user2) {
        this.id = id;
        this.isHidden1 = isHidden1;
        this.isHidden2 = isHidden2;
        this.groupEntity = groupEntity;
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

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public UserEntity getUser1() {
        return user1;
    }

    public void setUser1(UserEntity user1) {
        this.user1 = user1;
    }

    public UserEntity getUser2() {
        return user2;
    }

    public void setUser2(UserEntity user2) {
        this.user2 = user2;
    }
}
