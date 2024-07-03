package Formcom.example.Taaw3iya.web.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Changepass {
    private String oldpass;
    private String newpass;
    private String confirmpass;
}
