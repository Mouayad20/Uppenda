package com.example.demo.Controllers;


import com.example.demo.Models.PostModel;
import com.example.demo.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    /* Post Request */

    @PostMapping("/addProfilePost/{u_id}")
    public PostModel addProfilePost(@RequestBody PostModel postModel, @PathVariable(name = "u_id") Long u_id) {
        return postService.addProfilePost(postModel, u_id);
    }

    @PostMapping("/addGroupPost/{u_id}/{g_id}")
    public PostModel addGroupPost(@RequestBody PostModel postModel,
                                  @PathVariable(name = "u_id") Long u_id,
                                  @PathVariable(name = "g_id") Long g_id) {

        return postService.addGroupPost(postModel, u_id, g_id);
    }

    @PostMapping("/addPagePost/{u_id}/{p_id}")
    public PostModel addPagePost(@RequestBody PostModel postModel,
                                 @PathVariable(name = "u_id") Long u_id,
                                 @PathVariable(name = "p_id") Long p_id) {
        return postService.addPagePost(postModel, u_id, p_id);
    }

    /* Put Request */

    @PutMapping("/update")
    public PostModel update(@RequestBody PostModel postModel) {
        return postService.update(postModel);
    }

    /* Delete Request */

    @DeleteMapping("/delete/{id}")
    public PostModel delete(@PathVariable(name = "id") Long id) {
        return postService.delete(id);
    }

    /* Get Request */

    @GetMapping("/getFormat")
    public PostModel getFormat() {
        return new PostModel();
    }

    @GetMapping("/getByID/{postId}")
    public PostModel getByID(@PathVariable(name = "postId") Long id) {
        return postService.getByID(id);
    }

    @GetMapping("/getAll")
    public List<PostModel> getAll() {
        return postService.fetch();
    }

    @GetMapping(path = "/getAllPostsForUserByUID/user_id={id}")
    public List<PostModel> getUserPosts(@PathVariable(name = "id", required = true) long id) {
        return postService.getAllPostByUserId(id);
    }

    @GetMapping(path = "/getAllPostsForGroupByGID/group_id={id}")
    public List<PostModel> getGroupsPosts(@PathVariable(name = "id", required = true) long id) {
        return postService.getAllPostByGroupId(id);
    }

    @GetMapping(path = "/getAllPostsForPageByPID/page_id={id}")
    public List<PostModel> getPagesPosts(@PathVariable(name = "id", required = true) long id) {
        return postService.getAllPostByPageId(id);
    }

    @GetMapping(path = "/getSummery/user_id={id}")
    public List<PostModel> getSummery(@PathVariable(name = "id", required = true) long id) {
        return postService.getSummery(id);
    }

}
