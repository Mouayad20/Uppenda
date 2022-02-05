package com.example.demo.Controllers;

import com.example.demo.Models.CommentModel;
import com.example.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    /* Post Request */

    @PostMapping("/add/{post_id}/{u_id}")
    public CommentModel add(@RequestBody CommentModel commentModel,
                            @PathVariable(name = "post_id", required = true) Long post_id,
                            @PathVariable(name = "u_id", required = true) Long u_id) {
        return commentService.add(commentModel, post_id, u_id);
    }

    /* Put Request */

    @PutMapping("/update")
    public CommentModel update(@RequestBody CommentModel commentModel) {
        return commentService.update(commentModel);
    }

    /* Delete Request */

    @DeleteMapping("/delete/{id}")
    public CommentModel delete(@PathVariable(name = "id", required = true) Long id) {
        return commentService.delete(id);
    }

    /* Get Request */

    @GetMapping("/getAll/{id}")
    public List<CommentModel> getAllCommentByPostId(@PathVariable(name = "id", required = true) Long post_id) {
        return commentService.fetchAllCommentByPostId(post_id);
    }

}
