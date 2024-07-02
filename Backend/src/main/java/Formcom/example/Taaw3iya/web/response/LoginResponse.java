package Formcom.example.Taaw3iya.web.response;


import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Setter
public class LoginResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

	// Getters and setters...
}