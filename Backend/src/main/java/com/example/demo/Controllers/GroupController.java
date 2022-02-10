package com.example.demo.Controllers;

import com.example.demo.Converters.GroupConverter;
import com.example.demo.Models.GroupModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.GroupRepository;
import com.example.demo.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /* Post Request */

    @PostMapping(path = "/add/adminId={admin_id}")
    public GroupModel add(@PathVariable(name = "admin_id", required = true) long adminId,
                          @RequestBody(required = true) GroupModel groupModel) {
        return groupService.add(adminId, groupModel);
    }

    @PostMapping(path = "/addMember/group_id={g_id},user_id={u_id}")
    public GroupModel addMemberTorGroup(@PathVariable(name = "g_id", required = true) long g_id,
                                        @PathVariable(name = "u_id") long memberId) {
        return groupService.addMembersToGroup(g_id, memberId);
    }

    @PostMapping(path = "/deleteMember/group_id={g_id},user_id={u_id}")
    public GroupModel deleteMemberFromGroup(@PathVariable(name = "g_id", required = true) long g_id,
                                            @PathVariable(name = "u_id", required = true) long memberId) {
        return groupService.deleteMemberFromGroup(g_id, memberId);
    }

    /* Put Request */

    @PutMapping(path = "/update")
    public GroupModel update(@RequestBody(required = true) GroupModel groupModel) {
        return groupService.update(groupModel);
    }

    /* Delete Request */

    @DeleteMapping(path = "/delete/Id={id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true, name = "id") long id) {
        return groupService.delete(id);
    }

    /* Get Request */

    @GetMapping(path = "/getFormat")
    public GroupModel getFormat() {
        return new GroupModel();
    }

    @GetMapping(path = "/getAll")
    public List<GroupModel> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping(path = "/getGroup/Id={id}")
    public GroupModel findById(@PathVariable(name = "id", required = true) long id) {
        return groupService.findById(id);
    }

    @GetMapping(path = "/getGroup/Name={name}")
    public GroupModel findByName(@PathVariable(required = true, name = "name") String name) {
        return groupService.findByName(name);
    }

    @GetMapping(path = "/changeAdmin/groupId={group_id},adminId={admin_id}")
    public GroupModel changeAdmin(@PathVariable(name = "group_id", required = true) long id,
                                  @PathVariable(required = true, name = "admin_id") long adminId) {
        return groupService.changeAdmin(id, adminId);
    }

    @GetMapping("/getAllPostsInGroupByGId/{g_id}")
    public List<PostModel> getAllPostsInGroupByGId(@PathVariable(name = "g_id", required = true) Long g_id) {
        return groupService.getAllPostsInGroupByGId(g_id);
    }

    @GetMapping("/getAllUsersInGroupByGId/{g_id}")
    public List<UserModel> getAllUsersInGroupByGId(@PathVariable(name = "g_id", required = true) Long g_id) {
        return groupService.getAllUsersInGroupByGId(g_id);
    }

    @GetMapping(path = "/search/word={word}")
    public List<GroupModel> search(@PathVariable String word) {
        return groupService.search(word);
    }
}