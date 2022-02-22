package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserEntity {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String mobile;
    private String studyLevel;
    private String location;
    private String gender;
    private String imagePath;
    private String ip;
    private boolean onLine;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    //////////////////////////////////////////////
    ////////////////// OneToMany /////////////////
    //////////////////////////////////////////////


    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<PostEntity> postEntities;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<CommentEntity> commentEntities;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<ReactionEntity> reactionEntities;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<MessageEntity> messages;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AnswersEntity> answersEntities;
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<PageEntity> myPages;
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<GroupEntity> myGroups;

    //////////////////////////////////////////////
    //////////////// ManyToMany //////////////////
    //////////////////////////////////////////////

    @ManyToMany()
    private List<UserEntity> friends;
    @ManyToMany(mappedBy = "savers")
    private List<PostEntity> savedPost;
    @ManyToMany(mappedBy = "participants")
    private List<PostEntity> sharedPost;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ChatEntity> chats;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ChatEntity> hiddenChats;
    @ManyToMany(mappedBy = "members")
    private List<GroupEntity> groups;
    @ManyToMany(mappedBy = "members")
    private List<PageEntity> pages;

    //////////////////////////////////////////////
    //////////////// Constructors ////////////////
    //////////////////////////////////////////////

    public UserEntity() {
    }

    public UserEntity(Long id,
                      String firstName,
                      String lastName,
                      String email,
                      String mobile,
                      String studyLevel,
                      String location,
                      Date age,
                      String gender,
                      boolean onLine,
                      String password,
                      Date createdAt,
                      String imagePath,
                      List<PostEntity> postEntities,
                      List<CommentEntity> commentEntities,
                      List<ReactionEntity> reactionEntities,
                      List<PostEntity> savedPost,
                      List<PostEntity> sharedPost,
                      List<UserEntity> friends,
                      List<GroupEntity> groups,
                      List<PageEntity> pages,
                      List<AnswersEntity> answersEntities,
                      List<PageEntity> myPages,
                      List<GroupEntity> myGroups) {
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
        this.postEntities = postEntities;
        this.commentEntities = commentEntities;
        this.reactionEntities = reactionEntities;
        this.savedPost = savedPost;
        this.sharedPost = sharedPost;
        this.friends = friends;
        this.groups = groups;
        this.pages = pages;
        this.answersEntities = answersEntities;
        this.myPages = myPages;
        this.myGroups = myGroups;
    }

    //////////////////////////////////////////////
    ////////////// Getter & Setter ///////////////
    //////////////////////////////////////////////

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

    public List<PostEntity> getPostEntities() {
        return postEntities;
    }

    public void setPostEntities(List<PostEntity> postEntities) {
        this.postEntities = postEntities;
    }

    public List<CommentEntity> getCommentEntities() {
        return this.commentEntities;
    }

    public void setCommentEntities(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }

    public List<ReactionEntity> getReactionEntities() {
        return reactionEntities;
    }

    public void setReactionEntities(List<ReactionEntity> reactionEntities) {
        this.reactionEntities = reactionEntities;
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

    public List<PageEntity> getMyPages() {
        return myPages;
    }

    public void setMyPages(List<PageEntity> myPages) {
        this.myPages = myPages;
    }

    public List<GroupEntity> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(List<GroupEntity> myGroups) {
        this.myGroups = myGroups;
    }
}
