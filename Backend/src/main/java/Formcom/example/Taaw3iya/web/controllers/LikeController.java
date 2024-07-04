package Formcom.example.Taaw3iya.web.controllers;

import Formcom.example.Taaw3iya.business.services.AuthenticationService;
import Formcom.example.Taaw3iya.business.services.ILikeService;
import Formcom.example.Taaw3iya.business.services.IPostService;
import Formcom.example.Taaw3iya.dao.entities.Like;
import Formcom.example.Taaw3iya.dao.entities.Post;
import Formcom.example.Taaw3iya.exceptions.DuplicatePostExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/like")
@RestController
public class LikeController {
    public static Long idCountLikes=0L;

    @Autowired
    private IPostService postService;

    @Autowired
    private ILikeService likeService;

    @Autowired
    private AuthenticationService authservice;


    @GetMapping("getAllLikes")
    public String getAllLikesbypostId() {
        return "Hello, World!";
    }

    @RequestMapping("/createlike/{idpost}")

    public ResponseEntity<Object> createlikeforPost(@PathVariable("idpost")Long id) throws DuplicatePostExecption {

//        Like l1=new Like(idCountLikes++,"Like",authservice.getUserauth(getuseremail()));
//        likeService.addLike(l1);
//        System.out.println("big problem");
//        return new ResponseEntity<>("like ajouted",HttpStatus.OK);


        Optional<Post> post=postService.getPost(id);
        if (post.isPresent()) {
            Like l1=new Like(idCountLikes++,"Like",authservice.getUserauth(getuseremail()));
            likeService.addLike(l1);
            List<Like> Likes=postService.getPost(id).get().getLikes();
            Likes.add(l1);
            postService.getPost(id).get().setLikes(Likes);
            postService.updatePost(post.get(),id);
            System.out.println("maybeis good");
            return new ResponseEntity<>(l1,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("failed:post  not found",HttpStatus.NOT_FOUND);
        }
    }


    public Integer getidUser(){
        Integer idd=-1;
        String currentUserName="";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();

            idd= authservice.getidOfUserAuth(currentUserName);
        }
        return idd;
    }
    public String getuseremail(){
        Integer idd=-1;
        String currentUserName="";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return currentUserName;
    }
}
