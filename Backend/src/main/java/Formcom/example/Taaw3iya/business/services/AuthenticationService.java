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

import java.util.Optional;

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

    public User signup(RegisterUserDto input) throws DuplicateUserException{
        User user = new User();
                user.setFullName(input.getFullName());
                user.setEmail(input.getEmail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));
                 System.out.println(input.getRole());
                user.setRole(input.getRole());
        
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
}

// here will get the id of the connected user




