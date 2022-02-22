package com.example.demo.Services;

import com.example.demo.Converters.GroupConverter;
import com.example.demo.Converters.PageConverter;
import com.example.demo.Converters.PostConverter;
import com.example.demo.Converters.UserConverter;
import com.example.demo.Entities.PostEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.SignInModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.GroupRepository;
import com.example.demo.Repositories.PageRepository;
import com.example.demo.Repositories.PostRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private PostRepository postRepositroy;
    @Autowired
    private GroupService groupService;
    @Autowired
    private PageService pageService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private PageConverter pageConverter;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private GroupConverter groupConverter;
    @Autowired
    private TokenUtil tokenUtil;

    public ResponseEntity<Object> signUp(UserModel userModel) {

        if (userRepository.findByEmail(userModel.getEmail()) != null && userRepository.findByEmail(userModel.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("This email is used");
        } else if (userRepository.findByPassword(userModel.getPassword()) != null && userRepository.findByPassword(userModel.getPassword()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Enter another password!!");
        } else {
            UserEntity userEntity =
                    userRepository.save(userConverter.convertUserModelToUserEntity(userModel, false));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Authorization", "Bearer " + tokenUtil.generateToken(userEntity.getEmail()))
                    .body(userConverter.convertUserEntityToUserModel(userEntity));
        }

    }

    public ResponseEntity<Object> signIn(SignInModel signInModel) {
        UserEntity userEntity;
        if (userRepository.findByEmail(signInModel.getEmail()).isPresent()) {
            userEntity = userRepository.findByEmail(signInModel.getEmail()).get();
            if (userRepository.findByPassword(signInModel.getPassword()).isPresent() &&
                    userEntity.getId() == userRepository.findByPassword(signInModel.getPassword()).get().getId()) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Authorization", "Bearer " + tokenUtil.generateToken(userEntity.getEmail()))
                        .body(userConverter.convertUserEntityToUserModel(userEntity));
            } else return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("this password is wrong");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Email is not exist");
        }
    }

    public ResponseEntity<Object> update(UserModel userModel) {
        if (userRepository.findById(userModel.getId()).isPresent()) {
            UserEntity userEntity = userRepository.findById(userModel.getId()).get();
            if (userRepository.findByEmail(userModel.getEmail()).isPresent() && userRepository.findByEmail(userModel.getEmail()).get().getId() != userModel.getId()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_ACCEPTABLE)
                        .body("This email is used from another user");
            } else if (userRepository.findByPassword(userModel.getPassword()).isPresent() && userRepository.findByPassword(userModel.getPassword()).get().getId() != userModel.getId()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_ACCEPTABLE)
                        .body("Enter another password this is used before");
            } else {

                userEntity.setId(userModel.getId());
                userEntity.setEmail(userModel.getEmail());
                userEntity.setPassword(userModel.getPassword());
                userEntity.setFirstName(userModel.getFirstName());
                userEntity.setLastName(userModel.getLastName());
                userEntity.setMobile(userModel.getMobile());
                userEntity.setStudyLevel(userModel.getStudyLevel());
                userEntity.setLocation(userModel.getLocation());
                userEntity.setGender(userModel.getGender());
                userEntity.setImagePath(userModel.getImagePath());
                userEntity.setIp(userModel.getIp());
                userEntity.setAge(userModel.getAge());

                UserEntity savedEntity = userRepository.save(userEntity);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Authorization", "Bearer " + tokenUtil.generateToken(userEntity.getEmail()))
                        .body(userConverter.convertUserEntityToUserModel(savedEntity));
            }
        } else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("this user is not found");

    }

    public ResponseEntity<String> delete(String token) {
        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();
        userRepository.delete(userEntity);
        if (!userRepository.findById(userEntity.getId()).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("User deleted successfully ");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("User not deleted, problem happened");

    }

    public List<UserModel> search(String word) {
        return userConverter.convertUserListEntityToListModel(userRepository.searchUser(word));
    }

    public ResponseEntity<Object> getUserInformation(String token) {
        if (userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userConverter.convertUserEntityToUserModel(userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
        }
    }

    public ResponseEntity<Object> findById(long id) {
        if (!userRepository.findById(id).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("There is no user have this id ");
        } else {
            UserEntity foundUser = userRepository.findById(id).get();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userConverter.convertUserEntityToUserModel(foundUser));
        }
    }

    public ResponseEntity<Object> findByEmail(String email) {
        if (!userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("There is no user have this id ");
        } else {
            UserEntity foundUser = userRepository.findByEmail(email).get();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userConverter.convertUserEntityToUserModel(foundUser));
        }
    }

    ////////// friends methods

    public ResponseEntity<String> addFriend(String token, String fEmail) {

        UserEntity user = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();
        UserEntity friendEntity = userRepository.findByEmail(fEmail).get();

        if (!user.getFriends().contains(friendEntity))
            user.getFriends().add(friendEntity);
        if (!friendEntity.getFriends().contains(user))
            friendEntity.getFriends().add(user);

        user = userRepository.save(user);
        friendEntity = userRepository.save(friendEntity);

        if (user.getFriends().contains(friendEntity) && friendEntity.getFriends().contains(user))
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Friend added");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
    }

    public ResponseEntity<String> unFriend(String token, String fEmail) {

        UserEntity user = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();
        UserEntity friendEntity = userRepository.findByEmail(fEmail).get();

        user.getFriends().remove(friendEntity);
        friendEntity.getFriends().remove(user);

        user = userRepository.save(user);
        friendEntity = userRepository.save(friendEntity);

        if (!(user.getFriends().contains(friendEntity) && friendEntity.getFriends().contains(user)))
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Friend removed");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
    }

    ////////// save post methods

    public ResponseEntity<String> savedPost(String token, Long post_id) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();

        if (!postRepositroy.findById(post_id).isPresent())
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("This post is not exist");

        PostEntity postEntity = postRepositroy.findById(post_id).get();

        userEntity.getSavedPost().add(postEntity);
        postEntity.getSavers().add(userEntity);

        UserEntity savedUser = userRepository.save(userEntity);
        PostEntity savedPost = postRepositroy.save(postEntity);

        if (savedUser.getSavedPost().contains(savedPost))
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Post Saved");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");

    }

    public ResponseEntity<String> unSavedPost(String token, Long post_id) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();

        if (!postRepositroy.findById(post_id).isPresent())
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("This post is not exist");

        PostEntity postEntity = postRepositroy.findById(post_id).get();

        userEntity.getSavedPost().remove(postEntity);
        postEntity.getSavers().remove(userEntity);

        UserEntity savedUser = userRepository.save(userEntity);
        PostEntity savedPost = postRepositroy.save(postEntity);

        if (!savedUser.getSavedPost().contains(savedPost))
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Post unSaved");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
    }

    public List<PostModel> getAllSavedPosts(String token) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();

        List<Long> list = userRepository.getALlSavedPost(userEntity.getId());
        List<PostModel> postModelList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            postModelList.add(postConverter.postEntityToModel(postRepositroy.findById(list.get(i)).get(),
                    true, true, true, true, true));
        }
        return postModelList;
    }

    ////////// share post methods

    public ResponseEntity<String> sharedPost(String token, Long post_id) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();

        if (!postRepositroy.findById(post_id).isPresent())
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("This post is not exist");

        PostEntity postEntity = postRepositroy.findById(post_id).get();

        userEntity.getSharedPost().add(postEntity);
        postEntity.getParticipants().add(userEntity);

        UserEntity savedUser = userRepository.save(userEntity);
        PostEntity sharedPost = postRepositroy.save(postEntity);

        if (savedUser.getSharedPost().contains(sharedPost))
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Post shared");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
    }

    public ResponseEntity<String> unSharedPost(String token, Long post_id) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();

        if (!postRepositroy.findById(post_id).isPresent())
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("This post is not exist");

        PostEntity postEntity = postRepositroy.findById(post_id).get();

        userEntity.getSharedPost().remove(postEntity);
        postEntity.getParticipants().remove(userEntity);

        UserEntity savedUser = userRepository.save(userEntity);
        PostEntity sharedPost = postRepositroy.save(postEntity);

        if (!savedUser.getSharedPost().contains(sharedPost))
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Post unShared");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");

    }

    public List<PostModel> getAllSharedPosts(String token) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();

        List<Long> list = userRepository.getALlSharedPost(userEntity.getId());
        List<PostModel> postModelList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            postModelList.add(postConverter.postEntityToModel(postRepositroy.findById(list.get(i)).get(),
                    true, true, true, true, true));
        }
        return postModelList;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(">>>>>>>  \t\t " + email);
        UserModel userModel = userConverter.convertUserEntityToUserModel(userRepository.findByEmail(email).get());
        return new SignInModel(userModel.getEmail(), userModel.getPassword());
    }
}
