package com.home.project.locationsystem.service;

import java.util.Optional;

import com.home.project.locationsystem.entity.Location;

public interface LocationService {

	Location createLocation(Long ownerId, String name, String address);

	Optional<Location> findLocationById(Long locationId);

}
