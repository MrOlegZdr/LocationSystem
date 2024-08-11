package com.home.project.locationsystem.dto;

import java.util.List;

public class UserDTO {

	private String name;
	private String email;
	private List<LocationDTO> ownedLocations;
	private List<LocationDTO> sharedLocations;

	public UserDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<LocationDTO> getOwnedLocations() {
		return ownedLocations;
	}

	public void setOwnedLocations(List<LocationDTO> ownedLocations) {
		this.ownedLocations = ownedLocations;
	}

	public List<LocationDTO> getSharedLocations() {
		return sharedLocations;
	}

	public void setSharedLocations(List<LocationDTO> sharedLocations) {
		this.sharedLocations = sharedLocations;
	}

}
