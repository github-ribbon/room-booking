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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;



@Entity
@Table(name = "bookable_room")
@Cacheable(false)
public class BookableRoom implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BookableRoomPK id;

	@Column(nullable = false, name = "is_robot")
	private boolean isRobot;

	@OneToOne(fetch = LAZY)
	@JoinColumns({
		@JoinColumn(name="campus_id", referencedColumnName="campus_id",insertable=false,updatable=false),
		@JoinColumn(name="building_id", referencedColumnName="building_id",insertable=false,updatable=false),
		@JoinColumn(name="room_id", referencedColumnName="room_id",insertable=false,updatable=false)
	})
	private Room room;

	@OneToMany(mappedBy="bookableRoom", fetch = LAZY)
	@OrderBy
	private List<Reservation> reservations;


	@OneToMany(mappedBy="bookableRoom", fetch = LAZY)
	@OrderBy
	private List<RoomManager> roomManagers;


	public BookableRoom() {
	}

	public BookableRoomPK getId() {
		return id;
	}


	public void setId(BookableRoomPK id) {
		this.id = id;
	}

	public boolean getIsRobot() {
		return isRobot;
	}

	public void setIsRobot(boolean isRobot) {
		this.isRobot = isRobot;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<RoomManager> getRoomManagers() {
		return roomManagers;
	}

	public void setRoomManagers(List<RoomManager> roomManagers) {
		this.roomManagers = roomManagers;
	}
}