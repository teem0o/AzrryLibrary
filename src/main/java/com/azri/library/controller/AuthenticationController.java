package com.azri.library.controller;

import com.azri.library.dto.AuthenticateRequest;
import com.azri.library.dto.RegisterRequest;
import com.azri.library.security.AuthenticationResponse;
import com.azri.library.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> signup(@Valid @RequestBody RegisterRequest registerRequest) {
            return ResponseEntity.ok(authenticationService.signup(registerRequest));

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> signin(@Valid @RequestBody AuthenticateRequest authenticateRequest) {
        return ResponseEntity.ok(authenticationService.signin(authenticateRequest));
    }




}
