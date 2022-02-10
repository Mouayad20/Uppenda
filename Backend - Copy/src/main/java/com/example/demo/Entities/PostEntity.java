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
@Table(name = "post")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PostEntity {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id ;
    private String content ;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt ;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "u_id")
    private UserEntity userEntity ;

    @OneToMany(mappedBy = "postEntity",cascade = CascadeType.ALL )
    private List<CommentEntity> commentEntities;

    @OneToMany(mappedBy = "postEntity",cascade = CascadeType.ALL )
    private List<LikeEntity> likeEntities;

    @OneToMany(mappedBy = "postEntity",cascade = CascadeType.ALL )
    List<MediaEntity> media;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "g_id")
    private GroupEntity groupEntity ;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "page_id")
    private PageEntity pageEntity ;

    @ManyToMany(mappedBy = "savedPost")
    private List<UserEntity> savers;

    @ManyToMany(mappedBy = "sharedPost")
    private List<UserEntity> participants;

    @OneToOne
    private TypeEntity type;



    public PostEntity() {
    }

    public PostEntity(Long id, String content, Date createdAt, UserEntity userEntity, List<CommentEntity> commentEntities, List<LikeEntity> likeEntities, GroupEntity groupEntity, PageEntity pageEntity, List<UserEntity> savers, List<UserEntity> participants) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.userEntity = userEntity;
        this.commentEntities = commentEntities;
        this.likeEntities = likeEntities;
        this.groupEntity = groupEntity;
        this.pageEntity = pageEntity;
        this.savers = savers;
        this.participants = participants;
    }

    public List<MediaEntity> getMedia() {
        return media;
    }

    public void setMedia(List<MediaEntity> media) {
        this.media = media;
    }

    public TypeEntity getType() {
        return type;
    }

    public void setType(TypeEntity type) {
        this.type = type;
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

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getUserEntity() {
        return this.userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

    public GroupEntity getGroupEntity() {
        return this.groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }

    public PageEntity getPageEntity() {
        return this.pageEntity;
    }

    public void setPageEntity(PageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }

    public List<UserEntity> getSavers() {
        return this.savers;
    }

    public void setSavers(List<UserEntity> savers) {
        this.savers = savers;
    }

    public List<UserEntity> getParticipants() {
        return this.participants;
    }

    public void setParticipants(List<UserEntity> participants) {
        this.participants = participants;
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
