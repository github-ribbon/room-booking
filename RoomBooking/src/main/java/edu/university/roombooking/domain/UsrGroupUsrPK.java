package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsrGroupUsrPK implements Serializable {

	private static final long serialVersionUID = 1L;	

	@Column(name="usr_group_id",length=50)
	private String usrGroupId;	

	@Column(name="usr_id",length=50)
	private String usrId;	

	public UsrGroupUsrPK(){		
	}

	public String getUsrGroupId() {
		return usrGroupId;
	}

	public void setUsrGroupId(String usrGroupId) {
		this.usrGroupId = usrGroupId;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((usrGroupId == null) ? 0 : usrGroupId.hashCode());
		result = prime * result + ((usrId == null) ? 0 : usrId.hashCode());
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
		if (!(obj instanceof UsrGroupUsrPK)) {
			return false;
		}
		UsrGroupUsrPK other = (UsrGroupUsrPK) obj;
		if (usrGroupId == null) {
			if (other.usrGroupId != null) {
				return false;
			}
		} else if (!usrGroupId.equals(other.usrGroupId)) {
			return false;
		}
		if (usrId == null) {
			if (other.usrId != null) {
				return false;
			}
		} else if (!usrId.equals(other.usrId)) {
			return false;
		}
		return true;
	}	
}