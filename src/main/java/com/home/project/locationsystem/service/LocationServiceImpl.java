package com.home.project.locationsystem.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.project.locationsystem.dto.LocationDTO;
import com.home.project.locationsystem.entity.AccessLevel;
import com.home.project.locationsystem.entity.Location;
import com.home.project.locationsystem.entity.LocationAccess;
import com.home.project.locationsystem.entity.User;
import com.home.project.locationsystem.mapper.LocationMapper;
import com.home.project.locationsystem.repository.LocationAccessRepository;
import com.home.project.locationsystem.repository.LocationRepository;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private LocationAccessRepository locationAccessRepository;

	@Autowired
	private LocationMapper locationMapper;

	@Override
	public LocationDTO createLocation(LocationDTO locationDTO, User owner) {

		Location location = locationMapper.toLocation(locationDTO);
		location.setOwner(owner);
		Location savedLocation = locationRepository.save(location);

		return locationMapper.toLocationDTO(savedLocation);
	}

	@Override
	public List<LocationDTO> getAllLocations(User user) {

		// Fetch and map locations owned and shared with the user
		List<Location> accessibleLocations = locationAccessRepository.findAllByUser(user).stream()
				.map(LocationAccess::getLocation).collect(Collectors.toList());

		List<Location> ownedLocations = locationRepository.findAllByOwner(user);

		return Stream.concat(accessibleLocations.stream(), ownedLocations.stream()).distinct()
				.map(locationMapper::toLocationDTO).collect(Collectors.toList());
	}

	@Override
	public void shareLocation(Location location, User user, AccessLevel accessLevel) {
		LocationAccess locationAccess = new LocationAccess();
		locationAccess.setLocation(location);
		locationAccess.setUser(user);
		locationAccess.setAccessLevel(accessLevel);
		locationAccessRepository.save(locationAccess);
	}

	@Override
	public void updateAccess(Location location, User user, AccessLevel newAccessLevel) {
		LocationAccess locationAccess = locationAccessRepository.findByLocationAndUser(location, user)
				.orElseThrow(() -> new RuntimeException("Access record not found"));
		locationAccess.setAccessLevel(newAccessLevel);
		locationAccessRepository.save(locationAccess);
	}

}
