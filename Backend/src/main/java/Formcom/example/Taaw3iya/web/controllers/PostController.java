package Formcom.example.Taaw3iya.web.controllers;

import Formcom.example.Taaw3iya.business.services.AuthenticationService;
import Formcom.example.Taaw3iya.business.services.ICommentService;
import Formcom.example.Taaw3iya.business.services.ILikeService;
import Formcom.example.Taaw3iya.business.services.IPostService;
import Formcom.example.Taaw3iya.business.services.ITagService;
import Formcom.example.Taaw3iya.dao.entities.*;
import Formcom.example.Taaw3iya.dao.repository.UserRepository;
import Formcom.example.Taaw3iya.exceptions.DuplicatePostExecption;

import Formcom.example.Taaw3iya.web.dto.AddPostDto;
import Formcom.example.Taaw3iya.web.dto.TagDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// @PreAuthorize("hasAnyRole('ADMIN','USER')")
@RequestMapping("/api/post")
@RestController
public class PostController {
    @Autowired
    private IPostService postService;
    private ITagService tagService;


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

    @GetMapping({"/getId"})
    public ResponseEntity<Object> getUserID(){

        return new ResponseEntity<>( getidUser(), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/posts/create")
    public ResponseEntity<?> createNewPost(@RequestBody @Valid AddPostDto dto) throws JsonProcessingException, DuplicatePostExecption {
        Post newPost = postService.addPost(dto);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
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


//    @GetMapping("/tags/{tagName}")
//    public ResponseEntity<?> getPostsByTag(@PathVariable("tagName") String tagName,
//                                           @RequestParam("page") Integer page,
//                                           @RequestParam("size") Integer size) {
//        page = page < 0 ? 0 : page-1;
//        size = size <= 0 ? 5 : size;
//        Tag targetTag = tagService.getTagByName(tagName);
//        List<PostResponse> taggedPosts = postService.getPostByTagPaginate(targetTag, page, size);
//        return new ResponseEntity<>(taggedPosts, HttpStatus.OK);
//    }
}


