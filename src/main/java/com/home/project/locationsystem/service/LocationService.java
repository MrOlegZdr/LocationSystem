package com.home.project.locationsystem.service;

import java.util.List;

import com.home.project.locationsystem.dto.LocationDTO;
import com.home.project.locationsystem.entity.AccessLevel;
import com.home.project.locationsystem.entity.Location;
import com.home.project.locationsystem.entity.User;

public interface LocationService {

	LocationDTO createLocation(LocationDTO locationDTO, User owner);

	List<LocationDTO> getAllLocations(User user);

	void shareLocation(Location location, User user, AccessLevel accessLevel);

	void updateAccess(Location location, User user, AccessLevel newAccessLevel);

}
