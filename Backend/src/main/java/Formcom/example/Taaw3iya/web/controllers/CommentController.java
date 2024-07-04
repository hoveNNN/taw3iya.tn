package Formcom.example.Taaw3iya.web.controllers;

import Formcom.example.Taaw3iya.business.services.AuthenticationService;
import Formcom.example.Taaw3iya.business.services.ICommentService;
import Formcom.example.Taaw3iya.business.services.ILikeService;
import Formcom.example.Taaw3iya.business.services.IPostService;
import Formcom.example.Taaw3iya.dao.entities.Comment;
import Formcom.example.Taaw3iya.dao.entities.Like;
import Formcom.example.Taaw3iya.dao.entities.Post;
import Formcom.example.Taaw3iya.web.models.requests.CommentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static Formcom.example.Taaw3iya.dao.enums.IntercationType.LIKE;

@RequestMapping("/api/comment")
@RestController
public class CommentController {

    @Autowired
    private IPostService postService;

    private static Long idCountcomment=0L;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private AuthenticationService authservice;

    @GetMapping({"/getCommentForPost/{idpost}"})
    public ResponseEntity<Object> getCommentsBypost(@PathVariable("idpost") Long idpost){

       Optional<Comment> coments= commentService.gettAllCommentforPost(idpost);
        if(coments.isPresent()){
            List<Comment> test=coments.stream().toList();
            return new ResponseEntity<>(test,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No comment for this post",HttpStatus.NOT_FOUND);
        }



    }
    @RequestMapping({"/ajoutercomment/{idpost}"})
    public ResponseEntity<Object> ajoutercomment(@PathVariable("idpost") Long idpost ,@RequestBody CommentForm coment) {
        Post p1=postService.getPost(idpost).get();
        Long iduser= Long.valueOf(authservice.getidOfUserAuth(getuseremail()));

        Comment l1=new Comment(idCountcomment++,coment.getValue(),authservice.getUserauth(getuseremail()),idpost);
        commentService.addComment(l1);
//
//
        return new ResponseEntity<>("Comment add successfully", HttpStatus.OK);

    }
    @DeleteMapping(value="/comment/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id")Long id) {
        Optional<Comment> comment=commentService.getComment(id);
        if(comment.isPresent()){
            commentService.deleteComment(id);

            return new ResponseEntity<>("comment deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("failed:comment not found",HttpStatus.NOT_FOUND);
        }
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
