package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class UsrPK implements Serializable{

	private static final long serialVersionUID = 1L;	

	@Column(length=50,name="usr_id")
	private String usrId;	

	public UsrPK(){		
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
		if (!(obj instanceof UsrPK)) {
			return false;
		}
		UsrPK other = (UsrPK) obj;
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