package com.example.demo.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;

@Entity
@Table( name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserEntity {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String mobile;
    private String studyLevel;
    private String location;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date age;
    private String gender;
    boolean onLine;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    private String imagePath;
    private String ip;

    //////////////////////////////////////////////

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL )
    private List<PostEntity> PostEntity;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL )
    private List<CommentEntity> commentEntities;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL )
    private List<LikeEntity> likeEntities;

    @ManyToMany()
    private List<PostEntity> savedPost ;

    @ManyToMany()
    private List<PostEntity> sharedPost ;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<ChatEntity> chats ;

    @ManyToMany(cascade = CascadeType.ALL )
    private List<ChatEntity> hiddenChats ;

    @OneToMany(mappedBy = "sender",cascade = CascadeType.ALL)
    private List<MessageEntity> messages ;

    ////////

    @ManyToMany()
    private List<UserEntity> friends;

    @ManyToMany(/*  mappedBy = "members" */cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<GroupEntity> groups;

    @ManyToMany(/* mappedBy = "members" */cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<PageEntity> pages;

    /////////////////////////////////////////////////////

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<InterstEntity> interst;

    /////////////////////////////////////////////////////

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<AnswersEntity> answersEntities;

    /////////////////////////////////////////////////////


    public UserEntity() {
    }

    public UserEntity(Long id, String firstName, String lastName, String email, String mobile, String studyLevel, String location, Date age, String gender, boolean onLine, String password, Date createdAt, String imagePath, List<PostEntity> PostEntity, List<CommentEntity> commentEntities, List<LikeEntity> likeEntities, List<PostEntity> savedPost, List<PostEntity> sharedPost, List<UserEntity> friends, List<GroupEntity> groups, List<PageEntity> pages, List<InterstEntity> interst, List<AnswersEntity> answersEntities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.studyLevel = studyLevel;
        this.location = location;
        this.age = age;
        this.gender = gender;
        this.onLine = onLine;
        this.password = password;
        this.createdAt = createdAt;
        this.imagePath = imagePath;
        this.PostEntity = PostEntity;
        this.commentEntities = commentEntities;
        this.likeEntities = likeEntities;
        this.savedPost = savedPost;
        this.sharedPost = sharedPost;
        this.friends = friends;
        this.groups = groups;
        this.pages = pages;
        this.interst = interst;
        this.answersEntities = answersEntities;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Date getCreatedAt() {
        return this.createdAt;
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

    public List<PostEntity> getPostEntity() {
        return this.PostEntity;
    }

    public void setPostEntity(List<PostEntity> PostEntity) {
        this.PostEntity = PostEntity;
    }

    public List<CommentEntity> getCommentEntities() {
        return this.commentEntities;
    }

    public void setCommentEntities(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }

    public List<LikeEntity> getLikeEntities() {
        return this.likeEntities;
    }

    public void setLikeEntities(List<LikeEntity> likeEntities) {
        this.likeEntities = likeEntities;
    }

    public List<PostEntity> getSavedPost() {
        return this.savedPost;
    }

    public void setSavedPost(List<PostEntity> savedPost) {
        this.savedPost = savedPost;
    }

    public List<PostEntity> getSharedPost() {
        return this.sharedPost;
    }

    public void setSharedPost(List<PostEntity> sharedPost) {
        this.sharedPost = sharedPost;
    }

    public List<UserEntity> getFriends() {
        return this.friends;
    }

    public void setFriends(List<UserEntity> friends) {
        this.friends = friends;
    }

    public List<GroupEntity> getGroups() {
        return this.groups;
    }

    public void setGroups(List<GroupEntity> groups) {
        this.groups = groups;
    }

    public List<PageEntity> getPages() {
        return this.pages;
    }

    public void setPages(List<PageEntity> pages) {
        this.pages = pages;
    }

    public List<InterstEntity> getInterst() {
        return this.interst;
    }

    public void setInterst(List<InterstEntity> interst) {
        this.interst = interst;
    }

    public List<AnswersEntity> getAnswersEntities() {
        return this.answersEntities;
    }

    public void setAnswersEntities(List<AnswersEntity> answersEntities) {
        this.answersEntities = answersEntities;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<ChatEntity> getChats() {
        return chats;
    }

    public void setChats(List<ChatEntity> chats) {
        this.chats = chats;
    }

    public List<ChatEntity> getHiddenChats() {
        return hiddenChats;
    }

    public void setHiddenChats(List<ChatEntity> hiddenChats) {
        this.hiddenChats = hiddenChats;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
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
