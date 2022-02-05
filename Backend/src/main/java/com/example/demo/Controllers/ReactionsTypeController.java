package com.example.demo.Controllers;

import com.example.demo.Converters.ReactionTypeConverter;
import com.example.demo.Models.ReactionTypeModel;
import com.example.demo.Repositories.ReactionsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/typeReaction")
public class ReactionsTypeController {

    @Autowired
    private ReactionsTypeRepository reactionsTypeRepository;
    @Autowired
    private ReactionTypeConverter reactionTypeConverter;

    /* Get Request */

    @GetMapping("/getAllTypes")
    public List<ReactionTypeModel> getAllTypes() {
        return reactionTypeConverter.reactionTypeListEntityToModel(reactionsTypeRepository.findAll());
    }

}
