package com.azri.library.service.impl;


import com.azri.library.entity.User;
import com.azri.library.repository.UserRepository;
import com.azri.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private JwtService jwtService;
//
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
            }
        };
    }
//
//    public String generateToken(String username) {
//        return jwtService.generateToken(username);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> credential = userRepository.findByUsername(username);
//        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
//    }
//
//    public void validateToken(String token) {
//        jwtService.validateToken(token);
//    }
}
