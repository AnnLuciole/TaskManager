package com.example.demo.service;

import com.example.demo.model.User;

import java.util.Optional;

public interface UserService {
    User addUser(User user);

    Optional<User> findByLogin(String login);

    User getCurrentUser();
}