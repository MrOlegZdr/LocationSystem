package com.home.project.locationsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.home.project.locationsystem.dto.UserDTO;
import com.home.project.locationsystem.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO toUserDTO(User user);

	@Mapping(target = "locations", ignore = true) // Ignore reverse mapping of locations
	User toUser(UserDTO userDTO);

}
