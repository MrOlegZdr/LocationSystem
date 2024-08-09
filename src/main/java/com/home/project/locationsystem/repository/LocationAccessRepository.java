package com.home.project.locationsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.project.locationsystem.entity.Location;
import com.home.project.locationsystem.entity.LocationAccess;
import com.home.project.locationsystem.entity.User;

public interface LocationAccessRepository extends JpaRepository<LocationAccess, Long> {

	List<LocationAccess> findAllByUser(User user);

	Optional<LocationAccess> findByLocationAndUser(Location location, User user);
}
