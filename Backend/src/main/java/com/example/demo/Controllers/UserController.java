package com.example.demo.Controllers;

import com.example.demo.Converters.UserConverter;
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
    public ResponseEntity<Object> signIn(@RequestBody SignInModel signInModel) {
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
    public List<UserModel> search(@PathVariable(name = "word") String word) {
        return userService.search(word);
    }

    @GetMapping(path = "/getUserInformation")
    public ResponseEntity<Object> getUserInformation(@RequestHeader("Authorization") String token) {
        return userService.getUserInformation(token.substring("Bearer ".length()));
    }

    @GetMapping(path = "/getUser/Id={id}")
    public ResponseEntity<Object> findById(@PathVariable(name = "id", required = true) long id) {
        return userService.findById(id);
    }

    @GetMapping(path = "/getUser/Email={email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(name = "email", required = true) String email) {
        return userService.findByEmail(email);
    }

    @GetMapping(path = "/addFriend/friendEmail={fEmail}")
    public ResponseEntity<String> addFriend(@RequestHeader("Authorization") String token,
                                            @PathVariable(name = "fEmail", required = true) String fEmail) {
        return userService.addFriend(token.substring("Bearer ".length()), fEmail);
    }

    @GetMapping(path = "/unFriend/friendEmail={fEmail}")
    public ResponseEntity<String> unFriend(@RequestHeader("Authorization") String token,
                                           @PathVariable(name = "fEmail", required = true) String fEmail) {
        return userService.unFriend(token.substring("Bearer ".length()), fEmail);
    }

    @GetMapping(path = "/savePost/postId={post_id}")
    public ResponseEntity<String> savePost(@RequestHeader("Authorization") String token,
                                           @PathVariable(name = "post_id", required = true) Long post_id) {
        return userService.savedPost(token.substring("Bearer ".length()), post_id);
    }

    @GetMapping(path = "/unSavePost/postId={post_id}")
    public ResponseEntity<String> unSavePost(@RequestHeader("Authorization") String token,
                                             @PathVariable(name = "post_id", required = true) Long post_id) {
        return userService.unSavedPost(token.substring("Bearer ".length()), post_id);
    }

    @GetMapping(path = "/getAllSavedPosts")
    public List<PostModel> getAllSavedPosts(@RequestHeader("Authorization") String token) {
        return userService.getAllSavedPosts(token.substring("Bearer ".length()));
    }

    @GetMapping(path = "/sharePost/postId={post_id}")
    public ResponseEntity<String> sharePost(@RequestHeader("Authorization") String token,
                                            @PathVariable(name = "post_id", required = true) Long post_id) {
        return userService.sharedPost(token.substring("Bearer ".length()), post_id);
    }

    @GetMapping(path = "/unSharePost/postId={post_id}")
    public ResponseEntity<String> unSharePost(@RequestHeader("Authorization") String token,
                                              @PathVariable(name = "post_id", required = true) Long post_id) {
        return userService.unSharedPost(token.substring("Bearer ".length()), post_id);
    }

    @GetMapping(path = "/getAllSharedPosts")
    public List<PostModel> getAllSharedPosts(@RequestHeader("Authorization") String token) {
        return userService.getAllSharedPosts(token.substring("Bearer ".length()));
    }

}