package com.home.project.locationsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.project.locationsystem.entity.AccessLevel;
import com.home.project.locationsystem.entity.Location;
import com.home.project.locationsystem.entity.LocationAccess;
import com.home.project.locationsystem.entity.User;
import com.home.project.locationsystem.repository.LocationAccessRepository;
import com.home.project.locationsystem.repository.LocationRepository;
import com.home.project.locationsystem.repository.UserRepository;

@Service
public class LocationAccessServiceImpl implements LocationAccessService {

	@Autowired
	private LocationAccessRepository locationAccessRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public LocationAccess shareLocation(Long locationId, Long userId, AccessLevel accessLevel) {
		Optional<Location> location = locationRepository.findById(locationId);
		Optional<User> user = userRepository.findById(userId);

		if (location.isPresent() && user.isPresent()) {
			LocationAccess locationAccess = new LocationAccess();
			locationAccess.setLocation(location.get());
			locationAccess.setUser(user.get());
			locationAccess.setAccessLevel(accessLevel);
			return locationAccessRepository.save(locationAccess);
		} else {
			throw new IllegalArgumentException("Location or User not found");
		}

	}

	@Override
	public Optional<LocationAccess> findLocationAccess(Long locationId, Long userId) {
		return locationAccessRepository.findAll().stream().filter(
				access -> access.getLocation().getId().equals(locationId) && access.getUser().getId().equals(userId))
				.findFirst();
	}

	@Override
	@Transactional
	public void updateAccessLevel(Long locationId, Long userId, AccessLevel accessLevel) {
		Optional<LocationAccess> locationAccess = findLocationAccess(locationId, userId);
		if (locationAccess.isPresent()) {
			LocationAccess access = locationAccess.get();
			access.setAccessLevel(accessLevel);
			locationAccessRepository.save(access);
		} else {
			throw new IllegalArgumentException("Access not found for the given location and user");
		}
	}

}
