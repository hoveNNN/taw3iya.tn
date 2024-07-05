package Formcom.example.Taaw3iya.business.services;

import Formcom.example.Taaw3iya.dao.entities.Post;
import Formcom.example.Taaw3iya.exceptions.DuplicatePostExecption;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    public Optional<Post> getPost(Long id);

    public Post addPost(Post p)throws DuplicatePostExecption;

    public Post updatePost(Post p,Long id)throws DuplicatePostExecption;

    public void deletePost(Long id);

    public List<Post> getAllPost();

    public Post putImage(Long id ,String filename) ;

    //
    public List <Post>getPostBytopic (Long idtopic);
}
