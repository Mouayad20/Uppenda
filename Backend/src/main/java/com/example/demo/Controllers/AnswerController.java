package com.example.demo.Controllers;

import com.example.demo.Converters.AnswerConverter;
import com.example.demo.Converters.UserConverter;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.AnswerModel;
import com.example.demo.Repositories.AnswerRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/answer")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private AnswerConverter answerConverter;

    /* Post Request */

    @PostMapping("/addAnswers/user_id={u_id}")
    public List<AnswerModel> addAnswers(@RequestBody List<AnswerModel> answerModels,
                                        @PathVariable(name = "u_id") Long u_id) {
        List<AnswerModel> list = new ArrayList<AnswerModel>();
        UserEntity userEntity = userRepository.findById(u_id).get();
        for (int i = 0; i < answerModels.size(); i++) {
            answerModels.get(i).setUser(userConverter.convertUserEntityToUserModel(userEntity));
            list.add(answerConverter.answersEntityToModel(
                    answerRepository.save(answerConverter.answersModelToEntity(answerModels.get(i)))));
        }
        return list;
    }

    @PostMapping("/checkAnswers/user_id={u_id}")
    public List<Boolean> checkAnswers(@RequestBody List<AnswerModel> answerModels,
                                      @PathVariable(name = "u_id") Long u_id) {
        List<AnswerModel> list = answerConverter
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

    /* Get Request */

    @GetMapping("/getFormat")
    public AnswerModel getFormat() {
        return new AnswerModel();
    }

}
