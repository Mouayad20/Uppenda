package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.Converters.PageConverter;
import com.example.demo.Entities.PageEntity;
import com.example.demo.Models.PageModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.PageRepository;
import com.example.demo.services.PageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "pages")
public class PageController {

    @Autowired
    PageService pageService;
    @Autowired
    PageRepository pageRepository;
    @Autowired
    PageConverter pageConverter;

    @PostMapping(path = "/addPage/adminId={User_id}")
    public PageModel addPage(@RequestBody(required = true) PageModel pageModel,
                             @PathVariable(name = "User_id", required = true) long adminId) {
        return pageService.addPage(pageModel, adminId);
    }

    @PostMapping(path = "/deleteById/Id={id}")  
    public ResponseEntity<Object> deletePage(@PathVariable(required = true,name = "id")long id) {
        return pageService.deleteById(id);
    }

    @PostMapping(path = "/deleteMember/pageId={id},memberId={m_id}")
    public PageModel deleteMemberFromPage(@PathVariable(name = "id", required = true) long pageId,
                                          @PathVariable(name = "m_id", required = true) long memberId) {
        return pageService.deleteMemberFromPage(pageId, memberId);
    }

    @PostMapping(path = "/test")
    public List<PageModel> getAllPageThatUserIsAdminIn(@RequestBody(required = true) long id) {
        Optional<List<PageEntity>> pages = pageRepository.getAllPagesThatUserIsAdimnIn(id);
        List<PageModel> pageModels = new ArrayList<>();
        for (PageEntity pageEntity : pages.get()) {
            pageModels.add(pageConverter.convertPageEntityToPageModel(pageEntity));
        }
        return pageModels;
    }

    @PostMapping(path = "/updateInformation")
    public PageModel updateInformation(@RequestBody(required = true)PageModel pageModel){
        return pageService.updatePageInformation(pageModel);
    }

    @GetMapping(path = "/addMember/pageId={id},memberId={m_id}")
    public PageModel addMemberToPage(@PathVariable(name = "id", required = true) long pageId,
                                     @PathVariable(name = "m_id", required = true) long memberId) {
        return pageService.addMemberToPage(pageId, memberId);
    }

    @GetMapping(path = "/getAll")
    public List<PageModel> getAll() {
        return pageService.getAllPages();
    }

    @GetMapping(path = "/changeAdmin/pageId={page_id},adminId={admin_id}")
    public PageModel changeAdmin(@PathVariable(name = "page_id", required = true) long id,
                                 @PathVariable(required = true, name = "admin_id") long adminId) {
        return pageService.changeAdmin(id, adminId);
    }

    @GetMapping("/getAllPostsInPageByPId/{p_id}")
    public List<PostModel> getAllPostsInPageByPId(@PathVariable(name = "p_id",required = true)Long p_id){
        return pageService.fetchAllPostFromPageById(p_id);
    }

    @GetMapping("/getAllUsersInPageByPId/{p_id}")
    public List<UserModel> getAllUsersInGroupByGId(@PathVariable(name = "p_id",required = true)Long p_id){
        return pageService.fetchAllUserFromPageByPId(p_id);
    }

    @GetMapping(path = "/getFormat")
    public String getFormat() {
        return new PageModel().toString();
    }

    @GetMapping(path = "/search/word={word}")
    public List<PageModel> search(@PathVariable String word){
        return pageConverter.convertPageEntityListToPageModelList(pageRepository.searchPage(word));
    }

    @GetMapping(path = "/getPageById/id={id}")
    public PageModel getPageById(@PathVariable Long id){
        return pageConverter.convertPageEntityToPageModel(pageRepository.findById(id).get());
    }



}
