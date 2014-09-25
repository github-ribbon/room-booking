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
import javax.persistence.Table;


@Entity
@Table(name="room_attribute_type")
@Cacheable(false)
public class RoomAttributeType implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoomAttributeTypePK id;

	@Column(length=300)
	private String description;

	@OneToMany(mappedBy="roomAttributeType", fetch = LAZY)
	@OrderBy
	private List<RoomAttribute> roomAttributes;

	public RoomAttributeType() {
	}

	public RoomAttributeTypePK getId() {
		return id;
	}

	public void setId(RoomAttributeTypePK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RoomAttribute> getRoomAttributes() {
		return this.roomAttributes;
	}

	public void setRoomAttributes(List<RoomAttribute> roomAttributes) {
		this.roomAttributes = roomAttributes;
	}
}