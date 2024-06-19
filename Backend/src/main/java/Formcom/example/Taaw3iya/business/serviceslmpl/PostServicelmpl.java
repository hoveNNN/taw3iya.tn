package Formcom.example.Taaw3iya.business.serviceslmpl;

import Formcom.example.Taaw3iya.business.services.IPostService;
import Formcom.example.Taaw3iya.dao.entities.Post;
import Formcom.example.Taaw3iya.dao.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class PostServicelmpl implements IPostService {
@Autowired
    PostRepository postRepository;
@Override
    public Optional<Post> getPost(Long id){
    return postRepository.findById(id);
    }
@Override
    public Post addPost(Post p){
    return postRepository.save(p);
}
@Override
    public Post updatePost(Post p){
    return postRepository.save(p);
}
@Override
    public void deletePost(Long p){
    postRepository.deleteById(p);
    }
@Override
    public List<Post> getAllPost(){
    return  postRepository.findAll();
}
}
