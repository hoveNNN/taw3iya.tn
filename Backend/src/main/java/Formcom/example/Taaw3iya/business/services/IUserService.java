package Formcom.example.Taaw3iya.business.services;

import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public Optional<Formcom.example.Taaw3iya.dao.entities.User> getUserr(Long id);

    public void deleteUser(Long id);

    public List<User> getAllUser();


}
