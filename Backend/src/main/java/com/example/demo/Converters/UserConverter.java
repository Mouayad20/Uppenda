package com.example.demo.Converters;

import com.example.demo.Entities.GroupEntity;
import com.example.demo.Entities.PageEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.GroupModel;
import com.example.demo.Models.PageModel;
import com.example.demo.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserConverter {

    @Autowired
    private GroupConverter groupConverter;
    @Autowired
    private AnswerConverter answerConverter;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private PageConverter pageConverter;

    public UserModel getUserModelWithBasicInformation(UserEntity userEntity) {
        UserModel userModel = new UserModel();

        if (userEntity != null) {
            userModel.setId(userEntity.getId());
            userModel.setFirstName(userEntity.getFirstName());
            userModel.setLastName(userEntity.getLastName());
            userModel.setGender(userEntity.getGender());
            userModel.setAge(userEntity.getAge());
            userModel.setEmail(userEntity.getEmail());
            userModel.setMobile(userEntity.getMobile());
            userModel.setCreated_at(userEntity.getCreatedAt());
            userModel.setOnLine(userEntity.isOnLine());
            userModel.setPassword(userEntity.getPassword());
            userModel.setStudyLevel(userEntity.getStudyLevel());
            userModel.setLocation(userEntity.getLocation());
            userModel.setImagePath(userEntity.getImagePath());
            userModel.setAnswerModels(
                    new ArrayList<>()/* answerListEntityToListModel(userEntity.getAnswersEntities()) */);// <<<<<<<<
            userModel.setFriends(null);
            userModel.setGroups(null);
            userModel.setPages(null);
            userModel.setPostModels(new ArrayList<>());
            userModel.setSavedPost(new ArrayList<>());
            userModel.setSharedPost(new ArrayList<>());
        }
        return userModel;

    }

    public UserModel convertUserEntityToUserModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setFirstName(userEntity.getFirstName());
        userModel.setLastName(userEntity.getLastName());
        userModel.setLocation(userEntity.getLocation());
        userModel.setStudyLevel(userEntity.getStudyLevel());
        userModel.setImagePath(userEntity.getImagePath());
        userModel.setGender(userEntity.getGender());
        userModel.setAge(userEntity.getAge());
        userModel.setEmail(userEntity.getEmail());
        userModel.setMobile(userEntity.getMobile());
        userModel.setIp(userEntity.getIp());
        userModel.setCreated_at(userEntity.getCreatedAt());
        userModel.setOnLine(userEntity.isOnLine());
        userModel.setPassword(userEntity.getPassword());
        userModel.setIp(userEntity.getIp());
        if (!userEntity.getFriends().isEmpty())
            userModel.setFriends(fetchFriendsFromUserEntity(userEntity.getFriends()));
        userModel.setAnswerModels(answerConverter.answerListEntityToListModel(userEntity.getAnswersEntities()));// <<<<<<<<
        List<GroupModel> groups = new ArrayList<>();
        if (userEntity.getGroups() != null && !userEntity.getGroups().isEmpty()) {
            for (GroupEntity groupEntity : userEntity.getGroups()) {
                groups.add(groupConverter.convertGroupEntityToGroupModel(groupEntity));
            }
        }
        userModel.setGroups(groups);
        List<PageModel> pages = new ArrayList<>();
        if (userEntity.getPages() != null && !userEntity.getPages().isEmpty()) {

            for (PageEntity pageEntity : userEntity.getPages()) {
                pages.add(pageConverter.convertPageEntityToPageModel(pageEntity));
            }
        }
        userModel.setPages(pages);
        userModel.setPostModels(
                postConverter.postEntityListToModelList(userEntity.getPostEntity(), false, false, false, false, false)); ////////////////////////////////////////////////////////////////
        userModel.setSavedPost(
                new ArrayList<>()/* postEntityListToModelList(userEntity.getSavedPost(), false, false, false) */); ////////////////////////////////////////////////////////////////
        userModel.setSharedPost(
                new ArrayList<>()/* postEntityListToModelList(userEntity.getSharedPost(), false, false, false) */); ////////////////////////////////////////////////////////////////
        userModel.setChats(new ArrayList<>()/* chatListEntityToListModel(userEntity.getChats(), false) */);
        userModel.setMessages(new ArrayList<>()/* messageEntityListToModleList(userEntity.getMessages(), false) */);
        return userModel;
    }

    public UserEntity convertUserModelToUserEntity(UserModel userModel, boolean useIdFromModel) {
        UserEntity userEntity = new UserEntity();
        if (userModel == null)
            return null;
        if (useIdFromModel)
            userEntity.setId(userModel.getId());
        userEntity.setStudyLevel(userModel.getStudyLevel());
        userEntity.setFirstName(userModel.getFirstName());
        userEntity.setLastName(userModel.getLastName());
        userEntity.setAge(userModel.getAge());
        userEntity.setImagePath(userModel.getImagePath());
        userEntity.setLocation(userModel.getLocation());
        userEntity.setCreatedAt(userModel.getCreated_at());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setGender(userModel.getGender());
        userEntity.setMobile(userModel.getMobile());
        userEntity.setOnLine(userModel.isOnLine());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setFriends(new ArrayList<>());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setIp(userModel.getIp());
        userEntity.setPostEntity(postConverter.postModelListToEntityList(userModel.getPostModels())); /////////////////////////////////////////////////
        userEntity.setSavedPost(postConverter.postModelListToEntityList(userModel.getSavedPost())); /////////////////////////////////////////////////
        userEntity.setSharedPost(postConverter.postModelListToEntityList(userModel.getSharedPost())); /////////////////////////////////////////////////
        userEntity.setAnswersEntities(answerConverter.answerListModelToListEntity(userModel.getAnswerModels()));// <<<<<<<<
        return userEntity;
    }

    public List<UserModel> fetchFriendsFromUserEntity(List<UserEntity> friends) {
        List<UserModel> friendsModel = new ArrayList<>();
        UserModel userModel;
        if (friends == null)
            return null;
        for (UserEntity friendEntity : friends) {
            userModel = new UserModel();

            userModel.setId(friendEntity.getId());
            userModel.setFirstName(friendEntity.getFirstName());
            userModel.setLastName(friendEntity.getLastName());
            userModel.setGender(friendEntity.getGender());
            userModel.setAge(friendEntity.getAge());
            userModel.setEmail(friendEntity.getEmail());
            userModel.setIp(friendEntity.getIp());
            userModel.setMobile(friendEntity.getMobile());
            userModel.setCreated_at(friendEntity.getCreatedAt());
            userModel.setOnLine(friendEntity.isOnLine());
            userModel.setFriends(new ArrayList<>());
            userModel.setImagePath(friendEntity.getImagePath());

            friendsModel.add(userModel);
        }
        return friendsModel;
    }

    public List<UserModel> convertUserListEntityToListModel(List<UserEntity> members) {
        List<UserModel> list = new ArrayList<>();
        for (UserEntity userEntity : members) {
            list.add(convertUserEntityToUserModel(userEntity));
        }
        return list;
    }

    public List<UserEntity> convertUserListModelToListEntity(List<UserModel> userModels) {
        List<UserEntity> list = new ArrayList<>();
        if (userModels != null)
            for (UserModel userModel : userModels) {
                list.add(convertUserModelToUserEntity(userModel, true));//////////// true or false ????
            }
        return list;
    }

}
