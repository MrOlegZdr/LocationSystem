package com.home.project.locationsystem.entity;

import javax.persistence.*;

@Entity
public class LocationAccess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	private AccessLevel accessLevel;

	public LocationAccess() {
	}

	public LocationAccess(Long id, Location location, User user, AccessLevel accessLevel) {
		this.id = id;
		this.location = location;
		this.user = user;
		this.accessLevel = accessLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	@Override
	public String toString() {
		return "LocationAccess [id=" + id + ", location=" + location + ", user=" + user + ", accessLevel=" + accessLevel
				+ "]";
	}

}
