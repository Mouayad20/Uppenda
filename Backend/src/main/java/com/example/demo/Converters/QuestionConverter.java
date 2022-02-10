package com.example.demo.Converters;

import com.example.demo.Entities.QuestionEntity;
import com.example.demo.Models.QuestionModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionConverter {


    public QuestionEntity questionModelToEntity(QuestionModel questionModel) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(questionModel.getId());
        questionEntity.setQuestion(questionModel.getQuestion());
        return questionEntity;
    }

    public QuestionModel questionEntityToModel(QuestionEntity questionEntity) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setId(questionEntity.getId());
        questionModel.setQuestion(questionEntity.getQuestion());
        return questionModel;
    }

    public List<QuestionEntity> questionListModelToListEntity(List<QuestionModel> questionModels) {
        List<QuestionEntity> list = new ArrayList<QuestionEntity>();
        if (questionModels != null)
            for (QuestionModel questionModel : questionModels) {
                list.add(questionModelToEntity(questionModel));
            }
        return list;
    }

    public List<QuestionModel> questionListEntityToListModel(List<QuestionEntity> questionEntities) {
        List<QuestionModel> list = new ArrayList<QuestionModel>();
        if (questionEntities != null)
            for (QuestionEntity questionEntity : questionEntities) {
                list.add(questionEntityToModel(questionEntity));
            }
        return list;
    }

}
