package com.example.demo.Services;

import com.example.demo.Converters.PageConverter;
import com.example.demo.Converters.PostConverter;
import com.example.demo.Converters.UserConverter;
import com.example.demo.Entities.PageEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.PageModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.PageRepository;
import com.example.demo.Repositories.PostRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PageConverter pageConverter;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private TokenUtil tokenUtil;

    public ResponseEntity<String> add(PageModel pageModel, String token) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();
        PageEntity pageEntity = pageConverter.convertPageModelToPageEntity(pageModel);

        pageEntity.setAdmin(userEntity);
        pageEntity.getMembers().add(userEntity);

        pageEntity = pageRepository.save(pageEntity);
        userEntity.getPages().add(pageEntity);
        userRepository.save(userEntity);

        if (pageRepository.findById(pageEntity.getId()).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Page created successfully");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
    }

    public PageModel update(PageModel pageModel) {
        PageEntity pageEntity = pageRepository.findById(pageModel.getId()).get();

        pageEntity.setName(pageModel.getName());
        pageEntity.setDescription(pageModel.getDescription());
        pageEntity.setImgPath(pageModel.getImgPath());

        pageEntity = pageRepository.save(pageEntity);
        return pageConverter.convertPageEntityToPageModel(pageEntity);
    }

    public ResponseEntity<Object> delete(long id) {
        pageRepository.deleteById(id);
        if (!pageRepository.findById(id).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Page deleted successfully ");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Page not deleted, problem happened");
    }

    public ResponseEntity<String> addMember(Long pageId, Long memberId) {

        PageEntity pageEntity = pageRepository.findById(pageId).get();
        UserEntity userEntity = userRepository.findById(memberId).get();

        if (!pageEntity.getMembers().contains(userEntity)) {
            pageEntity.getMembers().add(userEntity);
        }
        PageEntity savedPage = pageRepository.save(pageEntity);

        if (savedPage.getMembers().contains(userEntity))
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Member added successfully");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
    }

    public ResponseEntity<String> deleteMember(Long pageId, Long memberId) {

        PageEntity pageEntity = pageRepository.findById(pageId).get();
        UserEntity userEntity = userRepository.findById(memberId).get();

        if (pageEntity.getMembers().contains(userEntity)) {
            pageEntity.getMembers().remove(userEntity);
        }

        PageEntity savedPage = pageRepository.save(pageEntity);

        if (!savedPage.getMembers().contains(userEntity))
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Member removed successfully");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");
    }

    public ResponseEntity<Object> getPage(Long id) {
        if (pageRepository.findById(id).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(pageConverter.convertPageEntityToPageModel(pageRepository.findById(id).get()));
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("This Page not exist");
    }

    public List<PageModel> getAll() {
        List<PageModel> pages = new ArrayList<>();
        for (PageEntity pageEntity : pageRepository.getAll()) {
            pages.add(pageConverter.convertPageEntityToPageModel(pageEntity));
        }
        return pages;
    }

    public List<PostModel> getPagePosts(Long page_id) {
        return postConverter.postEntityListToModelList(postRepository.getPagePosts(page_id),
                true, true, false, false, true);
    }

    public List<UserModel> getPageUsers(Long page_id) {
        return userConverter.convertUserListEntityToListModel(pageRepository.findById(page_id).get().getMembers());
    }

    public List<PageModel> search(String word) {
        return pageConverter.convertPageEntityListToPageModelList(pageRepository.search(word));
    }

}