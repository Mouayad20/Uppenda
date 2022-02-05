package com.example.demo.Services;

import com.example.demo.Converters.GroupConverter;
import com.example.demo.Converters.PageConverter;
import com.example.demo.Converters.PostConverter;
import com.example.demo.Converters.UserConverter;
import com.example.demo.Entities.GroupEntity;
import com.example.demo.Entities.PageEntity;
import com.example.demo.Entities.PostEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.*;
import com.example.demo.Repositories.GroupRepository;
import com.example.demo.Repositories.PageRepository;
import com.example.demo.Repositories.PostRepositroy;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private PostRepositroy postRepositroy;
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

    public UserModel addUser(UserModel userModel) {
        UserEntity userEntity = userRepository.save(userConverter.convertUserModelToUserEntity(userModel, false));
        return userConverter.convertUserEntityToUserModel(userEntity);
    }

    public ResponseEntity<Object> deleteUserUsingId(long id) {
        Optional<List<PageEntity>> pages = pageRepository.getAllPagesThatUserIsAdimnIn(id);
        if (!pages.isEmpty()) {
            for (PageEntity pageEntity : pages.get()) {
                pageService.delete(pageEntity.getId());
            }
        }
        Optional<List<GroupEntity>> groups = groupRepository.getAllGroupsThatUserIsAdiminIn(id);
        if (!groups.isEmpty()) {
            for (GroupEntity groupEntity : groups.get()) {
                groupService.delete(groupEntity.getId());

            }
        }
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (!userEntity.isEmpty()) {
            if (!userEntity.get().getFriends().isEmpty()) {
                for (int i = 0; i < userEntity.get().getFriends().size(); i++) {
                    deleateFriendFromUser(id, userEntity.get().getFriends().get(i).getId());
                    i -= 1;
                }
            }
            if (!userEntity.get().getPostEntity().isEmpty()) {
                for (int i = 0; i < userEntity.get().getPostEntity().size(); i++) {
                    postService.delete(userEntity.get().getPostEntity().get(i).getId());
                }
            }
            if (!userEntity.get().getSavedPost().isEmpty()) {
                for (int i = 0; i < userEntity.get().getSavedPost().size(); i++) {
                    unSavedPost(userEntity.get().getId(), userEntity.get().getSavedPost().get(i).getId());
                }
            }
            if (!userEntity.get().getSharedPost().isEmpty()) {
                for (int i = 0; i < userEntity.get().getSharedPost().size(); i++) {
                    unSharedPost(userEntity.get().getId(), userEntity.get().getSharedPost().get(i).getId());
                }
            }

            userRepository.deleteById(id);
            return ResponseEntity.ok("user deleated Sucsessfuly");

        } else
            return ResponseEntity.badRequest().body("user is not found");
    }

    public UserModel findById(long id) {
        Optional<UserEntity> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty())
            return null;
        else {
            return userConverter.convertUserEntityToUserModel(foundUser.get());
        }

    }

    public ResponseEntity<String> emailValidation(String email) {
        UserEntity userEntity = userRepository.getByEmail(email);
        if (userEntity != null)
            return ResponseEntity.ok().body("This email is valid");
        else
            return ResponseEntity.ok().body("This email is not valid");
    }

    public UserModel signIn(String email, String password) {
        Optional<UserEntity> userEntity = userRepository.findByEmailAndPassword(email, password);
        if (userEntity.isEmpty()) {
            UserModel userModel = new UserModel();
            return userModel;
        } else
            return userConverter.convertUserEntityToUserModel(userEntity.get());

    }

    public UserModel findByEmail(String email) {
        return userConverter.convertUserEntityToUserModel(userRepository.getByEmail(email));
    }

    public List<UserModel> findByFirstName(String firstName) {
        List<UserEntity> foundUser = userRepository.findByFirstName(firstName);
        List<UserModel> foundModels = new ArrayList<>();
        if (foundUser.isEmpty())
            return null;
        else {
            for (UserEntity userEntity : foundUser) {
                foundModels.add(userConverter.convertUserEntityToUserModel(userEntity));
            }
            return foundModels;
        }
    }

    public UserModel update(UserModel userModel) {
        UserEntity userEntity = userRepository.findById(userModel.getId()).get();
        userEntity.setId(userModel.getId());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setAge(userModel.getAge());
        userEntity.setCreatedAt(userModel.getCreatedAt());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setFirstName(userModel.getFirstName());
        userEntity.setGender(userModel.getGender());
        userEntity.setLastName(userModel.getLastName());
        userEntity.setMobile(userModel.getMobile());
        userEntity.setOnLine(userModel.isOnLine());
        userEntity.setIp(userModel.getIp());
        userEntity.setImagePath(userModel.getImagePath());
        userEntity = userRepository.save(userEntity);
        return userConverter.convertUserEntityToUserModel(userEntity);

    }

    ////////// friends methods

    public UserModel addFriend(long id, long friendId) {
        UserEntity user = userRepository.findById(id).get();
        UserEntity friendEntity = userRepository.findById(friendId).get();
        if (!user.getFriends().contains(friendEntity))
            user.getFriends().add(friendEntity);
        if (!friendEntity.getFriends().contains(user))
            friendEntity.getFriends().add(user);
        user = userRepository.save(user);
        /*--------------error--------------------*/
        userRepository.save(friendEntity);
        return userConverter.convertUserEntityToUserModel(user);

    }

    public UserModel unFriend(Long u_id, Long f_id) {

        UserEntity user = userRepository.findById(u_id).get();
        UserEntity friendEntity = userRepository.findById(f_id).get();
        user.getFriends().remove(friendEntity);
        friendEntity.getFriends().remove(user);
        friendEntity = userRepository.save(friendEntity);
        user = userRepository.save(user);
        return userConverter.convertUserEntityToUserModel(user);

    }

    public UserModel deleateFriendFromUser(long id, long friendId) {
        UserEntity userEntity = userRepository.findById(id).get();
        UserEntity friendEntity = userRepository.findById(friendId).get();
        userEntity.getFriends().remove(friendEntity);
        friendEntity.getFriends().remove(userEntity);
        userEntity = userRepository.save(userEntity);
        friendEntity = userRepository.save(friendEntity);
        return userConverter.convertUserEntityToUserModel(userEntity);

    }

    ////////// page methods

    public PageModel enterIntoPage(long userId, PageEntity pageEntity) {
        UserEntity userEntity = userRepository.findById(userId).get();
        if (!userEntity.getPages().contains(pageEntity)) {
            userEntity.getPages().add(pageEntity);
            userRepository.save(userEntity);
        }
        PageModel pageModel = pageConverter
                .convertPageEntityToPageModel(pageRepository.findById(pageEntity.getId()).get());
        return pageModel;
    }

    public boolean exitFromPage(long id, PageEntity pageEntity) {
        UserEntity userEntity = userRepository.findById(id).get();
        if (userEntity.getPages().contains(pageEntity)) {
            userEntity.getPages().remove(pageEntity);
            userEntity = userRepository.save(userEntity);
            return true;
        } else
            return false;

    }

    ////////// group methods

    public GroupModel enterIntoGroup(long userId, GroupEntity groupEntity) {
        UserEntity userEntity = userRepository.findById(userId).get();
        if (!userEntity.getGroups().contains(groupEntity)) {
            userEntity.getGroups().add(groupEntity);
            userRepository.save(userEntity);
        }
        GroupModel groupModel = groupConverter
                .convertGroupEntityToGroupModel(groupRepository.findById(groupEntity.getId()).get());
        return groupModel;
    }

    public boolean exitFromGroup(long userId, GroupEntity groupEntity) {
        UserEntity userEntity = userRepository.findById(userId).get();
        if (userEntity.getGroups().contains(groupEntity)) {
            userEntity.getGroups().remove(groupEntity);
            userEntity = userRepository.save(userEntity);
            return true;
        } else
            return false;

    }

    ////////// save post methods

    public String savedPost(Long u_id, Long p_id) {

        UserEntity userEntity = userRepository.findById(u_id).get();

        userEntity.getSavedPost().add(postRepositroy.findById(p_id).get());

        UserEntity savedUser = userRepository.save(userEntity);

        PostEntity postEntity = postRepositroy.findById(p_id).get();

        for (int i = 0; i < postEntity.getParticipants().size(); i++) {

            if (postEntity.getParticipants().get(i).getId() == u_id)

                return "This post is saved befor...";

        }

        savedUser.getSavedPost();


        return "This post is save...";

    }

    public boolean unSavedPost(Long u_id, Long p_id) {

        boolean isUnSaved = true;
        UserEntity userEntity = userRepository.findById(u_id).get();

        userEntity.getSavedPost().remove(postRepositroy.findById(p_id).get());

        UserEntity savedUser = userRepository.save(userEntity);

        List<PostEntity> list = savedUser.getSavedPost();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == p_id)
                isUnSaved = false; // the unSave opration is falied

        }

        return isUnSaved;

    }

    public List<PostModel> getAllSavedPostsByUserId(Long u_id) {
        System.out.println("\n\n\n\n\t\t" + userRepository.getALlSavedPost(u_id) + "\t\t\t\n\n\n\n");

        List<Long> list = userRepository.getALlSavedPost(u_id);
        List<PostModel> postModelList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            postModelList.add(postConverter.postEntityToModel(postRepositroy.findById(list.get(i)).get(), true, true, true,
                    true, true));
        }
        return postModelList;
    }

    ////////// share post methods

    public String sharedPost(Long u_id, Long p_id) {


        UserEntity userEntity = userRepository.findById(u_id).get();

        userEntity.getSharedPost().add(postRepositroy.findById(p_id).get());

        PostEntity postEntity = postRepositroy.findById(p_id).get();

        for (int i = 0; i < postEntity.getParticipants().size(); i++) {

            if (postEntity.getParticipants().get(i).getId() == u_id)

                return "This post is shared befor...";

        }

        userRepository.save(userEntity);

        return "This post is share...";
    }

    public boolean unSharedPost(Long u_id, Long p_id) {

        boolean isUnShared = true;
        UserEntity userEntity = userRepository.findById(u_id).get();


        userEntity.getSharedPost().remove(postRepositroy.findById(p_id).get());

        UserEntity savedUser = userRepository.save(userEntity);

        List<PostEntity> list = savedUser.getSharedPost();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == p_id)
                isUnShared = false; // the unShare opration is falied

        }
        return isUnShared;
    }

    public List<PostModel> getAllSharedPostsByUserId(Long u_id) {
        System.out.println("\n\n\n\n\t\t" + userRepository.getALlSharedPost(u_id) + "\t\t\t\n\n\n\n");

        List<Long> list = userRepository.getALlSharedPost(u_id);
        List<PostModel> postModelList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            postModelList.add(postConverter.postEntityToModel(postRepositroy.findById(list.get(i)).get(), true, true, true,
                    true, true));
        }
        return postModelList;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(">>>>>>>  \t\t " + email);
        UserModel userEntity = findByEmail(email);
        return new SignInModel(userEntity.getEmail(), userEntity.getPassword());
    }

}
