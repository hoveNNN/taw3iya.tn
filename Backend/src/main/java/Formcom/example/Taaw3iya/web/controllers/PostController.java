package Formcom.example.Taaw3iya.web.controllers;

import Formcom.example.Taaw3iya.business.services.AuthenticationService;
import Formcom.example.Taaw3iya.business.services.ICommentService;
import Formcom.example.Taaw3iya.business.services.ILikeService;
import Formcom.example.Taaw3iya.business.services.IPostService;
import Formcom.example.Taaw3iya.dao.entities.Comment;
import Formcom.example.Taaw3iya.dao.entities.Like;
import Formcom.example.Taaw3iya.dao.entities.Post;
import Formcom.example.Taaw3iya.dao.entities.User;
import Formcom.example.Taaw3iya.dao.repository.UserRepository;
import Formcom.example.Taaw3iya.exceptions.DuplicatePostExecption;

import Formcom.example.Taaw3iya.web.models.requests.PostDTO;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
// @PreAuthorize("hasAnyRole('ADMIN','USER')")
@RequestMapping("/api/post")
@RestController
public class PostController {
    @Autowired
    private IPostService postService;


    private static Long idCount=0L;

    @Autowired
    private ILikeService likeService;

    @Autowired
    private AuthenticationService authservice;

    @Autowired
    private ICommentService commentService;
    @Autowired
   private  UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping({"/getAllPosts"})
    public ResponseEntity<Object> getProducts(){

        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);

    }

    @GetMapping({"/getPostByIdd/{id}"})
    public ResponseEntity<Object> getpostByid33(@PathVariable("id")Long id){
       Optional <Post> p=postService.getPost(id);
      List<Like> likes= likeService.getAllLikes();
      List<Like> likes1= (List<Like>) likes.stream().filter(like -> like.getPoste()==id);
        if (p.isPresent()){
            p.get().setLikes(likes1);
            p.get().getLikes();
            Hibernate.initialize(p);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping({"/getId"})
    public ResponseEntity<Object> getUserID(){

        return new ResponseEntity<>( getidUser(), HttpStatus.OK);
    }



    @PreAuthorize("hasAnyRole('ADMIN')")
     @RequestMapping({"/ajouterPost"})
     public ResponseEntity<Object> ajouterprod(@RequestBody PostDTO postfrom) throws DuplicatePostExecption {

       
        List<Like> likes1=new ArrayList<Like>();
//        HashSet<Like> likes1=new HashSet<Like>();
         List<Comment> comments1=new ArrayList<Comment>();

          Post P1=new Post(idCount++,postfrom.getValue(),likes1,comments1,authservice.getUserauth(this.getuseremail()));
             // Add the product if the category exists
 //            postService.addPost(new Post(
 //                    idCount++,
 //                    post.getType(),likes1
 //                      // Pass the existing category
 //            ));
         postService.addPost(P1);
             return new ResponseEntity<>(P1, HttpStatus.OK);

         }
        @PreAuthorize("hasAnyRole('ROLE_USER')")
        @GetMapping({"/testRole"})
        public ResponseEntity<Object> ajouterprod2()   {
            
           
            
                return new ResponseEntity<>("USer Is here add successfully",HttpStatus.OK);
    
            }

            @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
            @RequestMapping({"/testAdmin"})
            public ResponseEntity<Object> ajouterprod22()   {
                
               
                
                    return new ResponseEntity<>("ADMIN Is here add successfully",HttpStatus.OK);
        
                }
                
                @RequestMapping({"/testForall"})
                public ResponseEntity<Object> ajouterprod33()   {

                        return new ResponseEntity<>("ADMIN and User Is here add successfully",HttpStatus.OK);
            
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


