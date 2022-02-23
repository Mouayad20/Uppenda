package com.example.demo.Services;

import com.example.demo.Converters.ReactionConverter;
import com.example.demo.Entities.ReactionEntity;
import com.example.demo.Models.ReactionModel;
import com.example.demo.Repositories.PostRepository;
import com.example.demo.Repositories.ReactionRepository;
import com.example.demo.Repositories.ReactionsTypeRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReactionsTypeRepository reactionsTypeRepository;
    @Autowired
    private ReactionConverter reactionConverter;
    @Autowired
    private TokenUtil tokenUtil;

    public ResponseEntity<String> add(Long post_id, String token, Long reaction_id) {

        ReactionEntity reactionEntity = new ReactionEntity();

        reactionEntity.setPostEntity(postRepository.findById(post_id).get());
        reactionEntity.setUserEntity(userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get());
        reactionEntity.setReaction(reactionsTypeRepository.findById(reaction_id).get());

        ReactionEntity savedReaction = reactionRepository.save(reactionEntity);

        if (reactionRepository.findById(savedReaction.getId()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Reaction added successfully");
        } else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
    }

    public ResponseEntity<String> remove(Long id) {
        reactionRepository.deleteById(id);
        if (!reactionRepository.findById(id).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Reaction removed successfully");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
    }

    public List<ReactionModel> getReactionsPost(Long post_id) {
        return reactionConverter.reactionEntityListToModelList(reactionRepository.getReactionsPost(post_id));
    }

}
