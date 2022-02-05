package com.example.demo.Controllers;

import com.example.demo.Converters.UserConverter;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.SignInModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Security.TokenUtil;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    UserConverter userConverter;

    /* Post Request */

    @PostMapping(path = "/signIn")
    public Map<String, Object> signIn(@RequestBody SignInModel signInModel) throws Exception {

        System.out.println("\n\t email  : " + signInModel.getEmail());
        System.out.println("\n\t pass   : " + signInModel.getPassword());

        ///////////////////////////////////////////////////////////////////////////

        userService = new UserService();

        UserEntity uouo = userRepository.findByEmail(signInModel.getEmail()).get();

        UserDetails userDetails = new SignInModel(uouo.getEmail(), uouo.getPassword());

        String token = tokenUtil.generateToken(userDetails.getUsername());

        Map<String, Object> tokenWithUser = new HashMap<>();

        tokenWithUser.put("userModel", uouo);

        tokenWithUser.put("token", String.valueOf(token));

        // final Authentication authentication = authenticationManager.authenticate(
        // new UsernamePasswordAuthenticationToken(signInModel.getEmail(),
        // signInModel.getPassword()));
        // SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenWithUser;
    }

    @PostMapping("/signUp")
    public Map<String, Object> signUp(@RequestBody UserModel userModel) {
        userModel = userService.addUser(userModel);
        Map<String, Object> tokenWithUser = new HashMap<>();
        final SignInModel user = new SignInModel(userModel.getEmail(), userModel.getPassword());
        final String token = tokenUtil.generateToken(user.getUsername());
        tokenWithUser.put("token", token);
        tokenWithUser.put("userModel", userModel);
        return tokenWithUser;

    }

    @PostMapping(path = "/checkValidations/Email")
    public ResponseEntity<String> emailValidation(@RequestBody String email) {
        return userService.emailValidation(email);
    }

    /* Put Request */

    @PutMapping(path = "/update")
    public Map<String, Object> update(@RequestBody(required = true) UserModel userModel) {
        userModel = userService.update(userModel);
        Map<String, Object> tokenWithUser = new HashMap<>();
        final SignInModel user = (SignInModel) userService.loadUserByUsername(userModel.getEmail());
        final String token = tokenUtil.generateToken(user.getUsername());
        tokenWithUser.put("token", token);
        tokenWithUser.put("userModel", userModel);
        return tokenWithUser;
    }

    /* Delete Request */

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<Object> deleteById(@RequestBody(required = false) long id) {
        return userService.deleteUserUsingId(id);
    }

    /* Get Request */

    @GetMapping(path = "/search/word={word}")
    public List<UserModel> search(@PathVariable String word) {
        return userConverter.convertUserListEntityToListModel(userRepository.searchUser(word));
    }

    @GetMapping(path = "/getFormat")
    public UserModel getFormat() {
        Date date = new Date();
        UserModel userModel = new UserModel();
        userModel.setCreatedAt(date);
        return userModel;
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