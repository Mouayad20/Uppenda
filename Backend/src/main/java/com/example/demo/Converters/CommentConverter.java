package com.example.demo.Converters;

import com.example.demo.Entities.CommentEntity;
import com.example.demo.Models.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentConverter {

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private PostConverter postConverter;

    public CommentEntity commentModelToEntity(CommentModel commentModel) {

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentModel.getId());
        commentEntity.setContent(commentModel.getContent());
        commentEntity.setImage_path(commentModel.getImagePath());
        commentEntity.setCreatedAt(commentModel.getCreatedAt());
        commentEntity.setPostEntity(postConverter.postModelToEntity(commentModel.getPostModel()));
        commentEntity.setUserEntity(userConverter.convertUserModelToUserEntity(commentModel.getUserModel(), true)); /////////////////////////////////////// true
        /////////////////////////////////////// or
        /////////////////////////////////////// false
        /////////////////////////////////////// ???
        return commentEntity;

    }

    public CommentModel commentEntityToModel(CommentEntity commentEntity, boolean withPost) {

        CommentModel commentModel = new CommentModel();
        commentModel.setId(commentEntity.getId());
        commentModel.setContent(commentEntity.getContent());
        commentModel.setImagePath(commentEntity.getImage_path());
        commentModel.setCreatedAt(commentEntity.getCreatedAt());
        if (withPost)
            commentModel
                    .setPostModel(postConverter.postEntityToModel(commentEntity.getPostEntity(), false, false, false, false, false));
        commentModel.setUserModel(userConverter.getUserModelWithBasicInformation(commentEntity.getUserEntity()));
        return commentModel;

    }

    public List<CommentModel> commentEntityListToModelList(List<CommentEntity> allByPostId) {
        List<CommentModel> list = new ArrayList<>();
        for (CommentEntity commentEntity : allByPostId) {
            list.add(commentEntityToModel(commentEntity, false));
        }
        return list;
    }

    public List<CommentEntity> commentModelListToEntityList(List<CommentModel> commentModels) {
        List<CommentEntity> list = new ArrayList<>();
        if (commentModels != null)
            for (CommentModel commentModel : commentModels) {
                list.add(commentModelToEntity(commentModel));
            }
        return list;
    }

}
