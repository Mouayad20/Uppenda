package com.example.demo.Controllers;

import com.example.demo.Converters.QuestionConverter;
import com.example.demo.Entities.QuestionEntity;
import com.example.demo.Models.QuestionModel;
import com.example.demo.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionConverter questionConverter;

    /* Get Request */

    @GetMapping("/getFormat")
    public QuestionModel getFormat() {
        return new QuestionModel();
    }

    @GetMapping("/getAllQuestion")
    public List<QuestionModel> fetchAllQuestion() {
        List<QuestionModel> list = new ArrayList<>();
        for (QuestionEntity questionEntity : questionRepository.findAll()) {
            list.add(questionConverter.questionEntityToModel(questionEntity));
        }
        return list;
    }

}
