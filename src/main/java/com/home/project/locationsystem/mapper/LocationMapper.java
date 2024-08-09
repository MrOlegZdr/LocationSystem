package com.home.project.locationsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.home.project.locationsystem.dto.LocationDTO;
import com.home.project.locationsystem.entity.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {

	@Mapping(source = "owner.id", target = "ownerId")
	LocationDTO toLocationDTO(Location location);

	@Mapping(target = "owner", ignore = true) // Set owner manually in the service layer
	Location toLocation(LocationDTO locationDTO);
}
