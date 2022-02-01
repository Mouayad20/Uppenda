package com.example.demo.Controllers;

import java.util.List;

import com.example.demo.Converters.ReactionConverter;
import com.example.demo.Models.ReactionModel;
import com.example.demo.Repositories.ReactionsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path  = "/typeReaction")
public class ReationController {

    @Autowired
    ReactionsRepository reactionsRepository;

    @Autowired
    ReactionConverter reactionConverter;

    @GetMapping("/getAllTypes") 
    public List<ReactionModel> getAllTypes(){
        return reactionConverter.reactionListEntityToModel(reactionsRepository.findAll());
    }
    
}
