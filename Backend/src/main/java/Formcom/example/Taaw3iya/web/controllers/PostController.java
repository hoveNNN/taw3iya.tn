package Formcom.example.Taaw3iya.web.controllers;

import Formcom.example.Taaw3iya.business.services.ILikeService;
import Formcom.example.Taaw3iya.business.services.IPostService;
import Formcom.example.Taaw3iya.dao.entities.Comment;
import Formcom.example.Taaw3iya.dao.entities.Like;
import Formcom.example.Taaw3iya.dao.entities.Post;
import Formcom.example.Taaw3iya.exceptions.DuplicatePostExecption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    @Autowired
    private IPostService postService;

    private static Long idCount=0L;

    @Autowired
    private ILikeService likeService;


    @GetMapping({"/getAllPosts"})
    public ResponseEntity<Object> getProducts(){

        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);

    }
    @RequestMapping({"/ajouterPost"})
    public ResponseEntity<Object> ajouterprod() throws DuplicatePostExecption {
         List<Like> likes1=new ArrayList<Like>();
            List<Comment> comments1=new ArrayList<Comment>();
         Post P1=new Post(idCount++,"type1",likes1,comments1);
            // Add the product if the category exists
//            postService.addPost(new Post(
//                    idCount++,
//                    post.getType(),likes1
//                      // Pass the existing category
//            ));
        postService.addPost(P1);
            return new ResponseEntity<>("Post add successfully", HttpStatus.OK);

        }
    @GetMapping(value="/getPostById/{id}")
    public ResponseEntity<Object> getPostbyId(@PathVariable("id")Long id) {
        Optional<Post> post=postService.getPost(id);
        if(post.isPresent()){


            return new ResponseEntity<>(post.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("failed:post  not found",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping(value="/post/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id")Long id) {
        Optional<Post> post=postService.getPost(id);
        if(post.isPresent()){
            postService.deletePost(id);

            return new ResponseEntity<>("post deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("failed:post not found",HttpStatus.NOT_FOUND);
        }
    }

    }


