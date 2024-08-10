package com.home.project.locationsystem.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@Email
	@NotBlank
	private String email;

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Location> ownLocations = new HashSet<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LocationAccess> sharedLocations = new HashSet<>();

	public User() {
	}

	public User(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Location> getOwnLocations() {
		return ownLocations;
	}

	public void setOwnLocations(Set<Location> ownLocations) {
		this.ownLocations = ownLocations;
	}

	public void add(Location ownLocation) {

		if (ownLocations == null) {
			ownLocations = new HashSet<>();
		}
		ownLocations.add(ownLocation);
	}

	public Set<LocationAccess> getSharedLocations() {
		return sharedLocations;
	}

	public void setSharedLocations(Set<LocationAccess> sharedLocations) {
		this.sharedLocations = sharedLocations;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}

}
