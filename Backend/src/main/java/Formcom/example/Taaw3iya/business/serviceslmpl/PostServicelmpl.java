package Formcom.example.Taaw3iya.business.serviceslmpl;

import Formcom.example.Taaw3iya.business.services.IFilesStorageService;
import Formcom.example.Taaw3iya.business.services.ILikeService;
import Formcom.example.Taaw3iya.business.services.IPostService;
import Formcom.example.Taaw3iya.dao.entities.Like;
import Formcom.example.Taaw3iya.dao.entities.Post;
import Formcom.example.Taaw3iya.dao.entities.User;
import Formcom.example.Taaw3iya.dao.repository.PostRepository;
import Formcom.example.Taaw3iya.exceptions.DuplicatePostExecption;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class PostServicelmpl implements IPostService {
   
@Autowired
    PostRepository postRepository;

@Autowired
    IFilesStorageService filesStorageService;


@Override
    public Optional<Post> getPost(Long id){
    return postRepository.findById(id);
    }
@Override
    public Post addPost(Post p)throws DuplicatePostExecption{

    Hibernate.initialize(p.getLikes());
    if (p ==null) {
            throw new IllegalArgumentException("post cannot be null");
        }
        try{
            return postRepository.save(p);
        }
        catch(DataIntegrityViolationException e){
            throw new DuplicatePostExecption("A Post whit the same email or other champ");
        }
    
}
@Override
    public Post updatePost(Post p ,Long id)throws DuplicatePostExecption{
        if (p ==null || id==null) {
            throw new IllegalArgumentException("Id or post cannot be null");
        }
        try{
            return postRepository.save(p);
        }
        catch(DataIntegrityViolationException e){
            throw new DuplicatePostExecption("A Post whit the same email or other champ");
        }
}
@Override
    public void deletePost(Long p){
    postRepository.deleteById(p);
    }
@Override
    public List<Post> getAllPost(){


    return  postRepository.findAll();
}
//
//    @Override
//    public Post putImage(Long id ,String filename) {
//
//        if(id == 0) {
//            throw new IllegalArgumentException("ID cannot be null");
//        }
//        Optional<Post> post=postRepository.findById(id);
//        if (post.isPresent()){
//            if(post.get().getImage()==null){
//                post.get().setImage(filename);
//            }else{
//                this.filesStorageService.delete(post.get().getImage());
//
//                post.get().setImage(filename);
//            }
//            return  postRepository.save(post.get());
//        }else{
//            return null;
//        }
//
//
//    }
}
