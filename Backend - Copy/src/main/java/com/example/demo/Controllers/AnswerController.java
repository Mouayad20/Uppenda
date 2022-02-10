package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.AnswerModel;
import com.example.demo.Repositories.AnswerRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.services.FormatFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/answer")
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FormatFactory formatFactory;

    @PostMapping("/addAnswers/user_id={u_id}")
    public List<AnswerModel> addAnswers(@RequestBody List<AnswerModel> answerModels,
            @PathVariable(name = "u_id") Long u_id) {
        List<AnswerModel> list = new ArrayList<AnswerModel>();
        UserEntity userEntity = userRepository.findById(u_id).get();
        for (int i = 0; i < answerModels.size(); i++) {
            answerModels.get(i).setUser(formatFactory.convertUserEntityToUserModel(userEntity));
            list.add(formatFactory.answersEntityToModel(
                    answerRepository.save(formatFactory.answersModelToEntity(answerModels.get(i)))));
        }
        return list;
    }

    @PostMapping("/checkAnswers/user_id={u_id}")
    public List<Boolean> checkAnswers(@RequestBody List<AnswerModel> answerModels,
            @PathVariable(name = "u_id") Long u_id) {
        List<AnswerModel> list = formatFactory
                .answerListEntityToListModel(answerRepository.getAllAnswersForUserByUID(u_id));
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < answerModels.size(); i++) {
            if (list.get(i).getQuestion().getId() == answerModels.get(i).getQuestion().getId()) {
                if (list.get(i).getAnswer().equals(answerModels.get(i).getAnswer())) {
                    result.add(true);
                    System.out.println("true");
                } else {
                    result.add(false);
                    System.out.println("false");
                }
            }
        }
        return result;
    }

    @GetMapping("/getFormat")
    public AnswerModel getFormat() {
        return new AnswerModel();
    }

}
