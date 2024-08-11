package com.home.project.locationsystem.mapper;

import org.mapstruct.Mapper;

import com.home.project.locationsystem.dto.UserDTO;
import com.home.project.locationsystem.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO toDTO(User user);

	User toEntity(UserDTO userDTO);

}
