package com.example.demo.Controllers;

import com.example.demo.Converters.PageConverter;
import com.example.demo.Models.PageModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.PageRepository;
import com.example.demo.Services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "pages")
public class PageController {

    @Autowired
    private PageService pageService;

    /* Post Request */

    @PostMapping(path = "/add/adminId={User_id}")
    public PageModel add(@RequestBody(required = true) PageModel pageModel,
                         @PathVariable(name = "User_id", required = true) long adminId) {
        return pageService.add(pageModel, adminId);
    }

    /* Put Request */

    @PutMapping(path = "/update")
    public PageModel update(@RequestBody(required = true) PageModel pageModel) {
        return pageService.update(pageModel);
    }

    /* Delete Request */

    @DeleteMapping(path = "/delete/Id={id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true, name = "id") long id) {
        return pageService.delete(id);
    }

    /* Get Request */

    @GetMapping(path = "/getFormat")
    public String getFormat() {
        return new PageModel().toString();
    }

    @GetMapping(path = "/addMember/pageId={id},memberId={m_id}")
    public PageModel addMember(@PathVariable(name = "id", required = true) long pageId,
                                     @PathVariable(name = "m_id", required = true) long memberId) {
        return pageService.addMember(pageId, memberId);
    }

    @GetMapping(path = "/deleteMember/pageId={id},memberId={m_id}")
    public PageModel deleteMember(@PathVariable(name = "id", required = true) long pageId,
                                          @PathVariable(name = "m_id", required = true) long memberId) {
        return pageService.deleteMember(pageId, memberId);
    }

    @GetMapping(path = "/getPageById/id={id}")
    public PageModel getPageById(@PathVariable Long id) {
        return pageService.getPageById(id);
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
    public List<PostModel> getAllPostsInPageByPId(@PathVariable(name = "p_id", required = true) Long p_id) {
        return pageService.fetchAllPostFromPageById(p_id);
    }

    @GetMapping("/getAllUsersInPageByPId/{p_id}")
    public List<UserModel> getAllUsersInGroupByGId(@PathVariable(name = "p_id", required = true) Long p_id) {
        return pageService.fetchAllUserFromPageByPId(p_id);
    }

    @GetMapping(path = "/search/word={word}")
    public List<PageModel> search(@PathVariable String word) {
        return pageService.search(word);
    }

}
