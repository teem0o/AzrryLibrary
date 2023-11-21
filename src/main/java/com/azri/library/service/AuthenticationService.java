package com.azri.library.service;

import com.azri.library.dto.AuthenticateRequest;
import com.azri.library.dto.AuthenticationResponse;
import com.azri.library.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse signup(RegisterRequest registerRequest);

    AuthenticationResponse signin(AuthenticateRequest authenticateRequest);

}
