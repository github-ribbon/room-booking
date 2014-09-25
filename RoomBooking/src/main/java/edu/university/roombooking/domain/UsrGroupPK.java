package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class UsrGroupPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(length=50,name="usr_group_id")
	private String usrGroupId;	 

	public UsrGroupPK(){		
	}

	public String getUsrGroupId() {
		return usrGroupId;
	}

	public void setUsrGroupId(String usrGroupId) {
		this.usrGroupId = usrGroupId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((usrGroupId == null) ? 0 : usrGroupId.hashCode());
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
		if (!(obj instanceof UsrGroupPK)) {
			return false;
		}
		UsrGroupPK other = (UsrGroupPK) obj;
		if (usrGroupId == null) {
			if (other.usrGroupId != null) {
				return false;
			}
		} else if (!usrGroupId.equals(other.usrGroupId)) {
			return false;
		}
		return true;
	}	
}
