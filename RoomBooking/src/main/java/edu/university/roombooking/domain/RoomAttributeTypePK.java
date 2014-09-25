package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoomAttributeTypePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="room_attribute_type_id",length=50)
	private String roomAttributeTypeId;

	public RoomAttributeTypePK() {	
	}
	
	public RoomAttributeTypePK(String roomAttributeTypeId) {
		this.roomAttributeTypeId=roomAttributeTypeId;
	}

	public String getRoomAttributeTypeId() {
		return roomAttributeTypeId;
	}

	public void setRoomAttributeTypeId(String roomAttributeTypeId) {
		this.roomAttributeTypeId = roomAttributeTypeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((roomAttributeTypeId == null) ? 0 : roomAttributeTypeId
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RoomAttributeTypePK))
			return false;
		RoomAttributeTypePK other = (RoomAttributeTypePK) obj;
		if (roomAttributeTypeId == null) {
			if (other.roomAttributeTypeId != null)
				return false;
		} else if (!roomAttributeTypeId.equals(other.roomAttributeTypeId))
			return false;
		return true;
	}   
}
