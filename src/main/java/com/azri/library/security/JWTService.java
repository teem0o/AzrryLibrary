package com.azri.library.security;

import com.azri.library.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);


//    boolean validateToken(String token);
//    String getUsernameFromToken(String token);
}
