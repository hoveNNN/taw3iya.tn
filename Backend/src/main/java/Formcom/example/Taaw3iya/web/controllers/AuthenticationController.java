package Formcom.example.Taaw3iya.web.controllers;




import Formcom.example.Taaw3iya.dao.entities.User;
import Formcom.example.Taaw3iya.exceptions.DuplicateUserException;
import Formcom.example.Taaw3iya.web.dto.LoginUserDto;
import Formcom.example.Taaw3iya.web.dto.RegisterUserDto;
import Formcom.example.Taaw3iya.web.response.LoginResponse;
import Formcom.example.Taaw3iya.business.services.AuthenticationService;
import Formcom.example.Taaw3iya.business.services.JwtService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) throws DuplicateUserException {
        User registeredUser = authenticationService.signup(registerUserDto);
        registeredUser.getRole().getAuthorities();
      System.out.println("maybe yes "); 
       System.out.println(registeredUser.getRole().getAuthorities()); 
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
}
