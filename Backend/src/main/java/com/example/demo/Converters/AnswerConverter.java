package com.example.demo.Converters;

import com.example.demo.Entities.AnswersEntity;
import com.example.demo.Models.AnswerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerConverter {

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private QuestionConverter questionConverter;


    public AnswersEntity answersModelToEntity(AnswerModel answerModel) {
        AnswersEntity answersEntity = new AnswersEntity();
        answersEntity.setId(answerModel.getId());
        answersEntity.setQuestion(questionConverter.questionModelToEntity(answerModel.getQuestion()));
        answersEntity.setAnswer(answerModel.getAnswer());
        answersEntity.setUser(userConverter.convertUserModelToUserEntity(answerModel.getUser(), true));
        return answersEntity;
    }

    public AnswerModel answersEntityToModel(AnswersEntity answersEntity) {
        AnswerModel answerModel = new AnswerModel();
        answerModel.setId(answersEntity.getId());
        answerModel.setQuestion(questionConverter.questionEntityToModel(answersEntity.getQuestion()));
        answerModel.setAnswer(answersEntity.getAnswer());
        // answerModel.setUser(getUserModelWithBasicInformation(answersEntity.getUser()));
        return answerModel;
    }

    public List<AnswersEntity> answerListModelToListEntity(List<AnswerModel> answerModels) {
        List<AnswersEntity> list = new ArrayList<AnswersEntity>();
        if (answerModels != null)
            for (AnswerModel answerModel : answerModels) {
                list.add(answersModelToEntity(answerModel));
            }
        return list;
    }

    public List<AnswerModel> answerListEntityToListModel(List<AnswersEntity> answersEntities) {
        List<AnswerModel> list = new ArrayList<AnswerModel>();
        if (answersEntities != null)
            for (AnswersEntity answersEntity : answersEntities) {
                list.add(answersEntityToModel(answersEntity));
            }
        return list;
    }

}
