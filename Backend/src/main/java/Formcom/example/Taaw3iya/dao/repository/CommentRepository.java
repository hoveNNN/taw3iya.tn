package Formcom.example.Taaw3iya.dao.repository;

import Formcom.example.Taaw3iya.dao.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findByValue(String name);
    Optional<Comment> findById(Long id);
}
