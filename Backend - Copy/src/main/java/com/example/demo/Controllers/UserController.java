package com.example.demo.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.Configurations.AuthRequest;
import com.example.demo.Configurations.TokenUtil;
import com.example.demo.DemoApplication;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.MyUserDetails;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.services.FormatFactory;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FormatFactory formatFactory;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenUtil tokenUtil;

    @PostMapping(path = "/signIn")
    public Map<String, Object> signIn(@RequestBody AuthRequest authRequest) throws Exception {

        System.out.println("\n\t email  : " + authRequest.getEmail());
        System.out.println("\n\t pass   : " + authRequest.getPassword());

        ///////////////////////////////////////////////////////////////////////////

        userService = new UserService();

        UserEntity uouo = userRepository.findByEmail(authRequest.getEmail()).get();

        UserDetails userDetails = new MyUserDetails(uouo.getEmail(), uouo.getPassword());

        String token = tokenUtil.generateTokenByEmail(userDetails.getUsername());

        Map<String, Object> tokenWithUser = new HashMap<>();

        tokenWithUser.put("userModel", uouo);

        tokenWithUser.put("token", String.valueOf(token));

        // final Authentication authentication = authenticationManager.authenticate(
        // new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
        // authRequest.getPassword()));
        // SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenWithUser;
    }

    @PostMapping(path = "/updateUser")
    public Map<String, Object> updateUserInforamtion(@RequestBody(required = true) UserModel userModel) {
        userModel = userService.updateUserInformation(userModel);
        Map<String, Object> tokenWithUser = new HashMap<>();
        final MyUserDetails user = (MyUserDetails) userService.loadUserByUsername(userModel.getEmail());
        final String token = tokenUtil.generateTokenByEmail(user.getUsername());
        tokenWithUser.put("token", token);
        tokenWithUser.put("userModel", userModel);
        return tokenWithUser;
    }

    @PostMapping("/addUser")
    public Map<String, Object> addUser(@RequestBody UserModel userModel) {
        userModel = userService.addUser(userModel);
        Map<String, Object> tokenWithUser = new HashMap<>();
        final MyUserDetails user = new MyUserDetails(userModel.getEmail(), userModel.getPassword());
        final String token = tokenUtil.generateTokenByEmail(user.getUsername());
        tokenWithUser.put("token", token);
        tokenWithUser.put("userModel", userModel);
        return tokenWithUser;

    }

    //////////////////////////////////////////////////

    @GetMapping(path = "/getFormat")
    public String getFormat() {
        System.out.println("\n\n\n\t\t\tGetFormat\n\n");
        UserModel userModel = new UserModel();
        try {
            return DemoApplication.objectMapper.writeValueAsString(userModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "not existed";
    }

    @GetMapping(path = "/getUser/Id={id}")
    public UserModel findById(@PathVariable(name = "id", required = true) long id) {
        return userService.findById(id);
    }

    @GetMapping(path = "/getUser/Name={name}")
    public List<UserModel> findByFirstName(@PathVariable(name = "name", required = true) String firstName) {
        return userService.findByFirstName(firstName);
    }

    // @PostMapping(path = "/updateUser")
    // public UserModel updateUserInforamtion(@RequestBody(required = true)UserModel
    // userModel){
    // return userService.updateUserInformation(userModel);
    // }

    @PostMapping(path = "/deleatById")
    public ResponseEntity<Object> deleatById(@RequestBody(required = false) long id) {
        return userService.deleteUserUsingId(id);
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
        return formatFactory.convertUserEntityToUserModel(new UserEntity());
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

    @PostMapping(path = "/checkValidations/Email")
    public ResponseEntity<String> emailValidation(@RequestBody String email) {
        return userService.emailValidation(email);
    }

    @GetMapping(path = "/search/word={word}")
    public List<UserModel> search(@PathVariable String word) {
        return formatFactory.convertUserListEntityToListModel(userRepository.searchUser(word));
    }

}