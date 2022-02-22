package com.example.demo.Services;

import com.example.demo.Converters.MediaConverter;
import com.example.demo.Converters.PostConverter;
import com.example.demo.Converters.TypeConverter;
import com.example.demo.Converters.UserConverter;
import com.example.demo.Entities.GroupEntity;
import com.example.demo.Entities.PageEntity;
import com.example.demo.Entities.PostEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.PostModel;
import com.example.demo.Repositories.*;
import com.example.demo.Security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepositroy;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private TypeConverter typeConverter;
    @Autowired
    private MediaConverter mediaConverter;
    @Autowired
    private TokenUtil tokenUtil;

    public ResponseEntity<String> addProfilePost(PostModel postModel, String token) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();

        PostEntity postEntity = postConverter.postModelToEntity(postModel);

        postEntity.setUserEntity(userEntity);
        userEntity.getPostEntities().add(postEntity);

        PostEntity savedPost = postRepositroy.save(postEntity);
        userRepository.save(userEntity);

        if (postRepositroy.findById(savedPost.getId()).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Post added successfully ");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Post not added, problem happened");


//        List<MediaModel> list = new ArrayList<>();
//        if (postModel.getMedia() != null && !postModel.getMedia().isEmpty()) {
//            for (MediaModel mediaModel : postModel.getMedia()) {
//                MediaEntity mediaEntity = new MediaEntity();
//                mediaEntity.setPath(mediaModel.getPath());
//                mediaEntity.setType(mediaModel.getType());
//                mediaEntity.setPostEntity(savedPost);
//                MediaModel m = mediaConverter.convertMediaEntityToMediaModel(mediaRepository.save(mediaEntity));
//                list.add(m);
//
//            }
//        }
//        p.setId(savedPost.getId());
//        p.setContent(savedPost.getContent());
//        p.setCreatedAt(savedPost.getCreatedAt());
//        p.setUserModel(userConverter.getUserModelWithBasicInformation(savedPost.getUserEntity()));
//        p.setType(typeConverter.typeEntityToModel(savedPost.getType()));
//        p.setMedia(list);
    }

    public ResponseEntity<String> addGroupPost(PostModel postModel, String token, Long group_id) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();
        GroupEntity groupEntity = groupRepository.findById(group_id).get();
        PostEntity postEntity = postConverter.postModelToEntity(postModel);

        postEntity.setUserEntity(userEntity);
        postEntity.setGroupEntity(groupEntity);

        PostEntity savedPost = postRepositroy.save(postEntity);
        userEntity.getPostEntities().add(savedPost);
        userRepository.save(userEntity);

        if (postRepositroy.findById(savedPost.getId()).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Post added successfully ");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Post not added, problem happened");

    }

    public ResponseEntity<String> addPagePost(PostModel postModel, String token, Long page_id) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();
        PageEntity pageEntity = pageRepository.findById(page_id).get();
        PostEntity postEntity = postConverter.postModelToEntity(postModel);

        postEntity.setUserEntity(userEntity);
        postEntity.setPageEntity(pageEntity);

        PostEntity savedPost = postRepositroy.save(postEntity);
        userEntity.getPostEntities().add(savedPost);
        userRepository.save(userEntity);

        if (postRepositroy.findById(savedPost.getId()).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Post added successfully ");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Post not added, problem happened");


    }

    public PostModel update(PostModel postModel) {
        PostEntity postEntity = postRepositroy.findById(postModel.getId()).get();
        postEntity.setContent(postModel.getContent());
        postEntity.setCreatedAt(new Date());
        postEntity.setType(typeConverter.typeModelToEntity(postModel.getType()));
        return postConverter.postEntityToModel(postRepositroy.save(postEntity), true, true, true, true, true);
    }

    public ResponseEntity<String> delete(Long id) {
        postRepositroy.deleteById(id);
        if (!postRepositroy.findById(id).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Post deleted successfully ");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Post not deleted, problem happened");
    }

    public PostModel getByID(Long id) {
        Optional<PostEntity> postEntity = postRepositroy.findById(id);
        return postConverter.postEntityToModel(postEntity.get(), true, true, false, false, true);
    }

    public List<PostModel> getAll() {
        return postConverter.postEntityIterableToModelList(postRepositroy.findAll(), true, true);
    }

    public List<PostModel> getUserPosts(String email) {
        return postConverter.postEntityListToModelList(postRepositroy.getUserPosts(userRepository.findByEmail(email).get().getId()),
                true, true, true, true, true);
    }

    public List<PostModel> getGroupsPosts(Long id) {
        return postConverter.postEntityListToModelList(postRepositroy.getGroupsPosts(id),
                true, true, true, true, true);
    }

    public List<PostModel> getPagesPosts(Long id) {
        return postConverter.postEntityListToModelList(postRepositroy.getPagesPosts(id), true, true, true, true, true);
    }

    public List<PostModel> getSummery(String token) {
        return postConverter.postEntityListToModelList(postRepositroy.getSummery(userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get().getId()),
                true, true, true, true, true);
    }
}