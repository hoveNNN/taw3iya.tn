package Formcom.example.Taaw3iya.dao.repository;

import Formcom.example.Taaw3iya.dao.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByType(String name);
    Optional<Post> findById(Long id);
}
