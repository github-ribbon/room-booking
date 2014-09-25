package edu.university.roombooking.domain;

import java.io.Serializable;


public class PersonPK implements Serializable {

	private static final long serialVersionUID = 1L;	

	private int personId;	

	public PersonPK(){		
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + personId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PersonPK))
			return false;
		PersonPK other = (PersonPK) obj;
		if (personId != other.personId)
			return false;
		return true;
	}		
}
