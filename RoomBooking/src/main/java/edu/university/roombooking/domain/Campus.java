package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;


@Entity
@Cacheable(false)
public class Campus implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CampusPK id;

	@Column(length=50,nullable=false)
	private String name;

	@Column(length=300)
	private String description;

	@OneToMany(mappedBy="campus", fetch = LAZY)
	@OrderBy
	private List<Building> buildings;

	public Campus() {
	}

	public CampusPK getId() {
		return id;
	}

	public void setId(CampusPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Building> getBuildings() {
		return this.buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}
}