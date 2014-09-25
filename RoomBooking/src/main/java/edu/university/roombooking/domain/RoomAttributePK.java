package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class RoomAttributePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="campus_id")
	private String campusId;

	@Column(name="building_id")
	private String buildingId;

	@Column(name="room_id")
	private String roomId;

	@Column(name="room_attribute_type_id")
	private String roomAttributeTypeId;	

	public RoomAttributePK() {
	}

	public RoomAttributePK(String campusId, String buildingId, 
			String roomId, String roomAttributeTypeId) {
		this.campusId=campusId;
		this.buildingId=buildingId;
		this.roomId=roomId;
		this.roomAttributeTypeId=roomAttributeTypeId;
	}

	public String getRoomAttributeTypeId() {
		return this.roomAttributeTypeId;
	}

	public void setRoomAttributeTypeId(String roomAttributeTypeId) {
		this.roomAttributeTypeId = roomAttributeTypeId;
	}

	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}	

	public String getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getCampusId() {
		return this.campusId;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((buildingId == null) ? 0 : buildingId.hashCode());
		result = prime * result
				+ ((campusId == null) ? 0 : campusId.hashCode());
		result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
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
		if (!(obj instanceof RoomAttributePK))
			return false;
		RoomAttributePK other = (RoomAttributePK) obj;
		if (buildingId == null) {
			if (other.buildingId != null)
				return false;
		} else if (!buildingId.equals(other.buildingId))
			return false;
		if (campusId == null) {
			if (other.campusId != null)
				return false;
		} else if (!campusId.equals(other.campusId))
			return false;
		if (roomId == null) {
			if (other.roomId != null)
				return false;
		} else if (!roomId.equals(other.roomId))
			return false;
		if (roomAttributeTypeId == null) {
			if (other.roomAttributeTypeId != null)
				return false;
		} else if (!roomAttributeTypeId.equals(other.roomAttributeTypeId))
			return false;
		return true;
	}	
}