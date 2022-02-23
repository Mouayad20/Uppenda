package com.example.demo.Services;

import com.example.demo.Converters.CommentConverter;
import com.example.demo.Converters.PostConverter;
import com.example.demo.Converters.UserConverter;
import com.example.demo.Entities.CommentEntity;
import com.example.demo.Models.CommentModel;
import com.example.demo.Repositories.CommentRepository;
import com.example.demo.Repositories.PostRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private TokenUtil tokenUtil;

    public ResponseEntity<String> add(CommentModel commentModel, Long post_id, String token) {

        CommentEntity commentEntity = commentConverter.commentModelToEntity(commentModel);

        commentEntity.setPostEntity(postRepository.findById(post_id).get());
        commentEntity.setUserEntity(userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get());

        CommentEntity savedEntity = commentRepository.save(commentEntity);

        if (commentRepository.findById(savedEntity.getId()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Comment added successfully");
        } else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");

    }

    public CommentModel update(CommentModel commentModel) {
        CommentEntity commentEntity = commentRepository.findById(commentModel.getId()).get();
        commentEntity.setContent(commentModel.getContent());
        commentEntity.setImage_path((commentModel.getImagePath()));
        commentEntity.setCreatedAt(new Date());
        return commentConverter.commentEntityToModel(commentRepository.save(commentEntity), false);
    }

    public ResponseEntity<String> delete(Long id) {
        CommentEntity commentEntity = commentRepository.findById(id).get();
        commentRepository.deleteById(id);
        if (!commentRepository.findById(commentEntity.getId()).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Comment deleted successfully ");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Comment not deleted, problem happened");
    }

    public List<CommentModel> getCommentsPost(Long post_id) {
        return commentConverter.commentEntityListToModelList(commentRepository.getCommentsPost(post_id));

    }

}
