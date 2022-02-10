package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entities.GroupEntity;
import com.example.demo.Entities.MediaEntity;
import com.example.demo.Entities.PageEntity;
import com.example.demo.Entities.PostEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.MediaModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepositroy postRepositroy;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    PageRepository pageRepository;
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    FormatFactory formatFactory;

    public PostModel addProfilePost(PostModel postModel, Long u_id) {

        UserEntity userEntity = userRepository.findById(u_id).get();

        PostEntity postEntity = formatFactory.postModelToEntity(postModel);
        postEntity.setUserEntity(userEntity);

        PostModel p = new PostModel();
        List<MediaModel> lista = new ArrayList<>();
        
        PostEntity savedPost = postRepositroy.save(postEntity);
        if (postModel.getMedia() != null && !postModel.getMedia().isEmpty()) {
            for (MediaModel mediaModel : postModel.getMedia()) {

                MediaEntity mediaEntity = new MediaEntity();
                mediaEntity.setPath(mediaModel.getPath());
                mediaEntity.setType(mediaModel.getType());
                mediaEntity.setPostEntity(savedPost);
                MediaModel m = formatFactory.convertMediaEntityToMediaModel(mediaRepository.save(mediaEntity));
                lista.add(m);

            }
        }

        p.setId(savedPost.getId());
        p.setContent(savedPost.getContent());
        p.setCreatedAt(savedPost.getCreatedAt());
        p.setUserModel(formatFactory.getUserModelWithBasicInformation(savedPost.getUserEntity()));
        p.setType(formatFactory.typeEntityToModel(savedPost.getType()));
        p.setMedia(lista);

        return p ;

    }

    public PostModel findById(Long id) {
        Optional<PostEntity> postEntity = postRepositroy.findById(id);
        return formatFactory.postEntityToModel(postEntity.get(), true, true, false, false, true);
    }

    public PostModel addGroupPost(PostModel postModel, Long u_id, Long g_id) {

        UserEntity userEntity = userRepository.findById(u_id).get();
        GroupEntity groupEntity = groupRepository.findById(g_id).get();
        PostEntity postEntity = formatFactory.postModelToEntity(postModel);
        postEntity.setUserEntity(userEntity);
        postEntity.setGroupEntity(groupEntity);
        return formatFactory.postEntityToModel(postRepositroy.save(postEntity), false, false, true, true, true);

    }

    public PostModel addPagePost(PostModel postModel, Long u_id, Long p_id) {

        UserEntity userEntity = userRepository.findById(u_id).get();
        PageEntity pageEntity = pageRepository.findById(p_id).get();
        PostEntity postEntity = formatFactory.postModelToEntity(postModel);
        postEntity.setUserEntity(userEntity);
        postEntity.setPageEntity(pageEntity);
        return formatFactory.postEntityToModel(postRepositroy.save(postEntity), false, false, false, false, false);

    }

    public PostModel deleteById(Long id) {
        PostEntity postEntity = postRepositroy.findById(id).get();
        if (!postEntity.getParticipants().isEmpty()) {
            for (int i = 0; i < postEntity.getParticipants().size(); i++) {
                userService.unSharedPost(postEntity.getParticipants().get(i).getId(), id);
            }
            for (int i = 0; i < postEntity.getSavers().size(); i++) {
                userService.unSavedPost(postEntity.getSavers().get(i).getId(), id);
            }
        }
        postRepositroy.deleteById(id);
        return formatFactory.postEntityToModel(postEntity, false, false, false, false, false);
    }

    public PostModel update(PostModel postModel) {
        PostEntity postEntity = postRepositroy.findById(postModel.getId()).get();
        postEntity.setContent(postModel.getContent());
        postEntity.setCreatedAt(new Date());
        postEntity.setType(formatFactory.typeModleToEntity(postModel.getType()));
        return formatFactory.postEntityToModel(postRepositroy.save(postEntity), true, true, true, true, true);
    }

    public List<PostModel> fetch() {
        return formatFactory.postEntityIterableToModelList(postRepositroy.findAll(), true, true);
    }

}
