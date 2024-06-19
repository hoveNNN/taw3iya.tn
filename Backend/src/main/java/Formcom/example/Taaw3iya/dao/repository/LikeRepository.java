package Formcom.example.Taaw3iya.dao.repository;

import Formcom.example.Taaw3iya.dao.entities.Like;
import Formcom.example.Taaw3iya.dao.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like> findByValue(String name);
    Optional<Like> findById(Long id);
}
