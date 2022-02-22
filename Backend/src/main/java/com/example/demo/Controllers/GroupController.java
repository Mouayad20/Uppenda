package com.example.demo.Controllers;

import com.example.demo.Models.GroupModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /* Post Request */

    @PostMapping(path = "/add")
    public ResponseEntity<String> add(@RequestBody(required = true) GroupModel groupModel,
                                      @RequestHeader("Authorization") String token) {
        return groupService.add(groupModel, token.substring("Bearer ".length()));
    }

    /* Put Request */

    @PutMapping(path = "/update")
    public GroupModel update(@RequestBody(required = true) GroupModel groupModel) {
        return groupService.update(groupModel);
    }

    /* Delete Request */

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        return groupService.delete(id);
    }

    /* Get Request */

    @GetMapping(path = "/getFormat")
    public GroupModel getFormat() {
        return new GroupModel();
    }

    @GetMapping(path = "/addMember/groupId={group_id},memberId={member_id}")
    public ResponseEntity<String> addMember(@PathVariable(name = "group_id", required = true) Long groupId,
                                            @PathVariable(name = "member_id", required = true) Long memberId) {
        return groupService.addMember(groupId, memberId);
    }

    @GetMapping(path = "/deleteMember/groupId={group_id},memberId={member_id}")
    public ResponseEntity<String> deleteMember(@PathVariable(name = "group_id", required = true) Long groupId,
                                               @PathVariable(name = "member_id", required = true) Long memberId) {
        return groupService.deleteMember(groupId, memberId);
    }

    @GetMapping(path = "/getGroup/{id}")
    public GroupModel getGroup(@PathVariable(name = "id", required = true) Long id) {
        return groupService.getGroup(id);
    }

    @GetMapping(path = "/getAll")
    public List<GroupModel> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/getGroupPosts/{group_id}")
    public List<PostModel> getGroupPosts(@PathVariable(name = "group_id", required = true) Long group_id) {
        return groupService.getGroupPosts(group_id);
    }

    @GetMapping("/getGroupUsers/{group_id}")
    public List<UserModel> getGroupUsers(@PathVariable(name = "group_id", required = true) Long group_id) {
        return groupService.getGroupUsers(group_id);
    }

    @GetMapping(path = "/search/word={word}")
    public List<GroupModel> search(@PathVariable String word) {
        return groupService.search(word);
    }

}