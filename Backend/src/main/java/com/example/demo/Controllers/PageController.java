package com.example.demo.Controllers;

import com.example.demo.Models.PageModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "page")
public class PageController {

    @Autowired
    private PageService pageService;

    /* Post Request */

    @PostMapping(path = "/add")
    public ResponseEntity<String> add(@RequestBody PageModel pageModel,
                                      @RequestHeader("Authorization") String token) {
        return pageService.add(pageModel, token.substring("Bearer ".length()));
    }

    /* Put Request */

    @PutMapping(path = "/update")
    public PageModel update(@RequestBody(required = true) PageModel pageModel) {
        return pageService.update(pageModel);
    }

    /* Delete Request */

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true, name = "id") long id) {
        return pageService.delete(id);
    }

    /* Get Request */

    @GetMapping(path = "/getFormat")
    public PageModel getFormat() {
        return new PageModel();
    }

    @GetMapping(path = "/addMember/pageId={page_id},memberId={member_id}")
    public ResponseEntity<String> addMember(@PathVariable(name = "page_id", required = true) Long pageId,
                                            @PathVariable(name = "member_id", required = true) Long memberId) {
        return pageService.addMember(pageId, memberId);
    }

    @GetMapping(path = "/deleteMember/pageId={page_id},memberId={member_id}")
    public ResponseEntity<String> deleteMember(@PathVariable(name = "page_id", required = true) Long pageId,
                                               @PathVariable(name = "member_id", required = true) Long memberId) {
        return pageService.deleteMember(pageId, memberId);
    }

    @GetMapping(path = "/getPage/{id}")
    public ResponseEntity<Object> getPage(@PathVariable(name = "id") Long id) {
        return pageService.getPage(id);
    }

    @GetMapping(path = "/getAll")
    public List<PageModel> getAll() {
        return pageService.getAll();
    }

    @GetMapping("/getPagePosts/{page_id}")
    public List<PostModel> getPagePosts(@PathVariable(name = "page_id", required = true) Long page_id) {
        return pageService.getPagePosts(page_id);
    }

    @GetMapping("/getPageUsers/{page_id}")
    public List<UserModel> getPageUsers(@PathVariable(name = "page_id", required = true) Long page_id) {
        return pageService.getPageUsers(page_id);
    }

    @GetMapping(path = "/search/word={word}")
    public List<PageModel> search(@PathVariable String word) {
        return pageService.search(word);
    }

}
