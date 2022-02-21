package com.example.demo.Converters;

import com.example.demo.Entities.MediaEntity;
import com.example.demo.Entities.PostEntity;
import com.example.demo.Models.PostModel;
import com.example.demo.Repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostConverter {

    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private GroupConverter groupConverter;
    @Autowired
    private PageConverter pageConverter;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private ReactionConverter reactionConverter;
    @Autowired
    private MediaConverter mediaConverter;
    @Autowired
    private TypeConverter typeConverter;

    public PostEntity postModelToEntity(PostModel postModel) {

        PostEntity postEntity = new PostEntity();
        if (postModel == null)
            return null;
        postEntity.setId(postModel.getId());
        postEntity.setContent(postModel.getContent());
        postEntity.setCreatedAt(postModel.getCreatedAt());
        postEntity.setCommentEntities(commentConverter.commentModelListToEntityList(postModel.getCommentModels()));
        postEntity.setReactionEntities(reactionConverter.reactionModelListToEntityList(postModel.getReactionModels()));
        postEntity.setParticipants(userConverter.convertUserListModelToListEntity(postModel.getParticipants()));
        postEntity.setType(typeRepository.findByType(postModel.getType().getType()).get());
        return postEntity;
    }

    public PostModel postEntityToModel(PostEntity postEntity, boolean withParticipants, boolean withSavers,
                                       boolean withGroups, boolean withPages, boolean withReactions) {
        PostModel postModel = new PostModel();
        postModel.setId(postEntity.getId());
        postModel.setContent(postEntity.getContent());
        postModel.setCreatedAt(postEntity.getCreatedAt());
        postModel.setUserModel(userConverter.getUserModelWithBasicInformation(postEntity.getUserEntity()));
        postModel.setType(typeConverter.typeEntityToModel(postEntity.getType()));
        postModel.setCommentModels(commentConverter.commentEntityListToModelList(postEntity.getCommentEntities()));

        if (postEntity.getMedia() != null && postEntity.getMedia().size() != 0) {
            postModel.setMedia(new ArrayList<>());
            for (MediaEntity media : postEntity.getMedia()) {
                postModel.getMedia().add(mediaConverter.convertMediaEntityToMediaModel(media));
            }
        }
        if (withReactions)
            postModel.setReactionModels(reactionConverter.reactionEntityListToModelList(postEntity.getReactionEntities()));
        if (withGroups)
            postModel.setGroupModel(groupConverter.convertGroupEntityToGroupModel(postEntity.getGroupEntity()));
        if (withPages)
            postModel.setPageModel(pageConverter.convertPageEntityToPageModel(postEntity.getPageEntity()));
        if (withParticipants)
            postModel.setParticipants(userConverter.convertUserListEntityToListModel(postEntity.getParticipants()));
        if (withSavers)
            postModel.setSavers(userConverter.convertUserListEntityToListModel(postEntity.getSavers()));
        return postModel;
    }

    public List<PostModel> postEntityListToModelList(List<PostEntity> findAll, boolean withParticipants,
                                                     boolean withSavers, boolean withGroups, boolean withPages, boolean withReactions) {

        List<PostModel> list = new ArrayList<>();
        if (findAll == null)
            return null;
        for (PostEntity postEntity : findAll) {
            list.add(postEntityToModel(postEntity, withParticipants, withSavers, withGroups, withPages, withReactions));
        }

        return list;
    }

    public List<PostModel> postEntityIterableToModelList(Iterable<PostEntity> all, boolean withGroups,
                                                         boolean withPages) {
        List<PostModel> list = new ArrayList<>();
        if (all == null)
            return null;
        for (PostEntity postEntity : all) {
            list.add(postEntityToModel(postEntity, true, true, withGroups, withPages, true));
        }

        return list;
    }

    public List<PostEntity> postModelListToEntityList(List<PostModel> postModelList) {

        List<PostEntity> list = new ArrayList<>();
        if (postModelList != null)
            for (PostModel postModel : postModelList) {
                list.add(postModelToEntity(postModel));
            }

        return list;
    }

}
