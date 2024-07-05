package Formcom.example.Taaw3iya.web.dto;

import Formcom.example.Taaw3iya.dao.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminAddUserDTO {
    private String firstName;

    private String lastName;


//    private String email;


    private String password;

    private String confirmpassword;
}
