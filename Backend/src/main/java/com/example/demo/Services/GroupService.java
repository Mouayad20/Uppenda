package com.example.demo.Services;

import com.example.demo.Converters.GroupConverter;
import com.example.demo.Converters.PostConverter;
import com.example.demo.Entities.GroupEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.GroupModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.GroupRepository;
import com.example.demo.Repositories.PostRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private PostRepository postRepositroy;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupConverter groupConverter;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private TokenUtil tokenUtil;

    public ResponseEntity<String> add(GroupModel groupModel, String token) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();
        GroupEntity groupEntity = groupConverter.convertGroupModelToGroupEntity(groupModel);

        groupEntity.setAdmin(userEntity);
        groupEntity.getMembers().add(userEntity);

        groupEntity = groupRepository.save(groupEntity);
        userEntity.getGroups().add(groupEntity);
        userRepository.save(userEntity);

        if(groupRepository.findById(groupEntity.getId()).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Group created successfully");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");

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
        return postConverter.postEntityListToModelList(postRepositroy.getGroupsPosts(g_id), true, true, false, false, true);
    }

    public List<UserModel> getAllUsersInGroupByGId(Long g_id) {

        return findById(g_id).getMembers();
    }

    public List<GroupModel> search(String word) {
        return groupConverter.convertGroupEntityListToGroupModelList(groupRepository.searchGroup(word));
    }
}
