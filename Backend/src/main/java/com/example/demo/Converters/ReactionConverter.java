package com.example.demo.Converters;

import com.example.demo.Entities.ReactionEntity;
import com.example.demo.Models.ReactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReactionConverter {

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private ReactionTypeConverter reactionTypeConverter;


    public ReactionEntity reactionModelToEntity(ReactionModel reactionModel) {
        ReactionEntity reactionEntity = new ReactionEntity();
        reactionEntity.setId(reactionModel.getId());
        reactionEntity.setPostEntity(postConverter.postModelToEntity(reactionModel.getPostModel()));
        reactionEntity.setUserEntity(userConverter.convertUserModelToUserEntity(reactionModel.getUserModel(), true));
        reactionEntity.setReaction(reactionTypeConverter.reactionTypeModelToEntity(reactionModel.getReactionTypeModel()));
        return reactionEntity;
    }

    public ReactionModel reactionEntityToModel(ReactionEntity reactionEntity, boolean withPost, boolean withUser) {
        ReactionModel reactionModel = new ReactionModel();
        reactionModel.setId(reactionEntity.getId());
        reactionModel.setReactionTypeModel(reactionTypeConverter.reactionTypeEntityToModel(reactionEntity.getReaction()));
        if (withPost)
            reactionModel.setPostModel(postConverter.postEntityToModel(reactionEntity.getPostEntity(), false, false, false, false, false));
        if (withUser)
            reactionModel.setUserModel(userConverter.convertUserEntityToUserModel(reactionEntity.getUserEntity()));
        return reactionModel;
    }

    public List<ReactionModel> reactionEntityListToModelList(List<ReactionEntity> reactionEntities) {
        List<ReactionModel> list = new ArrayList<>();
        for (ReactionEntity reactionEntity : reactionEntities) {
            list.add(reactionEntityToModel(reactionEntity, false, true));
        }
        return list;
    }

    public List<ReactionEntity> reactionModelListToEntityList(List<ReactionModel> reactionModels) {
        List<ReactionEntity> list = new ArrayList<>();
        if (reactionModels != null)
            for (ReactionModel reactionModel : reactionModels) {
                list.add(reactionModelToEntity(reactionModel));
            }
        return list;
    }

}
