package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entities.QuestionEntity;
import com.example.demo.Models.QuestionModel;
import com.example.demo.Repositories.QuestionRepository;
import com.example.demo.services.FormatFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    FormatFactory formatFactory;

    @GetMapping("/getAllQuestion")
    public List<QuestionModel> fetchAllQuestion(){
        List<QuestionModel> list = new ArrayList<>();
        for (QuestionEntity questionEntity : questionRepository.findAll()) {
            list.add(formatFactory.questionEntityToModel(questionEntity));
        }
        return list;
    }

    @GetMapping("/getFormat")
    public QuestionModel getFormat(){
        return new QuestionModel();
    }

    
}
