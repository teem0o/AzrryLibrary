package com.azri.library.service.impl;

import com.azri.library.dto.UserRequest;
import com.azri.library.dto.UserResponse;
import com.azri.library.entity.User;
import com.azri.library.exception.UserNotFoundException;
import com.azri.library.exception.UsernameAlreadyExistsException;
import com.azri.library.repository.UserRepository;
import com.azri.library.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(user -> modelMapper.map(user, UserResponse.class))
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id).map(user -> modelMapper.map(user, UserResponse.class))
                .orElseThrow(() -> new UserNotFoundException(id + ""));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserResponse.class)).toList();
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(userRequest.getUsername());
        }
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        userRepository.findById(userRequest.getId())
                .orElseThrow(() -> new UserNotFoundException(userRequest.getId() + ""));
        var user = modelMapper.map(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return modelMapper.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id + ""));
        userRepository.deleteById(id);

    }
}
