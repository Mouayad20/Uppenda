package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pages")
public class PageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String imgPath;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    //////////////////////////////////////////////
    //////////////// OneToMany  //////////////////
    //////////////////////////////////////////////

    @OneToMany(mappedBy = "pageEntity", cascade = CascadeType.ALL)
    private List<PostEntity> postEntities;

    //////////////////////////////////////////////
    //////////////// ManyToOne  //////////////////
    //////////////////////////////////////////////

    @ManyToOne()
    private UserEntity admin;

    //////////////////////////////////////////////
    //////////////// ManyToMany  /////////////////
    //////////////////////////////////////////////

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<UserEntity> members;

    //////////////////////////////////////////////
    /////////// Getters & Setters  ///////////////
    //////////////////////////////////////////////

    public List<PostEntity> getPostEntities() {
        return this.postEntities;
    }

    public void setPostEntities(List<PostEntity> postEntities) {
        this.postEntities = postEntities;
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

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return this.imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public UserEntity getAdmin() {
        return this.admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }

}
