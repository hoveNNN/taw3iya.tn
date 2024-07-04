package Formcom.example.Taaw3iya.business.serviceslmpl;

import Formcom.example.Taaw3iya.business.services.ILikeService;
import Formcom.example.Taaw3iya.business.services.IPostService;
import Formcom.example.Taaw3iya.dao.entities.Post;
import Formcom.example.Taaw3iya.dao.repository.PostRepository;
import Formcom.example.Taaw3iya.dao.repository.UserRepository;
import Formcom.example.Taaw3iya.exceptions.DuplicatePostExecption;
import Formcom.example.Taaw3iya.web.dto.AddPostDto;
import jakarta.transaction.Transactional;
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
    UserRepository userRepository;



    @Override
    public Optional<Post> getPost(Long id){
    return postRepository.findById(id);
    }

    @Override
    public Post addPost(AddPostDto dto) throws DuplicatePostExecption{
        Post post = new Post();
        post.setTags(dto.getTags());
        post.setSubject(dto.getSubject());
        post.setDescription(dto.getDescription());
        post.setUser(userRepository.getReferenceById(dto.getUserId()));
        return postRepository.save(post);
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
}
