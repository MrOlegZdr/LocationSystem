package com.home.project.locationsystem.service;

import java.util.List;
import java.util.Optional;

import com.home.project.locationsystem.dto.UserDTO;

public interface UserService {

	UserDTO registerUser(UserDTO userDTO);

	Optional<UserDTO> findByEmail(String email);

	List<UserDTO> getAllUsers();
}
