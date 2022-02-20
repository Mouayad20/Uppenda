package com.example.demo.Controllers;

import com.example.demo.Converters.UserConverter;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.SignInModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Security.TokenUtil;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private UserConverter userConverter;

    /* Post Request */

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody UserModel userModel) {
        return userService.signUp(userModel);
    }

    @PostMapping(path = "/signIn")
    public ResponseEntity<Object> signIn(@RequestBody SignInModel signInModel)  {
        return userService.signIn(signInModel);
    }

    /* Put Request */

    @PutMapping(path = "/update")
    public ResponseEntity<Object> update(@RequestBody(required = true) UserModel userModel) {
        return userService.update(userModel);
    }

    /* Delete Request */

    @DeleteMapping("/delete")
    public boolean delete(@RequestHeader("Authorization") String token) {
        return userService.delete(token.substring("Bearer ".length()));
    }

    /* Get Request */


    @GetMapping(path = "/getFormat")
    public UserModel getFormat() {
        Date date = new Date();
        UserModel userModel = new UserModel();
        userModel.setCreatedAt(date);
        return userModel;
    }

    @GetMapping(path = "/search/word={word}")
    public List<UserModel> search(@PathVariable String word) {
        return userConverter.convertUserListEntityToListModel(userRepository.searchUser(word));
    }

    @GetMapping(path = "/getUser/Id={id}")
    public UserModel findById(@PathVariable(name = "id", required = true) long id) {
        return userService.findById(id);
    }

    @GetMapping(path = "/getUser/Name={name}")
    public List<UserModel> findByFirstName(@PathVariable(name = "name", required = true) String firstName) {
        return userService.findByFirstName(firstName);
    }

    @GetMapping(path = "/addFriend/userId={id},friendId={friend_id}")
    public UserModel addFriend(@PathVariable(name = "id", required = true) long id,
                               @PathVariable(name = "friend_id", required = true) long friendId) {
        return userService.addFriend(id, friendId);
    }

    @GetMapping(path = "/unFriend/userId={u_id},friendId={f_id}")
    public UserModel unFriend(@PathVariable(name = "u_id", required = true) long u_id,
                              @PathVariable(name = "f_id", required = true) long f_id) {
        return userService.unFriend(u_id, f_id);
    }

    @GetMapping(path = "/test")
    public UserModel test() {
        return userConverter.convertUserEntityToUserModel(new UserEntity());
    }

    @GetMapping(path = "/savePost/userId={u_id},postId={post_id}")
    public String savePost(@PathVariable(name = "u_id", required = true) long u_id,
                           @PathVariable(name = "post_id", required = true) long post_id) {
        return userService.savedPost(u_id, post_id);
    }

    @GetMapping(path = "/unSavePost/userId={u_id},postId={post_id}")
    public boolean unSavePost(@PathVariable(name = "u_id", required = true) long u_id,
                              @PathVariable(name = "post_id", required = true) long post_id) {
        return userService.unSavedPost(u_id, post_id);
    }

    @GetMapping(path = "/getAllSavedPostByUserId/userId={u_id}")
    public List<PostModel> getAllSavedPosts(@PathVariable(name = "u_id", required = true) Long u_id) {
        return userService.getAllSavedPostsByUserId(u_id);
    }

    @GetMapping(path = "/sharePost/userId={u_id},postId={post_id}")
    public String sharePost(@PathVariable(name = "u_id", required = true) long u_id,
                            @PathVariable(name = "post_id", required = true) long post_id) {
        return userService.sharedPost(u_id, post_id);
    }

    @GetMapping(path = "/unSharePost/userId={u_id},postId={post_id}")
    public boolean unSharePost(@PathVariable(name = "u_id", required = true) long u_id,
                               @PathVariable(name = "post_id", required = true) long post_id) {
        return userService.unSharedPost(u_id, post_id);
    }

    @GetMapping(path = "/getAllSharedPostByUserId/userId={u_id}")
    public List<PostModel> getAllSharedPosts(@PathVariable(name = "u_id", required = true) Long u_id) {
        return userService.getAllSharedPostsByUserId(u_id);
    }

}