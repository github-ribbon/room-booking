package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CampusPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="campus_id",length=10)
	private String campusId;

	public CampusPK() {		
	}
	
	public CampusPK(String campusId){
		this.campusId=campusId;
	}

	public String getCampusId() {
		return campusId;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (!(obj instanceof CampusPK))
			return false;
		CampusPK other = (CampusPK) obj;
		if (campusId == null) {
			if (other.campusId != null)
				return false;
		} else if (!campusId.equals(other.campusId))
			return false;
		return true;
	}   
}
