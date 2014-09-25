package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="room_attribute")
@Cacheable(false)
public class RoomAttribute implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoomAttributePK id;

	@Column(length=50,nullable=false)
	private String value;	

	@ManyToOne(fetch = LAZY)
	@JoinColumns({
		@JoinColumn(name="campus_id", referencedColumnName="campus_id",insertable=false,updatable=false),
		@JoinColumn(name="building_id", referencedColumnName="building_id",insertable=false,updatable=false),
		@JoinColumn(name="room_id", referencedColumnName="room_id",insertable=false,updatable=false)
	})
	private Room room;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "room_attribute_type_id", referencedColumnName = "room_attribute_type_id", insertable = false, updatable = false)
	private RoomAttributeType roomAttributeType;

	public RoomAttribute() {
	}

	public RoomAttributePK getId() {
		return this.id;
	}

	public void setId(RoomAttributePK id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public RoomAttributeType getRoomAttributeType() {
		return this.roomAttributeType;
	}

	public void setRoomAttributeType(RoomAttributeType roomAttributeType) {
		this.roomAttributeType = roomAttributeType;
	}
}