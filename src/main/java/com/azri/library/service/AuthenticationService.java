package com.azri.library.service;

import com.azri.library.security.AuthenticationResponse;

import com.azri.library.dto.RegisterRequest;
import com.azri.library.dto.AuthenticateRequest;

public interface AuthenticationService {
    AuthenticationResponse signup(RegisterRequest registerRequest);
    AuthenticationResponse signin(AuthenticateRequest authenticateRequest);
}
