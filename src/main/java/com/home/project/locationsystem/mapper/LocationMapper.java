package com.home.project.locationsystem.mapper;

import org.mapstruct.Mapper;

import com.home.project.locationsystem.dto.LocationDTO;
import com.home.project.locationsystem.entity.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {

	LocationDTO toDTO(Location location);

	Location toEntity(LocationDTO locationDTO);
}
