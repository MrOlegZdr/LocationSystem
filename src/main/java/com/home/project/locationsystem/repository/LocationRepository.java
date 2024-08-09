package com.home.project.locationsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.project.locationsystem.entity.Location;
import com.home.project.locationsystem.entity.User;

public interface LocationRepository extends JpaRepository<Location, Long> {

	List<Location> findAllByOwner(User owner);

}
