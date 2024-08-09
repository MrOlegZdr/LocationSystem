package com.home.project.locationsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.project.locationsystem.dto.UserDTO;
import com.home.project.locationsystem.entity.User;
import com.home.project.locationsystem.mapper.UserMapper;
import com.home.project.locationsystem.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDTO registerUser(UserDTO userDTO) {

		User savedUser = userRepository.save(userMapper.toUser(userDTO));
		return userMapper.toUserDTO(savedUser);
	}

	@Override
	public Optional<UserDTO> findByEmail(String email) {

		return Optional.ofNullable(userRepository.findByEmail(email)).map(userMapper::toUserDTO);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream().map(userMapper::toUserDTO).collect(Collectors.toList());
	}

}
