package com.azri.library;

import com.azri.library.dto.AuthenticateRequest;
import com.azri.library.dto.RegisterRequest;
import com.azri.library.entity.User;
import com.azri.library.repository.UserRepository;
import com.azri.library.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.azri.library.security.Role.ADMIN;
import static com.azri.library.security.Role.USER;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = userRepository.save(User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role(ADMIN)
                    .build());
            userRepository.save(admin);
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = userRepository.save(User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user"))
                    .role(USER)
                    .build());
            userRepository.save(user);
        }
//        TODO logger
        System.out.println("ADMIN token: " + authenticationService.signin(new AuthenticateRequest("admin", "admin")).getToken());
        System.out.println("USER token: " + authenticationService.signin(new AuthenticateRequest("user", "user")).getToken());




    }
}
