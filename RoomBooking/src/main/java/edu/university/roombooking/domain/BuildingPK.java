package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BuildingPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="campus_id")
	private String campusId;

	@Column(name="building_id",length=10)
	private String buildingId;

	public BuildingPK() {
	}
	
	public BuildingPK(String campusId, String buildingId) {
		this.campusId=campusId;
		this.buildingId=buildingId;		
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
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BuildingPK))
			return false;
		BuildingPK other = (BuildingPK) obj;
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
		return true;
	}
}