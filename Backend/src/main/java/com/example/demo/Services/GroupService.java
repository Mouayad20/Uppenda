package com.example.demo.Services;

import com.example.demo.Converters.GroupConverter;
import com.example.demo.Converters.PostConverter;
import com.example.demo.Converters.UserConverter;
import com.example.demo.Entities.ChatEntity;
import com.example.demo.Entities.GroupEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.GroupModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.ChatRepository;
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


@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupConverter groupConverter;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private TokenUtil tokenUtil;

    public ResponseEntity<String> add(GroupModel groupModel, String token) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();
        GroupEntity groupEntity = groupConverter.convertGroupModelToGroupEntity(groupModel);

        ChatEntity chatEntity = new ChatEntity();

        groupEntity.setAdmin(userEntity);
        groupEntity.getMembers().add(userEntity);

        groupEntity = groupRepository.save(groupEntity);
        userEntity.getGroups().add(groupEntity);
        chatEntity.setGroupEntity(groupEntity);
        userRepository.save(userEntity);
        chatRepository.save(chatEntity);



        if (groupRepository.findById(groupEntity.getId()).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Group created successfully");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");

    }

    public GroupModel update(GroupModel groupModel) {
        GroupEntity groupEntity = groupRepository.findById(groupModel.getId()).get();

        groupEntity.setName(groupModel.getName());
        groupEntity.setDescription(groupModel.getDescription());
        groupEntity.setImagePath(groupModel.getImgPath());

        groupEntity = groupRepository.save(groupEntity);
        return groupConverter.convertGroupEntityToGroupModel(groupEntity);
    }

    public ResponseEntity<String> delete(Long id) {
        groupRepository.deleteById(id);
        if (!groupRepository.findById(id).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Group deleted successfully ");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Group not deleted, problem happened");
    }

    public ResponseEntity<String> addMember(Long groupId, Long memberId) {

        GroupEntity groupEntity = groupRepository.findById(groupId).get();
        UserEntity userEntity = userRepository.findById(memberId).get();

        if (!groupEntity.getMembers().contains(userEntity)) {
            groupEntity.getMembers().add(userEntity);
        }
        GroupEntity savedGroup = groupRepository.save(groupEntity);

        if (savedGroup.getMembers().contains(userEntity))
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Member added successfully");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
    }

    public ResponseEntity<String> deleteMember(Long groupId, Long memberId) {

        GroupEntity groupEntity = groupRepository.findById(groupId).get();
        UserEntity userEntity = userRepository.findById(memberId).get();

        if (groupEntity.getMembers().contains(userEntity)) {
            groupEntity.getMembers().remove(userEntity);
        }

        GroupEntity savedGroup = groupRepository.save(groupEntity);

        if (!savedGroup.getMembers().contains(userEntity))
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Member removed successfully");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
    }

    public List<GroupModel> getAll() {
        List<GroupModel> groups = new ArrayList<>();
        for (GroupEntity groupEntity : groupRepository.getAllGroup()) {
            groups.add(groupConverter.convertGroupEntityToGroupModel(groupEntity));
        }
        return groups;
    }

    public GroupModel getGroup(Long id) {
        GroupModel groupModel = groupConverter.convertGroupEntityToGroupModel(groupRepository.findById(id).get());
        return groupModel;
    }

    public List<PostModel> getGroupPosts(Long group_id) {
        return postConverter.postEntityListToModelList(postRepository.getGroupPosts(group_id), true, true, false, false, true);
    }

    public List<UserModel> getGroupUsers(Long group_id) {
        return userConverter.convertUserListEntityToListModel(groupRepository.findById(group_id).get().getMembers());
    }

    public List<GroupModel> search(String word) {
        return groupConverter.convertGroupEntityListToGroupModelList(groupRepository.search(word));
    }
}
