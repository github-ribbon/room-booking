package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;


@Entity
@Cacheable(false)
public class Building implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BuildingPK id;

	@Column(length=50,nullable=false)
	private String name;

	@Column(length=300)
	private String description; 

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "campus_id", referencedColumnName = "campus_id", insertable = false, updatable = false)
	private Campus campus;

	@OneToMany(mappedBy="building", fetch = LAZY)
	@OrderBy
	private List<Room> rooms;

	public Building() {
	}

	public BuildingPK getId() {
		return this.id;
	}

	public void setId(BuildingPK id) {
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

	public Campus getCampus() {
		return this.campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}