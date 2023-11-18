package com.azri.library.controller;

import com.azri.library.dto.SignUpRequest;
import com.azri.library.entity.User;
import com.azri.library.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }
    @GetMapping("/test")
    public String test() {
        return "test";
    }
    @GetMapping("/test2")
    public String test2() {
        return "test";
    }




}
