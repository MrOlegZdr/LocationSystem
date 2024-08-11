package com.home.project.locationsystem.service;

import java.util.Optional;

import com.home.project.locationsystem.entity.AccessLevel;
import com.home.project.locationsystem.entity.LocationAccess;

public interface LocationAccessService {

	LocationAccess shareLocation(Long locationId, Long userId, AccessLevel accessLevel);

	Optional<LocationAccess> findLocationAccess(Long locationId, Long userId);

	void updateAccessLevel(Long locationId, Long userId, AccessLevel accessLevel);
}
