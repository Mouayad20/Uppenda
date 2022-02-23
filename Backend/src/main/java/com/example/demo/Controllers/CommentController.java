package com.example.demo.Controllers;

import com.example.demo.Models.CommentModel;
import com.example.demo.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /* Post Request */

    @PostMapping("/add/{post_id}")
    public ResponseEntity<String> add(@RequestBody CommentModel commentModel,
                                      @PathVariable(name = "post_id", required = true) Long post_id,
                                      @RequestHeader("Authorization") String token) {
        return commentService.add(commentModel, post_id, token.substring("Bearer ".length()));
    }

    /* Put Request */

    @PutMapping("/update")
    public CommentModel update(@RequestBody CommentModel commentModel) {
        return commentService.update(commentModel);
    }

    /* Delete Request */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id", required = true) Long id) {
        return commentService.delete(id);
    }

    /* Get Request */

    @GetMapping("/getFormat")
    public CommentModel getFormat() {
        return new CommentModel();
    }

    @GetMapping("/getCommentsPost/{post_id}")
    public List<CommentModel> getCommentsPost(@PathVariable(name = "post_id", required = true) Long post_id) {
        return commentService.getCommentsPost(post_id);
    }

}
