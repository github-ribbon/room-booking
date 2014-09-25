package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonGroupPK implements Serializable{

	private static final long serialVersionUID = 1L;	

	@Column(name="person_group_id",length=50)
	private String personGroupId;

	public String getPersonGroupId() {
		return personGroupId;
	}

	public void setPersonGroupId(String personGroupId) {
		this.personGroupId = personGroupId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((personGroupId == null) ? 0 : personGroupId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PersonGroupPK)) {
			return false;
		}
		PersonGroupPK other = (PersonGroupPK) obj;
		if (personGroupId == null) {
			if (other.personGroupId != null) {
				return false;
			}
		} else if (!personGroupId.equals(other.personGroupId)) {
			return false;
		}
		return true;
	}	
}
