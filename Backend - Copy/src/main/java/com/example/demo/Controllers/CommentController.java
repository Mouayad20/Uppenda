package com.example.demo.Controllers;

import java.util.List;
///////////////////////////////////////
import com.example.demo.Models.CommentModel;
import com.example.demo.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/add/{post_id}/{u_id}")
    public CommentModel addComment(
         @RequestBody CommentModel commentModel ,
         @PathVariable(name = "post_id",required = true)Long post_id,
         @PathVariable(name = "u_id",required = true) Long u_id ){

            return commentService.add(commentModel, post_id, u_id);

    }

    @PostMapping("/update")
    public CommentModel updateComment(@RequestBody CommentModel commentModel){
        return commentService.update(commentModel);
    }

    @DeleteMapping("/delete/{id}")
    public CommentModel deleteComment(@PathVariable(name = "id",required = true)Long id){
        return commentService.delete(id);
    }

    @GetMapping("/getAll/{id}")
    public List<CommentModel> getAllCommentByPostId(@PathVariable(name = "id",required = true)Long post_id){
        return commentService.fetchAllCommentByPostId(post_id);

    }

    
}
