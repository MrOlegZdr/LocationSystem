package com.home.project.locationsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.project.locationsystem.entity.User;
import com.home.project.locationsystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User registerUser(String name, String email) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findUserByEmail(String email) {

		return Optional.ofNullable(userRepository.findByEmail(email));
	}

	@Override
	public Optional<User> findUserById(Long id) {

		return userRepository.findById(id);
	}

}
