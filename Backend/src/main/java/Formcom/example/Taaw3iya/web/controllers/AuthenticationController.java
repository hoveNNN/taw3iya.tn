package Formcom.example.Taaw3iya.web.controllers;




import Formcom.example.Taaw3iya.dao.entities.User;
import Formcom.example.Taaw3iya.dao.repository.UserRepository;
import Formcom.example.Taaw3iya.exceptions.DuplicateUserException;
import Formcom.example.Taaw3iya.web.dto.LoginUserDto;
import Formcom.example.Taaw3iya.web.dto.RegisterUserDto;
import Formcom.example.Taaw3iya.web.models.requests.Changepass;
import Formcom.example.Taaw3iya.web.response.LoginResponse;
import Formcom.example.Taaw3iya.business.services.AuthenticationService;
import Formcom.example.Taaw3iya.business.services.JwtService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    private  UserRepository userRepository;
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) throws DuplicateUserException {


            User registeredUser = authenticationService.signup(registerUserDto);
            registeredUser.getRole().getAuthorities();
            return new ResponseEntity<>(registeredUser,HttpStatus.OK);




    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

       LoginResponse loginResponse = new LoginResponse();
       loginResponse.setToken(jwtToken);
       loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    public String getuseremail(){
        Integer idd=-1;
        String currentUserName="";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return currentUserName;
    }

    @RequestMapping("/changepas")
    public ResponseEntity<?> changepass(@RequestBody Changepass changepass){

       User user= this.authenticationService.updateUserPassword(getuseremail(), changepass.getOldpass(), changepass.getNewpass(), changepass.getConfirmpass());

        if (user !=null){
            return new ResponseEntity<>("Password have been updated",HttpStatus.OK);
        }else return new ResponseEntity<>("Password not updated",HttpStatus.BAD_REQUEST);

    }
}
