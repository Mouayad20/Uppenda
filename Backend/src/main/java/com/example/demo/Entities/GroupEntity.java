package com.example.demo.Entities;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String imagePath;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    ///////////////////

    @OneToMany(mappedBy = "groupEntity", cascade = CascadeType.ALL)
    private List<PostEntity> postEntities;

    @ManyToOne()
    private UserEntity admin;

    @ManyToMany(mappedBy = "groups")
    private List<UserEntity> members;

    public GroupEntity() {
    }

    public GroupEntity(long id, String name, String description, Date createdAt, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.imagePath = imagePath;
    }

    public List<PostEntity> getPostEntities() {
        return this.postEntities;
    }

    public void setPostEntities(List<PostEntity> postEntities) {
        this.postEntities = postEntities;
    }

    public UserEntity getAdmin() {
        return this.admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }

    public List<UserEntity> getMembers() {
        return this.members;
    }

    public void setMembers(List<UserEntity> members) {
        this.members = members;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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


}
