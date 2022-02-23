package com.example.demo.Controllers;

import com.example.demo.Models.ReactionModel;
import com.example.demo.Services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/reaction")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping("/add/postId={post_id},reactionId={reaction_id}")
    public ResponseEntity<String> add(@PathVariable(name = "post_id", required = true) Long post_id,
                                      @RequestHeader("Authorization") String token,
                                      @PathVariable(name = "reaction_id", required = true) Long reaction_id) {
        return reactionService.add(post_id, token.substring("Bearer ".length()), reaction_id);
    }

    @PostMapping("/remove/reactionId={reaction_id}")
    public ResponseEntity<String> remove(@PathVariable(name = "reaction_id", required = true) Long reaction_id) {
        return reactionService.remove(reaction_id);
    }

    @GetMapping("/getReactionsPost/{post_id}")
    public List<ReactionModel> getReactionsPost(@PathVariable(name = "post_id", required = true) Long post_id) {
        return reactionService.getReactionsPost(post_id);
    }

}
