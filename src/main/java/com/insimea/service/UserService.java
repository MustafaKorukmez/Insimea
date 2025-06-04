package com.insimea.service;

import com.insimea.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);
}
