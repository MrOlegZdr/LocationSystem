package com.home.project.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.project.locationsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
