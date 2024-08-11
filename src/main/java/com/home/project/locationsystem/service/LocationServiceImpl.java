package com.home.project.locationsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.project.locationsystem.entity.Location;
import com.home.project.locationsystem.entity.User;
import com.home.project.locationsystem.repository.LocationRepository;
import com.home.project.locationsystem.repository.UserRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public Location createLocation(Long ownerId, String name, String address) {
		Optional<User> owner = userRepository.findById(ownerId);
		if (owner.isPresent()) {
			Location location = new Location();
			location.setName(name);
			location.setAddress(address);
			location.setOwner(owner.get());
			return locationRepository.save(location);
		} else {
			throw new IllegalArgumentException("Owner not found");
		}
	}

	@Override
	public Optional<Location> findLocationById(Long locationId) {

		return locationRepository.findById(locationId);
	}

}
