package Formcom.example.Taaw3iya.business.serviceslmpl;

import Formcom.example.Taaw3iya.business.services.IUserService;
import Formcom.example.Taaw3iya.dao.entities.User;
import Formcom.example.Taaw3iya.dao.repository.UserRepository;
import Formcom.example.Taaw3iya.web.dto.AdminAddUserDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import static Formcom.example.Taaw3iya.business.services.AuthenticationService.idcountDEntity;
import static Formcom.example.Taaw3iya.dao.enums.Gender.MALE;
import static Formcom.example.Taaw3iya.dao.enums.Role.USER;
import static Formcom.example.Taaw3iya.dao.enums.Role.ADMIN;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@AllArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements IUserService {


    @Autowired
     UserRepository userRepository ;

   final private   PasswordEncoder passwordEncoder;


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

    public Boolean chnageRole(Long id){
        Optional<User> user=this.getUserr(id);
        if(user.isPresent()){
            if(user.get().getRole().equals("ADMIN")) {
                user.get().setRole(USER);

            } else{
                    user.get().setRole(ADMIN);
                }
                userRepository.save(user.get());
            return Boolean.TRUE;
            }
        return Boolean.FALSE;

        }

    public Boolean AdminAddUser2(AdminAddUserDTO data22) {
        Boolean test = false;
        User user = new User();


        if (Objects.equals(data22.getPassword(), data22.getConfirmpassword())) {

            user.setLastName(data22.getLastName());
            user.setFirstName(data22.getFirstName());
            user.setEmail(data22.getEmail());
            user.setGender(MALE);
            user.setPassword(passwordEncoder.encode(data22.getPassword()));

            user.setRole(USER);
            userRepository.save(user);
            test = true;

        }
    return test;
    }


}

