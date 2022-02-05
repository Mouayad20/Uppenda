package com.example.demo.Services;

import com.example.demo.Converters.ReactionConverter;
import com.example.demo.Entities.PostEntity;
import com.example.demo.Entities.ReactionEntity;
import com.example.demo.Entities.ReactionTypeEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.ReactionModel;
import com.example.demo.Repositories.PostRepositroy;
import com.example.demo.Repositories.ReactionRepository;
import com.example.demo.Repositories.ReactionsTypeRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private PostRepositroy postRepositroy;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InterestService interestService;
    @Autowired
    private ReactionsTypeRepository reactionsTypeRepository;
    @Autowired
    private ReactionConverter reactionConverter;

    public ReactionModel add(Long post_id, Long u_id, Long reaction_id) {

        ReactionEntity reactionEntity = new ReactionEntity();

        UserEntity userEntity = userRepository.findById(u_id).get();

        PostEntity postEntity = postRepositroy.findById(post_id).get();

        ReactionTypeEntity reactionsEntity = reactionsTypeRepository.findById(reaction_id).get();

        reactionEntity.setPostEntity(postEntity);
        reactionEntity.setUserEntity(userEntity);
        reactionEntity.setReaction(reactionsEntity);

        interestService.addInterest(postEntity, userEntity);

        return reactionConverter.reactionEntityToModel(reactionRepository.save(reactionEntity), false, true);

    }

    public String remove(Long id) {

        reactionRepository.deleteById(id);
        if (reactionRepository.findById(id).isPresent())
            return "removed reaction failed";
        else
            return "removed reaction successfully";

    }

    public List<ReactionModel> getAllReactionForPost(Long post_id) {
        return reactionConverter.reactionEntityListToModelList(reactionRepository.getAllReactionsByPostId(post_id));
    }

}
