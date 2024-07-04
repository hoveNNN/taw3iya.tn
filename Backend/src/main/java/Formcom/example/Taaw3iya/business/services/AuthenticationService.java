package  Formcom.example.Taaw3iya.business.services;


import  Formcom.example.Taaw3iya.web.dto.LoginUserDto;
import Formcom.example.Taaw3iya.web.dto.RegisterUserDto;
import Formcom.example.Taaw3iya.dao.entities.User;
import Formcom.example.Taaw3iya.dao.repository.UserRepository;
import Formcom.example.Taaw3iya.exceptions.DuplicateUserException;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static Formcom.example.Taaw3iya.dao.enums.Role.USER;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    private final IFilesStorageService filesStorageService;
    
  
    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        IFilesStorageService filesStorageService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.filesStorageService=filesStorageService;
    }

    public User signup(RegisterUserDto input) throws Exception {
        User user = new User();

                if (Objects.equals(input.getPassword(), input.getConfirmpassword())){
                    user.setLastName(input.getLastName());
                    user.setFirstName(input.getFirstName());
                    user.setEmail(input.getEmail());
                    user.setPassword(passwordEncoder.encode(input.getPassword()));

                    user.setRole(USER);
                }else{
                    throw new Exception("Password not the same");
                }

        
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
    public Integer getidOfUserAuth(String email){
        return Math.toIntExact(userRepository.findByEmail(email).get().getIDD());
    }
    public User getUserauth(String mail){

        return userRepository.findByEmail(mail).get();
    }


    public User updateUserImage(Long id , String filename){


        if(id == 0) {
            throw new IllegalArgumentException("ID cannot be null");
        }
            Optional<User> user=userRepository.findById(id);
            if (user.isPresent()){
                if(user.get().getImage()==null){
                    user.get().setImage(filename);
                }else{
                    this.filesStorageService.delete(user.get().getImage());

                    user.get().setImage(filename);
                }
                return  userRepository.save(user.get());
            }else{
                return null;
            }




    }
    public User updateUserPassword(String email, String password , String newPassword, String confirmPassword) {
        User user=getUserauth(email);


            if (newPassword.equals(confirmPassword)){
                if (passwordEncoder.matches(password, user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(newPassword));
                }else{
                    throw new IllegalArgumentException("Password does not match");
                }
                userRepository.save(user);
                return user;
            }else return null;
    }

    public User updateUser( User user,String Firstname,String lastname, String email) {

        user.setFirstName(Firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        return userRepository.save(user);



    }




}

// here will get the id of the connected user




