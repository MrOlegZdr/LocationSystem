package com.home.project.locationsystem.mapper;

import org.mapstruct.Mapper;

import com.home.project.locationsystem.dto.LocationAccessDTO;
import com.home.project.locationsystem.entity.LocationAccess;

@Mapper(componentModel = "spring")
public interface LocationAccessMapper {

	LocationAccessDTO toDTO(LocationAccess locationAccess);

	LocationAccess toEntity(LocationAccessDTO locationAccessDTO);
}
