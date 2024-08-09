package com.home.project.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.project.locationsystem.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

}
