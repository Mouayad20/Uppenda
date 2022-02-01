package com.example.demo.Converters;

import com.example.demo.Entities.LikeEntity;
import com.example.demo.Models.LikeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeConverter {

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private ReactionConverter reactionConverter;


    public LikeEntity likeModelToEntity(LikeModel likeModel) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setId(likeModel.getId());
        likeEntity.setPostEntity(postConverter.postModelToEntity(likeModel.getPostModel()));
        likeEntity.setUserEntity(userConverter.convertUserModelToUserEntity(likeModel.getUserModel(), true));
        likeEntity.setReaction(reactionConverter.reactionModelToEntity(likeModel.getReactionModel()));
        return likeEntity;
    }

    public LikeModel likeEntityToModel(LikeEntity likeEntity, boolean withPost, boolean withUser) {
        LikeModel likeModel = new LikeModel();
        likeModel.setId(likeEntity.getId());
        likeModel.setReactionModel(reactionConverter.reactionEntityToModel(likeEntity.getReaction()));
        if (withPost)
            likeModel.setPostModel(postConverter.postEntityToModel(likeEntity.getPostEntity(), false, false, false, false, false));
        if (withUser)
            likeModel.setUserModel(userConverter.convertUserEntityToUserModel(likeEntity.getUserEntity()));
        return likeModel;
    }

    public List<LikeModel> likeEntityListToModelList(List<LikeEntity> likeEntities) {
        List<LikeModel> list = new ArrayList<>();
        for (LikeEntity likeEntity : likeEntities) {
            list.add(likeEntityToModel(likeEntity, false, true));
        }
        return list;
    }

    public List<LikeEntity> likeModelListToEntityList(List<LikeModel> likeModels) {
        List<LikeEntity> list = new ArrayList<>();
        if (likeModels != null)
            for (LikeModel likeModel : likeModels) {
                list.add(likeModelToEntity(likeModel));
            }
        return list;
    }

}
