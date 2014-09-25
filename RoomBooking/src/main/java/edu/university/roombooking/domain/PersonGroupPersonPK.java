package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonGroupPersonPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="person_group_id",length=50)
	private String personGroupId;

	@Column(name="person_id")
	private int personId;	

	public PersonGroupPersonPK(){		
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

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
		result = prime * result + personId;
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
		if (!(obj instanceof PersonGroupPersonPK)) {
			return false;
		}
		PersonGroupPersonPK other = (PersonGroupPersonPK) obj;
		if (personGroupId == null) {
			if (other.personGroupId != null) {
				return false;
			}
		} else if (!personGroupId.equals(other.personGroupId)) {
			return false;
		}
		if (personId != other.personId) {
			return false;
		}
		return true;
	}	
}
