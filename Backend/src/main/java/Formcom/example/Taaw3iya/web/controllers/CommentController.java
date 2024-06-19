package Formcom.example.Taaw3iya.web.controllers;

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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    private IPostService postService;

    private static Long idCountcomment=0L;

    @Autowired
    private ICommentService commentService;


    @GetMapping({"/getAllComment"})
    public ResponseEntity<Object> getComments(){

        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);

    }
    @RequestMapping({"/ajoutercomment/{id}"})
    public ResponseEntity<Object> ajoutercomment(@PathVariable Long id ,@RequestBody CommentForm coment) {
        Post p1=postService.getPost(id).get();


        commentService.addComment(new Comment(idCountcomment++,coment.getValue(),p1));
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
}
