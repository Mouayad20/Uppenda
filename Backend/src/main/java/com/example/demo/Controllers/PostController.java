package com.example.demo.Controllers;

import com.example.demo.Models.PostModel;
import com.example.demo.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    /* Post Request */

    @PostMapping("/addProfilePost")
    public ResponseEntity<String> addProfilePost(@RequestBody PostModel postModel,
                                                 @RequestHeader("Authorization") String token) {
        return postService.addProfilePost(postModel, token.substring("Bearer ".length()));
    }

    @PostMapping("/addGroupPost/{group_id}")
    public ResponseEntity<String> addGroupPost(@RequestBody PostModel postModel,
                                               @RequestHeader("Authorization") String token,
                                               @PathVariable(name = "group_id") Long group_id) {
        return postService.addGroupPost(postModel, token.substring("Bearer ".length()), group_id);
    }

    @PostMapping("/addPagePost/{page_id}")
    public ResponseEntity<String> addPagePost(@RequestBody PostModel postModel,
                                              @RequestHeader("Authorization") String token,
                                              @PathVariable(name = "page_id") Long page_id) {
        return postService.addPagePost(postModel, token.substring("Bearer ".length()), page_id);
    }

    /* Put Request */

    @PutMapping("/update")
    public PostModel update(@RequestBody PostModel postModel) {
        return postService.update(postModel);
    }

    /* Delete Request */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
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
        return postService.getAll();
    }

    @GetMapping(path = "/getUserPosts/email={email}")
    public List<PostModel> getUserPosts(@PathVariable(name = "email", required = true) String email) {
        return postService.getUserPosts(email);
    }

    @GetMapping(path = "/getGroupsPosts/group_id={id}")
    public List<PostModel> getGroupsPosts(@PathVariable(name = "id", required = true) long id) {
        return postService.getGroupsPosts(id);
    }

    @GetMapping(path = "/getPagesPosts/page_id={id}")
    public List<PostModel> getPagesPosts(@PathVariable(name = "id", required = true) long id) {
        return postService.getPagesPosts(id);
    }

    @GetMapping(path = "/getSummery")
    public List<PostModel> getSummery(@RequestHeader("Authorization") String token) {
        return postService.getSummery(token.substring("Bearer ".length()));
    }

}
