package Formcom.example.Taaw3iya.business.services;

import Formcom.example.Taaw3iya.dao.entities.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    public Optional<Post> getPost(Long id);

    public Post addPost(Post p);

    public Post updatePost(Post p);

    public void deletePost(Long id);

    public List<Post> getAllPost();
}
