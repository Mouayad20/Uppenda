package com.example.demo.Services;

import com.example.demo.Converters.AnswerConverter;
import com.example.demo.Converters.QuestionConverter;
import com.example.demo.Entities.AnswersEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.AnswerModel;
import com.example.demo.Repositories.AnswerRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AnswerConverter answerConverter;
    @Autowired
    private QuestionConverter questionConverter;
    @Autowired
    private TokenUtil tokenUtil;

    public List<AnswerModel> addAnswers(List<AnswerModel> answerModels, String token) {
        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();
        List<AnswersEntity> list = new ArrayList<>();
        for (int i = 0; i < answerModels.size(); i++) {
            AnswersEntity answersEntity = new AnswersEntity();
            answersEntity.setAnswer(answerModels.get(i).getAnswer());
            answersEntity.setQuestion(questionConverter.questionModelToEntity(answerModels.get(i).getQuestion()));
            answersEntity.setUser(userEntity);
            list.add(answerRepository.save(answersEntity));
        }
        return answerConverter.answerListEntityToListModel(list);
    }

    public List<Boolean> checkAnswers(List<AnswerModel> answerModels, String token) {
        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();
        List<AnswersEntity> list = answerRepository.getAllAnswersForUserByUID(userEntity.getId());
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < answerModels.size(); i++) {
            if (list.get(i).getQuestion().getId() == answerModels.get(i).getQuestion().getId()) {
                if (list.get(i).getAnswer().equalsIgnoreCase(answerModels.get(i).getAnswer())) {
                    result.add(true);
                } else {
                    result.add(false);
                }
            }
        }
        return result;
    }
}