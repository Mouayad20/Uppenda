package com.example.demo.Controllers;

import com.example.demo.Models.ReactionModel;
import com.example.demo.Services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/reaction")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping("/add/p={post_id}/u={u_id}/r={reaction_id}")
    public ReactionModel add(@PathVariable(name = "post_id", required = true) Long post_id,
                              @PathVariable(name = "u_id", required = true) Long u_id,
                              @PathVariable(name = "reaction_id", required = true) Long reaction_id) {
        return reactionService.add(post_id, u_id, reaction_id);
    }

    @PostMapping("/remove/r_id={reaction_id}")
    public String remove(
            @PathVariable(name = "reaction_id", required = true) Long reaction_id) {
        return reactionService.remove(reaction_id);
    }

    @GetMapping("/getAllReactionForPost/{post_id}")
    public List<ReactionModel> getAllReactionForPost(@PathVariable(name = "post_id", required = true) Long post_id) {
        return reactionService.getAllReactionForPost(post_id);
    }

}
