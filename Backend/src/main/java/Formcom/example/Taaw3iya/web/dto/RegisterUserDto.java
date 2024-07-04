package Formcom.example.Taaw3iya.web.dto;



import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import Formcom.example.Taaw3iya.dao.enums.Role;

@Setter
@Getter
public class RegisterUserDto {
    private String fullName;

    private String email;
    
    private String password;

    private String confirmpassword;
    



    
    // getters and setters here...

    // public static User FromRegisterUserDTO(RegisterUserDto registerUser, PasswordEncoder paswword){
    //     User user=User.builder().fullName(registerUser.fullName)
    //     .email(registerUser.email)
    //     .password(paswword.encode(registerUser.password))
    //     .role(null);
    // }
}
    
  