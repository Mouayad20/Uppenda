package com.example.demo.Controllers;

import com.example.demo.Models.AnswerModel;
import com.example.demo.Services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    /* Post Request */

    @PostMapping("/addAnswers")
    public List<AnswerModel> addAnswers(@RequestBody List<AnswerModel> answerModels,
                                        @RequestHeader("Authorization") String token) {
        return answerService.addAnswers(answerModels, token.substring("Bearer ".length()));
    }

    @PostMapping("/checkAnswers")
    public List<Boolean> checkAnswers(@RequestBody List<AnswerModel> answerModels,
                                      @RequestHeader("Authorization") String token) {
        return answerService.checkAnswers(answerModels, token.substring("Bearer ".length()));
    }

    /* Get Request */

    @GetMapping("/getFormat")
    public AnswerModel getFormat() {
        return new AnswerModel();
    }

}
