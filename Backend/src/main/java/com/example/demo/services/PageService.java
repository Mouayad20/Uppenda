package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entities.PageEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.PageModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.PageRepository;
import com.example.demo.Repositories.PostRepositroy;
import com.example.demo.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    @Autowired
    PageRepository pageRepository;
    @Autowired
    FormatFactory formatFactory;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepositroy postRepositroy;
    @Autowired
    UserService userService;

    public PageModel addPage(PageModel pageModel, long adminId) {
        UserEntity userEntity = userRepository.findById(adminId).get();
        PageEntity pageEntity = new PageEntity();
        pageEntity = formatFactory.convertPageModelToPageEntity(pageModel, userEntity, false);
        // pageEntity.setCreatedAt(new Date());
        pageEntity = pageRepository.save(pageEntity);
        addMemberToPage(pageEntity.getId(), adminId);
        for (int i = 0; i < pageModel.getMembers().size(); i++) {
            addMemberToPage(pageEntity.getId(), pageModel.getMembers().get(i).getId());
        }
        return formatFactory.convertPageEntityToPageModel(pageEntity);
    }

    public PageModel addMemberToPage(long id, long userId) {
        PageEntity pageEntity = pageRepository.findById(id).get();
        return userService.enterIntoPage(userId, pageEntity);
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
        return formatFactory.convertPageEntityToPageModel(savedEntity);
    }

    public PageModel deleteMemberFromPage(long id, long memberId) {
        PageEntity pageEntity = pageRepository.findById(id).get();
        userService.exitFromPage(memberId, pageEntity);
        return formatFactory.convertPageEntityToPageModel(pageEntity);
    }

    public ResponseEntity<Object> deleteById(long id) {
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

    public List<PageModel> getAllPages() {
        List<PageModel> pages = new ArrayList<>();
        for (PageEntity pageEntity : pageRepository.getAllPages()) {
            pages.add(formatFactory.convertPageEntityToPageModel(pageEntity));
        }
        return pages;
    }

    public PageModel updatePageInformation(PageModel pageModel) {
        PageEntity pageEntity = new PageEntity();
        pageEntity.setId(pageModel.getId());
        pageEntity.setDescreption(pageModel.getDescription());
        pageEntity.setImgPath(pageModel.getImgPath());
        pageEntity.setName(pageModel.getName());
        pageEntity = pageRepository.save(pageEntity);

        return formatFactory.convertPageEntityToPageModel(pageEntity);
    }

    public List<PostModel> fetchAllPostFromPageById(Long p_id) {
        return formatFactory.postEntityListToModelList(postRepositroy.getAllPostByPageId(p_id),true,true,false,false,true);
    }

    public List<UserModel> fetchAllUserFromPageByPId(Long p_id) {
        return formatFactory.convertUserListEntityToListModel(pageRepository.findById(p_id).get().getMembers());
    }

}