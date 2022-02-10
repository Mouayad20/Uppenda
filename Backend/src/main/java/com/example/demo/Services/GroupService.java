package com.example.demo.Services;

import com.example.demo.Converters.GroupConverter;
import com.example.demo.Converters.PostConverter;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepositroy postRepositroy;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupConverter groupConverter;
    @Autowired
    private PostConverter postConverter;

    public GroupModel add(long adminId, GroupModel groupModel) {
        UserEntity userEntity = userRepository.findById(adminId).get();
        GroupEntity groupEntity = new GroupEntity();
        groupEntity = groupConverter.convertGroupModelToGroupEntity(groupModel, userEntity, false);
        // groupEntity.setCreatedAt(new Date());
        groupEntity = groupRepository.save(groupEntity);
        addMembersToGroup(groupEntity.getId(), adminId);
        for (int i = 0; i < groupModel.getMembers().size(); i++) {
            addMembersToGroup(groupEntity.getId(), groupModel.getMembers().get(i).getId());
        }
        return groupConverter.convertGroupEntityToGroupModel(groupEntity);
    }

    public GroupModel addMembersToGroup(long groupId, long userId) {
        GroupEntity groupEntity = groupRepository.findById(groupId).get();
        return userService.enterIntoGroup(userId, groupEntity);
    }

    public GroupModel deleteMemberFromGroup(long groupId, long memberId) {
        GroupEntity groupEntity = groupRepository.findById(groupId).get();
        userService.exitFromGroup(memberId, groupEntity);
        groupEntity = groupRepository.findById(groupId).get();
        return groupConverter.convertGroupEntityToGroupModel(groupEntity);
    }

    public GroupModel update(GroupModel groupModel) {
        GroupEntity groupEntity = groupRepository.findById(groupModel.getId()).get();
        groupEntity.setDescription(groupModel.getDescription());
        groupEntity.setImagePath(groupModel.getImgPath());
        groupEntity.setName(groupModel.getName());
        groupEntity = groupRepository.save(groupEntity);
        return groupConverter.convertGroupEntityToGroupModel(groupEntity);
    }

    public ResponseEntity<Object> delete(long id) {
        Optional<GroupEntity> groupEntity = groupRepository.findById(id);
        if (!groupEntity.isEmpty()) {
            for (UserEntity userEntity : groupEntity.get().getMembers()) {
                userService.exitFromGroup(userEntity.getId(), groupEntity.get());
            }
            groupRepository.deleteById(id);
            return ResponseEntity.ok("successfully delete the group");
        } else
            return ResponseEntity.badRequest().body("group is not found");
    }

    public List<GroupModel> getAllGroups() {
        List<GroupModel> groups = new ArrayList<>();
        for (GroupEntity groupEntity : groupRepository.getAllGroup()) {
            groups.add(groupConverter.convertGroupEntityToGroupModel(groupEntity));
        }
        return groups;
    }

    public GroupModel findById(long id) {
        GroupModel groupModel = groupConverter.convertGroupEntityToGroupModel(groupRepository.findById(id).get());
        return groupModel;
    }

    public GroupModel findByName(String name) {
        return groupConverter.convertGroupEntityToGroupModel(groupRepository.findByName(name));
    }

    public GroupModel changeAdmin(long id, long adminId) {
        Optional<GroupEntity> groupEntity = groupRepository.findById(id);
        Optional<UserEntity> userEntity = userRepository.findById(adminId);
        GroupEntity savedEntity = new GroupEntity();
        if (!groupEntity.isEmpty() && !userEntity.isEmpty()) {
            if (groupEntity.get().getAdmin().getId() != userEntity.get().getId()) {
                groupEntity.get().setAdmin(userEntity.get());
                savedEntity = groupRepository.save(groupEntity.get());
            }
        }
        return groupConverter.convertGroupEntityToGroupModel(savedEntity);
    }

    public List<PostModel> getAllPostsInGroupByGId(Long g_id) {
        return postConverter.postEntityListToModelList(postRepositroy.getAllPostByGroupId(g_id), true, true, false, false, true);
    }

    public List<UserModel> getAllUsersInGroupByGId(Long g_id) {

        return findById(g_id).getMembers();
    }

    public List<GroupModel> search(String word) {
        return groupConverter.convertGroupEntityListToGroupModelList(groupRepository.searchGroup(word));
    }
}
