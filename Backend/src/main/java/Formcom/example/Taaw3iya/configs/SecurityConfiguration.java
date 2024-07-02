package Formcom.example.Taaw3iya.configs;

import ch.qos.logback.core.encoder.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

import org.springframework.security.web.authentication.logout.LogoutHandler;


import static Formcom.example.Taaw3iya.dao.enums.Role.ADMIN;

import static Formcom.example.Taaw3iya.dao.enums.Privilege.READ_PRIVILEGE;

import static Formcom.example.Taaw3iya.dao.enums.Role.USER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private static final String[] WHITE_LIST_URL = {"testForall",
            "auth/login",
            "auth/signup",
            "api/post/getId",
            "api/post/getAllPosts",
            "api/like/getAllLikes",
            "api/like/createlike/{idpost}"



            };


    // public SecurityConfiguration(
    //     JwtAuthenticationFilter jwtAuthenticationFilter,
    //     AuthenticationProvider authenticationProvider,
    //     LogoutHandler logoutHandler

    // ) {
    //     this.authenticationProvider = authenticationProvider;
    //     this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    //     this.logoutHandler=logoutHandler;
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


//        http
//                .authorizeRequests()
//                // Allow only users with role ADMIN to access POST requests to "/api/example"
//                .requestMatchers(GET, "/api/test/testAdmin").hasRole(ADMIN.name())
//                // Allow all other requests to be accessed by anyone
//                .anyRequest().permitAll()
//                .and()
//                // Configure form-based authentication
//                .formLogin()
//                .and()
//                // Configure HTTP Basic authentication
//                .httpBasic();
//        return http.build();




        http
        .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/post/testAdmin").hasRole(ADMIN.name())
                                .requestMatchers( "/api/post/testRole").hasRole(USER.name())
                                .requestMatchers( "/api/post/ajouterPost").hasRole(ADMIN.name())

                                .anyRequest()
                                .authenticated()
                )
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();

    
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8005"));
        configuration.setAllowedMethods(List.of("GET","POST"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
}
