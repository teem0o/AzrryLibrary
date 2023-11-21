package com.azri.library.service;

import com.azri.library.dto.UserRequest;
import com.azri.library.dto.UserResponse;
import com.azri.library.entity.User;

import java.util.List;

public interface UserService {
    UserResponse getUserByUsername(String username);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    UserResponse createUser(UserRequest user);
    UserResponse updateUser(UserRequest user);
    void deleteUser(Long id);

}
