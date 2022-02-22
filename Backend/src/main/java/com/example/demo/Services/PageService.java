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
import java.util.Optional;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepositroy;
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
        PageEntity pageEntity = new PageEntity();
        pageEntity.setId(pageModel.getId());
        pageEntity.setDescription(pageModel.getDescription());
        pageEntity.setImgPath(pageModel.getImgPath());
        pageEntity.setName(pageModel.getName());
        pageEntity = pageRepository.save(pageEntity);

        return pageConverter.convertPageEntityToPageModel(pageEntity);
    }

    public ResponseEntity<Object> delete(long id) {
        Optional<PageEntity> pageEntity = pageRepository.findById(id);
        if (!pageEntity.isEmpty()) {
            for (UserEntity userEntity : pageEntity.get().getMembers()) {
                userService.exitFromPage(userEntity.getId(), pageEntity.get());
            }
            pageRepository.deleteById(id);
            if (pageRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("deleat operation not allawed");
            else
                return ResponseEntity.ok("sucsessfuly deleated");
        } else
            return ResponseEntity.ok("page is not found");
    }

    public PageModel addMember(long id, long userId) {
        PageEntity pageEntity = pageRepository.findById(id).get();
        return userService.enterIntoPage(userId, pageEntity);
    }

    public PageModel deleteMember(long id, long memberId) {
        PageEntity pageEntity = pageRepository.findById(id).get();
        userService.exitFromPage(memberId, pageEntity);
        return pageConverter.convertPageEntityToPageModel(pageEntity);
    }

    public PageModel getPageById(Long id) {
        return pageConverter.convertPageEntityToPageModel(pageRepository.findById(id).get());
    }

    public List<PageModel> getAllPages() {
        List<PageModel> pages = new ArrayList<>();
        for (PageEntity pageEntity : pageRepository.getAllPages()) {
            pages.add(pageConverter.convertPageEntityToPageModel(pageEntity));
        }
        return pages;
    }

    public PageModel changeAdmin(long id, long adminId) {
        Optional<PageEntity> pageEntity = pageRepository.findById(id);
        Optional<UserEntity> userEntity = userRepository.findById(adminId);
        PageEntity savedEntity = new PageEntity();
        ;
        if (!pageEntity.isEmpty() && !userEntity.isEmpty()) {
            if (pageEntity.get().getAdmin().getId() != userEntity.get().getId()) {
                pageEntity.get().setAdmin(userEntity.get());
                savedEntity = pageRepository.save(pageEntity.get());
            }
        }
        return pageConverter.convertPageEntityToPageModel(savedEntity);
    }

    public List<PostModel> fetchAllPostFromPageById(Long p_id) {
        return postConverter.postEntityListToModelList(postRepositroy.getPagesPosts(p_id), true, true, false, false, true);
    }

    public List<UserModel> fetchAllUserFromPageByPId(Long p_id) {
        return userConverter.convertUserListEntityToListModel(pageRepository.findById(p_id).get().getMembers());
    }

    public List<PageModel> search(String word) {
        return pageConverter.convertPageEntityListToPageModelList(pageRepository.searchPage(word));
    }

}