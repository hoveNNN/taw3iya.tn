package Formcom.example.Taaw3iya.business.serviceslmpl;

import Formcom.example.Taaw3iya.business.services.IUserService;
import Formcom.example.Taaw3iya.dao.entities.User;
import Formcom.example.Taaw3iya.dao.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {


    @Autowired
     UserRepository userRepository ;




    @Override
    public Optional<User> getUserr(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public List<org.springframework.security.core.userdetails.User> getAllUser() {
        return List.of((org.springframework.security.core.userdetails.User) userRepository.findAll());
    }

    public List<User> getAllUserr() {
        return userRepository.findAll();
    }
}
