package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity
@Cacheable(false)
public class Room implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoomPK id;

	@Column(length=300)
	private String description;

	@ManyToOne(fetch = LAZY)
	@JoinColumns({
		@JoinColumn(name="campus_id", referencedColumnName="campus_id",insertable=false,updatable=false),
		@JoinColumn(name="building_id", referencedColumnName="building_id",insertable=false,updatable=false)
	})
	private Building building;

	@OneToMany(mappedBy="room", fetch = LAZY)
	@OrderBy
	private List<RoomAttribute> roomAttributes;

	@OneToOne(mappedBy="room", fetch = LAZY)
	@OrderBy
	private BookableRoom bookableRoom;

	public Room() {
	}

	public RoomPK getId() {
		return this.id;
	}

	public void setId(RoomPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Building getBuilding() {
		return this.building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public List<RoomAttribute> getRoomAttributes() {
		return this.roomAttributes;
	}

	public void setRoomAttributes(List<RoomAttribute> roomAttributes) {
		this.roomAttributes = roomAttributes;
	}	

	public BookableRoom getBookableRoom() {
		return bookableRoom;
	}

	public void setRoomBooking(BookableRoom bookableRoom) {
		this.bookableRoom = bookableRoom;
	}
}