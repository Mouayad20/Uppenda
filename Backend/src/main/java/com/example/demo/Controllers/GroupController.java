package com.example.demo.Controllers;

import java.util.List;

import com.example.demo.Converters.GroupConverter;
import com.example.demo.Entities.GroupEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.GroupModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.GroupRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.services.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/groups")
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired 
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupConverter groupConverter;


    @PostMapping(path = "/addGroup/adminId={admin_id}")
    public GroupModel addGroup(@PathVariable(name = "admin_id",required = true)long adminId,@RequestBody(required = true)GroupModel groupModel){
        return groupService.addGroup(adminId,groupModel);
    }
/*
"SELECT * FROM post WHERE u_id=:u_id OR ( u_id=:u_id AND g_id=(SELECT groups_id FROM users_groups WHERE members_id=:u_id)) OR ( u_id=:u_id AND page_id=(SELECT pages_id FROM users_pages WHERE members_id=:u_id)) ORDER BY `post`.`created_at` DESC"
 */
    @PostMapping(path = "/addMember/group_id={g_id},user_id={u_id}")
    public GroupModel addMemberTorGroup(@PathVariable(name = "g_id",required = true)long g_id,@PathVariable(name = "u_id")long memberId){
        return groupService.addMembersToGroup( g_id,memberId);
    }

    @PostMapping(path = "/leaveMember/group_id={g_id},user_id={u_id}")
    public GroupModel leaveMemberFromGroup(@PathVariable(name = "g_id", required = true) long g_id,
                                           @PathVariable(name = "u_id",required = true) long memberId) {
        return groupService.deleateMemberFromGroup(g_id, memberId);
    }

    @PostMapping(path = "/updateInformation")
    public GroupModel updateInformation(@RequestBody(required = true)GroupModel groupModel){
        return groupService.updateGroupInformation(groupModel);
    }

    @GetMapping(path = "/getGroup/Id={id}")
    public GroupModel findById(@PathVariable(name = "id",required = true)long id){
        return groupService.findById(id);
    }
    
    @GetMapping(path = "/getAll")
    public List<GroupModel> getAllGroups(){
        return groupService.getAllPages();
    }

    @GetMapping(path = "/getGroup/Name={name}")
    public GroupModel findByName(@PathVariable(required = true,name = "name")String name){
        return groupService.findByName(name);
    }
   
    @GetMapping(path = "/deleteGroup/Id={id}")
    public ResponseEntity<Object> deleteById(@PathVariable(required = true,name = "id")long id){
        return groupService.deleteById(id);
    }

    @GetMapping(path = "/changeAdmin/groupId={group_id},adminId={admin_id}")
    public GroupModel changeAdmin(@PathVariable(name = "group_id", required = true) long id,
            @PathVariable(required = true, name = "admin_id") long adminId) {
        return groupService.changeAdmin(id, adminId);
    }

    @GetMapping("/getAllPostsInGroupByGId/{g_id}")
    public List<PostModel> getAllPostsInGroupByGId(@PathVariable(name = "g_id",required = true)Long g_id){
        return groupService.fetchAllPostFromGroupById(g_id);
    }

    @GetMapping("/getAllUsersInGroupByGId/{g_id}")
    public List<UserModel> getAllUsersInGroupByGId(@PathVariable(name = "g_id",required = true)Long g_id){
        return groupService.fetchAllUserFromGroupByGId(g_id);
    }

    @GetMapping(path = "/test")
    public GroupModel test(){
        GroupEntity groupEntity = new GroupEntity();
        groupEntity = groupRepository.findById((long)1).get();
        UserEntity userEntity = userRepository.findById((long)1).get();
        groupEntity.getMembers().remove(userEntity);
        groupEntity = groupRepository.save(groupEntity);
        return groupConverter.convertGroupEntityToGroupModel(groupEntity);
    }

    @GetMapping(path = "/getFormat")
    public String getFormatOfGroupModel(){
        return new GroupModel().toString();
    }

    @GetMapping(path = "/search/word={word}")
    public List<GroupModel> search(@PathVariable String word){
        return groupConverter.convertGroupEntityListToGroupModelList(groupRepository.searchGroup(word));
    }


}
