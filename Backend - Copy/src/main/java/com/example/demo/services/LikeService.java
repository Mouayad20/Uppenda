package com.example.demo.services;

import java.util.List;

import com.example.demo.Entities.LikeEntity;
import com.example.demo.Entities.PostEntity;
import com.example.demo.Entities.ReactionEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.LikeModel;
import com.example.demo.Repositories.LikeRepository;
import com.example.demo.Repositories.PostRepositroy;
import com.example.demo.Repositories.ReactionsRepository;
import com.example.demo.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;
    @Autowired
    PostRepositroy postRepositroy;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InterestService interestService;
    @Autowired
    ReactionsRepository reactionsRepository; 
    @Autowired
    FormatFactory formatFactory ;

    public LikeModel addLike(Long post_id , Long u_id, Long reaction_id){

        LikeEntity likeEntity = new LikeEntity();

        UserEntity userEntity = userRepository.findById(u_id).get() ;

        PostEntity postEntity = postRepositroy.findById(post_id).get() ;

        ReactionEntity reactionsEntity = reactionsRepository.findById(reaction_id).get();

        likeEntity.setPostEntity(postEntity);
        likeEntity.setUserEntity(userEntity);
        likeEntity.setReaction(reactionsEntity);

        interestService.addInterst(postEntity,userEntity);

        return formatFactory.likeEntityToModel(likeRepository.save(likeEntity),false,true);

    }

    public String removeLikeByLikeId(Long id){

        likeRepository.deleteById(id);
        if (likeRepository.findById(id).isPresent())
            return "removed like failed";
        else
            return "removed like sucsefly"; 
        
    }

    public List<LikeModel> fetchAllLikeByPostId(Long post_id ){
        return formatFactory.likeEntityListToModelList( likeRepository.getAllLikeByPostId(post_id));
    }
    
}
