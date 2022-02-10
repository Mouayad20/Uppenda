package com.example.demo.FilesMangment.controllers;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Model {
    private String name;
    private String ip;
    private String image;
    private String image_enc;
    private List<MultipartFile> doc;

    public List<MultipartFile> getDoc() {
        return this.doc;
    }

    public void setDoc(List<MultipartFile> doc) {
        this.doc = doc;
    }



    public Model() {
    }

    public Model(String name, String ip, String image, String image_enc) {
        this.name = name;
        this.ip = ip;
        this.image = image;
        this.image_enc = image_enc;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_enc() {
        return this.image_enc;
    }

    public void setImage_enc(String image_enc) {
        this.image_enc = image_enc;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", ip='" + getIp() + "'" +
            ", image='" + getImage() + "'" +
            ", image_enc='" + getImage_enc() + "'" +
            "}";
    }

    

    
}
