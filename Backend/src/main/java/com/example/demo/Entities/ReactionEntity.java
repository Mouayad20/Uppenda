package com.example.demo.Entities;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReactionEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    @ManyToOne
    @JoinColumn(name = "u_id")
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name = "reaction_id")
    private ReactionTypeEntity reaction;

    public ReactionEntity() {
    }

    public ReactionEntity(Long id, PostEntity postEntity, UserEntity userEntity, ReactionTypeEntity reaction) {
        this.id = id;
        this.postEntity = postEntity;
        this.userEntity = userEntity;
        this.reaction = reaction;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostEntity getPostEntity() {
        return this.postEntity;
    }

    public void setPostEntity(PostEntity postEntity) {
        this.postEntity = postEntity;
    }

    public UserEntity getUserEntity() {
        return this.userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ReactionTypeEntity getReaction() {
        return this.reaction;
    }

    public void setReaction(ReactionTypeEntity reaction) {
        this.reaction = reaction;
    }

    @Override
    public String toString() {
        try {
            return DemoApplication.objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
        return null;
    }

}
