package com.example.demo.Controllers;

import java.util.List;

import com.example.demo.Models.LikeModel;
import com.example.demo.services.LikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/typeReaction")
public class LikeController {

    @Autowired
    LikeService likeService ;

    @PostMapping("/reaction/p={post_id}/u={u_id}/r={reaction_id}")
    public LikeModel Like(
                    @PathVariable(name = "post_id"     , required = true) Long post_id     ,
                    @PathVariable(name = "u_id"        , required = true) Long u_id        ,
                    @PathVariable(name = "reaction_id" , required = true) Long reaction_id ){

         return  likeService.addLike(post_id, u_id,reaction_id)   ;          

    }

    @PostMapping("/unReaction/l={reaction_id}")
    public String  unLike(
                    @PathVariable(name = "reaction_id",required = true) Long reaction_id ){

         return  likeService.removeLikeByLikeId(reaction_id)   ;          
    }

    @GetMapping("/getAllLikeByPostId/{post_id}")
    public List<LikeModel> getAllLike(@PathVariable(name = "post_id",required = true) Long post_id){
            return likeService.fetchAllLikeByPostId(post_id);
    }

}
