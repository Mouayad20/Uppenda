package com.example.demo.Converters;

import com.example.demo.Entities.GroupEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.GroupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupConverter {

    @Autowired
    private PostConverter postConverter;
    @Autowired
    private UserConverter userConverter;

    public GroupEntity convertGroupModelToGroupEntity(GroupModel groupModel, UserEntity userEntity,
                                                      boolean userIdFromModel) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setCreatedAt(groupModel.getCreatedAt());
        if (userIdFromModel) {
            groupEntity.setId(groupModel.getId());
        }
        groupEntity.setDescription(groupModel.getDescription());
        groupEntity.setImagePath(groupModel.getImgPath());
        groupEntity.setName(groupModel.getName());
        groupEntity.setMembers(new ArrayList<>());
        groupEntity.setAdmin(userEntity);
        groupEntity.setPostEntities(postConverter.postModelListToEntityList(groupModel.getPostModels()));///////////////////
        return groupEntity;

    }

    public GroupModel convertGroupEntityToGroupModel(GroupEntity groupEntity) {
        GroupModel groupModel = new GroupModel();
        if (groupEntity == null)
            return null;
        groupModel.setId(groupEntity.getId());
        groupModel.setDescription(groupEntity.getDescription());
        groupModel.setImgPath(groupEntity.getImagePath());
        groupModel.setCreatedAt(groupEntity.getCreatedAt());
        groupModel.setName(groupEntity.getName());
        groupModel.setMembers(userConverter.fetchFriendsFromUserEntity(groupEntity.getMembers()));
        groupModel.setAdmin(userConverter.getUserModelWithBasicInformation(groupEntity.getAdmin()));
        groupModel.setPostModels(new ArrayList<>()/*
         * postEntityListToModelList(groupEntity.getPostEntities(),
         * true,true,true,true,true)
         */);///////////////////////////
        return groupModel;

    }

    public List<GroupModel> convertGroupEntityListToGroupModelList(List<GroupEntity> searchGroup) {
        List<GroupModel> list = new ArrayList<>();
        if (searchGroup != null) {
            for (GroupEntity groupEntity : searchGroup) {
                list.add(convertGroupEntityToGroupModel(groupEntity));
            }

        }
        return list;
    }

}
