package com.azri.library;

import com.azri.library.dto.AuthenticateRequest;
import com.azri.library.entity.User;
import com.azri.library.repository.UserRepository;
import com.azri.library.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.azri.library.security.Role.ADMIN;
import static com.azri.library.security.Role.USER;

@SpringBootApplication
@EnableJms
public class LibraryApplication implements CommandLineRunner {
    @Value("${application.security.admin.username}")
    private String ADMIN_USERNAME = "admin";
    @Value("${application.security.admin.password}")
    private String ADMIN_PASSWORD = "admin";

    @Value("${application.security.user.username}")
    private String USER_USERNAME = "user";
    @Value("${application.security.user.password}")
    private String USER_PASSWORD = "user";

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    static final Logger log =
            LoggerFactory.getLogger(LibraryApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername(ADMIN_USERNAME).isEmpty()) {
            var admin = userRepository.save(User.builder()
                    .username(ADMIN_USERNAME)
                    .password(passwordEncoder.encode(ADMIN_PASSWORD))
                    .role(ADMIN)
                    .build());
            userRepository.save(admin);
        }
        if (userRepository.findByUsername(USER_USERNAME).isEmpty()) {
            var user = userRepository.save(User.builder()
                    .username(USER_USERNAME)
                    .password(passwordEncoder.encode(USER_PASSWORD))
                    .role(USER)
                    .build());
            userRepository.save(user);
        }
        log.info("ADMIN token: {}", authenticationService.signin(new AuthenticateRequest(ADMIN_USERNAME, ADMIN_PASSWORD)).getToken());
        log.info("USER token: {}", authenticationService.signin(new AuthenticateRequest(USER_USERNAME, USER_PASSWORD)).getToken());


    }
}
