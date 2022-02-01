package com.example.demo.Converters;

import com.example.demo.Entities.ReactionEntity;
import com.example.demo.Models.ReactionModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReactionConverter {

    public ReactionEntity reactionModelToEntity(ReactionModel reactionModel) {
        ReactionEntity reactionsEntity = new ReactionEntity();
        reactionsEntity.setId(reactionModel.getId());
        reactionsEntity.setReactionType(reactionModel.getReactionType());
        reactionsEntity.setColorName(reactionModel.getColorName());
        return reactionsEntity;
    }

    public ReactionModel reactionEntityToModel(ReactionEntity reactionsEntity) {
        ReactionModel reactionModel = new ReactionModel();
        reactionModel.setId(reactionsEntity.getId());
        reactionModel.setReactionType(reactionsEntity.getReactionType());
        reactionModel.setColorName(reactionsEntity.getColorName());
        return reactionModel;
    }

    public List<ReactionModel> reactionListEntityToModel(Iterable<ReactionEntity> rIterable) {
        List<ReactionModel> reactionModels = new ArrayList<>();
        for (ReactionEntity reactionEntity : rIterable) {
            reactionModels.add(reactionEntityToModel(reactionEntity));
        }
        return reactionModels;
    }

}
