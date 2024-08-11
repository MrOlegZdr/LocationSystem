package com.home.project.locationsystem.service;

import java.util.Optional;

import com.home.project.locationsystem.entity.User;

public interface UserService {

	User registerUser(String name, String email);

	Optional<User> findUserByEmail(String email);

	Optional<User> findUserById(Long id);
}
