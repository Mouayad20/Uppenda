package com.example.demo.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;

@Entity
@Table(name = "pages")
public class PageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String descreption;
    private String imgPath;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    ///////////////////////
    
    @OneToMany(mappedBy = "pageEntity",cascade = CascadeType.ALL)
    private List<PostEntity> postEntities;

    ////////////////

    @ManyToOne()
    private UserEntity admin;

    @ManyToMany(mappedBy = "pages")
    private List<UserEntity> members;

    /////////////////////////


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

    public String getDescreption() {
        return this.descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
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
    @Override
    public String toString() {
        try {
            return DemoApplication.objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
    
}
