package com.example.demo.Controllers;


import java.util.List;

import com.example.demo.Models.PostModel;
import com.example.demo.Repositories.PostRepositroy;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.services.FormatFactory;
import com.example.demo.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/post")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FormatFactory formatFactory;
    @Autowired
    PostRepositroy postRepositroy ;


    @PostMapping("/addProfilePost/{u_id}")
    public PostModel addPostOnProfile(@RequestBody PostModel postModel,@PathVariable(name = "u_id") Long u_id) {
        return postService.addProfilePost(postModel, u_id);
    }

    @PostMapping("/addGroupPost/{u_id}/{g_id}")
    public PostModel addPostOnGroup(
                    @RequestBody PostModel postModel,
                    @PathVariable(name = "u_id") Long u_id,
                    @PathVariable(name = "g_id") Long g_id) {
            
        return postService.addGroupPost(postModel, u_id , g_id );
}

    @PostMapping("/addPagePost/{u_id}/{p_id}")
    public PostModel addPostOnPage(
                    @RequestBody PostModel postModel,
                    @PathVariable(name = "u_id") Long u_id,
                    @PathVariable(name = "p_id") Long p_id) {
            
        return postService.addPagePost(postModel, u_id , p_id );
}

    @PostMapping("/update")
    public PostModel updatePost(@RequestBody PostModel postModel){
        return  postService.update(postModel);

    }
    @GetMapping("/getByID/{postId}")
    public PostModel findPostById(@PathVariable(name = "postId") Long id){
        return postService.findById(id);
    }

    @GetMapping("/getAll")
    public List<PostModel> getAll(){
        return postService.fetch();
    }

    @DeleteMapping("/delete/{id}")
    public PostModel deletePost(@PathVariable(name = "id") Long id ){
        return postService.deleteById(id);
    }
    
    @GetMapping("/getFormate")
    public PostModel getFormat(){
        return new PostModel() ;
    }

    @GetMapping(path = "/getAllPostsForUserByUID/user_id={id}")
    public List<PostModel> getUserPosts(@PathVariable(name = "id",required = true)long id){
        return formatFactory.postEntityListToModelList(postRepositroy.getAllPostByUserId(id),true,true,true,true,true);
    }

    @GetMapping(path = "/getAllPostsForGroupByGID/group_id={id}")
    public List<PostModel> getGroupsPosts(@PathVariable(name = "id",required = true)long id){
        return formatFactory.postEntityListToModelList(postRepositroy.getAllPostByGroupId(id),true,true,true,true,true);
    }

    @GetMapping(path = "/getAllPostsForPageByPID/page_id={id}")
    public List<PostModel> getPagesPosts(@PathVariable(name = "id",required = true)long id){
        return formatFactory.postEntityListToModelList(postRepositroy.getAllPostByPageId(id),true,true,true,true,true);
    }

    @GetMapping(path = "/getSummery/user_id={id}")
    public List<PostModel> getSummery(@PathVariable(name = "id",required = true)long id){
        return formatFactory.postEntityListToModelList(postRepositroy.getSummery(id),true,true,true,true,true);
    }
    
}
