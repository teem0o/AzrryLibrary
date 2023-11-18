package com.azri.library.service;

import com.azri.library.dto.SignUpRequest;
import com.azri.library.entity.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
}
