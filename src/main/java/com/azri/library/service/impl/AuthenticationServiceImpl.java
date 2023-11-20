package com.azri.library.service.impl;

import com.azri.library.exception.UsernameAlreadyExistsException;
import com.azri.library.security.AuthenticationResponse;

import com.azri.library.dto.RegisterRequest;
import com.azri.library.dto.AuthenticateRequest;
import com.azri.library.entity.User;
import com.azri.library.repository.UserRepository;
import com.azri.library.security.Role;
import com.azri.library.service.AuthenticationService;
import com.azri.library.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthenticationResponse signup(RegisterRequest registerRequest) throws RuntimeException {
        var user = new User();
        user.setUsername(registerRequest.getUsername());
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(registerRequest.getUsername());
        }
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse signin(AuthenticateRequest authenticateRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword()));
        var user = userRepository.findByUsername(authenticateRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        var jwt = jwtService.generateToken(user);


        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

}
