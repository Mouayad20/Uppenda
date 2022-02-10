package com.example.demo.Converters;

import com.example.demo.Entities.ReactionTypeEntity;
import com.example.demo.Models.ReactionTypeModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReactionTypeConverter {

    public ReactionTypeEntity reactionTypeModelToEntity(ReactionTypeModel reactionTypeModel) {
        ReactionTypeEntity reactionTypeEntity = new ReactionTypeEntity();
        reactionTypeEntity.setId(reactionTypeModel.getId());
        reactionTypeEntity.setReactionType(reactionTypeModel.getReactionType());
        reactionTypeEntity.setColorName(reactionTypeModel.getColorName());
        return reactionTypeEntity;
    }

    public ReactionTypeModel reactionTypeEntityToModel(ReactionTypeEntity reactionTypeEntity) {
        ReactionTypeModel reactionTypeModel = new ReactionTypeModel();
        reactionTypeModel.setId(reactionTypeEntity.getId());
        reactionTypeModel.setReactionType(reactionTypeEntity.getReactionType());
        reactionTypeModel.setColorName(reactionTypeEntity.getColorName());
        return reactionTypeModel;
    }

    public List<ReactionTypeModel> reactionTypeListEntityToModel(Iterable<ReactionTypeEntity> rIterable) {
        List<ReactionTypeModel> reactionTypeModels = new ArrayList<>();
        for (ReactionTypeEntity reactionEntity : rIterable) {
            reactionTypeModels.add(reactionTypeEntityToModel(reactionEntity));
        }
        return reactionTypeModels;
    }

}
