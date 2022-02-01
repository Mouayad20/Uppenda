package com.example.demo.services;

import com.example.demo.Converters.CommentConverter;
import com.example.demo.Converters.PostConverter;
import com.example.demo.Converters.UserConverter;
import com.example.demo.Entities.CommentEntity;
import com.example.demo.Entities.PostEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.CommentModel;
import com.example.demo.Repositories.CommentRepository;
import com.example.demo.Repositories.PostRepositroy;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepositroy postRepositroy;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InterestService interestService;
    @Autowired
    CommentConverter commentConverter;
    @Autowired
    PostConverter postConverter;
    @Autowired
    UserConverter userConverter;

    public CommentModel add(CommentModel commentModel, Long post_id, Long u_id) {

        UserEntity userEntity = userRepository.findById(u_id).get();

        PostEntity postEntity = postRepositroy.findById(post_id).get();

        interestService.addInterst(postEntity, userEntity);
        commentModel.setPostModel(postConverter.postEntityToModel(postEntity, false, false, true, true, false));
        commentModel.setUserModel(userConverter.convertUserEntityToUserModel(userEntity));
        CommentEntity savedEntity = commentConverter.commentModelToEntity(commentModel);
        savedEntity = commentRepository.save(savedEntity);
        return commentConverter.commentEntityToModel(savedEntity, false);

    }

    public CommentModel update(CommentModel commentModel) {
        CommentEntity commentEntity = commentRepository.findById(commentModel.getId()).get();
        commentEntity.setContent(commentModel.getContent());
        commentEntity.setImage_path((commentModel.getImagePath()));
        commentEntity.setCreatedAt(new Date());
        return commentConverter.commentEntityToModel(commentRepository.save(commentEntity), false);
    }

    public CommentModel delete(Long id) {
        CommentEntity commentEntity = commentRepository.findById(id).get();
        commentRepository.deleteById(id);
        return commentConverter.commentEntityToModel(commentEntity, false);
    }

    public List<CommentModel> fetchAllCommentByPostId(Long post_id) {
        return commentConverter.commentEntityListToModelList(commentRepository.getAllCommentByPostId(post_id));

    }

}
