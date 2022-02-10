package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entities.GroupEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.GroupModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.GroupRepository;
import com.example.demo.Repositories.PostRepositroy;
import com.example.demo.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepositroy postRepositroy;
    @Autowired
    FormatFactory formatFactory;
    @Autowired
    UserService userService;


    public GroupModel addGroup(long adminId, GroupModel groupModel) {
        UserEntity userEntity = userRepository.findById(adminId).get();
        GroupEntity groupEntity = new GroupEntity();
        groupEntity = formatFactory.convertGroupModelToGroupEntity(groupModel, userEntity, false);
        // groupEntity.setCreatedAt(new Date());
        groupEntity = groupRepository.save(groupEntity);
        addMembersToGroup(groupEntity.getId(),adminId);
        for (int i = 0; i < groupModel.getMembers().size(); i++) {
            addMembersToGroup(groupEntity.getId(), groupModel.getMembers().get(i).getId());
        }
        return formatFactory.convertGroupEntityToGroupModel(groupEntity);
    }

    public List<GroupModel> getAllPages() {
        List<GroupModel> groups = new ArrayList<>();

        for (GroupEntity groupEntity : groupRepository.getAllGroup()) {
            groups.add(formatFactory.convertGroupEntityToGroupModel(groupEntity));
        }
        return groups;
    }

    public GroupModel findById(long id) {
        GroupModel groupModel = formatFactory.convertGroupEntityToGroupModel(groupRepository.findById(id).get());
        return groupModel;
    }

    public GroupModel findByName(String name) {
        return formatFactory.convertGroupEntityToGroupModel(groupRepository.findByName(name));
    }

   

    public GroupModel addMembersToGroup( long groupId,long userId) {
        GroupEntity groupEntity = groupRepository.findById(groupId).get();
        return userService.enterIntoGroup(userId, groupEntity);

    }

    public GroupModel deleateMemberFromGroup(long groupId, long memberId) {
        GroupEntity groupEntity = groupRepository.findById(groupId).get();
        userService.exitFromGroup(memberId, groupEntity);
        groupEntity = groupRepository.findById(groupId).get();
        return formatFactory.convertGroupEntityToGroupModel(groupEntity);

    }

    public GroupModel updateGroupInformation(GroupModel groupModel) {
        GroupEntity groupEntity = groupRepository.findById(groupModel.getId()).get();
        groupEntity.setDescreption(groupModel.getDescription());
        groupEntity.setImagePath(groupModel.getImgPath());
        groupEntity.setName(groupModel.getName());
        groupEntity = groupRepository.save(groupEntity);
        return formatFactory.convertGroupEntityToGroupModel(groupEntity);

    }

    public ResponseEntity<Object> deleteById(long id) {
        Optional<GroupEntity> groupEntity = groupRepository.findById(id);
        if (!groupEntity.isEmpty()) {
            for (UserEntity userEntity : groupEntity.get().getMembers()) {
                userService.exitFromGroup(userEntity.getId(), groupEntity.get());
            }
            groupRepository.deleteById(id);
            return ResponseEntity.ok("sucsessfuly deleate the group");
        } else
            return ResponseEntity.badRequest().body("page is not found");
    }

    public GroupModel changeAdmin(long id,long adminId){
        Optional<GroupEntity> groupEntity = groupRepository.findById(id);
        Optional<UserEntity> userEntity = userRepository.findById(adminId);
        GroupEntity savedEntity = new GroupEntity();
        if(!groupEntity.isEmpty() && !userEntity.isEmpty()){
            if(groupEntity.get().getAdmin().getId() != userEntity.get().getId())
                {
                    groupEntity.get().setAdmin(userEntity.get());
                    savedEntity = groupRepository.save(groupEntity.get());
                }
        }
        return formatFactory.convertGroupEntityToGroupModel(savedEntity);
    }



    
    public List<PostModel> fetchAllPostFromGroupById(Long g_id){
        return formatFactory.postEntityListToModelList(postRepositroy.getAllPostByGroupId(g_id),true,true,false,false,true);
    }

    public List<UserModel> fetchAllUserFromGroupByGId(Long g_id){

        return findById(g_id).getMembers();
    }

}
