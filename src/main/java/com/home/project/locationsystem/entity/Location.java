package com.home.project.locationsystem.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String adress;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;

	@OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LocationAccess> sharedUsers = new HashSet<>();

	public Location() {
	}

	public Location(Long id, @NotBlank String name, @NotBlank String adress, User owner,
			Set<LocationAccess> sharedUsers) {
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.owner = owner;
		this.sharedUsers = sharedUsers;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<LocationAccess> getSharedUsers() {
		return sharedUsers;
	}

	public void setSharedUsers(Set<LocationAccess> sharedUsers) {
		this.sharedUsers = sharedUsers;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", adress=" + adress + "]";
	}

}
