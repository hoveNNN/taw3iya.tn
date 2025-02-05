package Formcom.example.Taaw3iya.dao.repository;


import Formcom.example.Taaw3iya.dao.entities.User;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
  
        Optional<User> findByEmail(String email);
        Optional<User> findById(Long id);

}
