package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class UsrGroupAuthPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="authority_id",length = 20)	
	private String authorityId;

	@Column(name="usr_group_id",length=50)
	private String usrGroupId;

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
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
				+ ((authorityId == null) ? 0 : authorityId.hashCode());
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
		if (!(obj instanceof UsrGroupAuthPK)) {
			return false;
		}
		UsrGroupAuthPK other = (UsrGroupAuthPK) obj;
		if (authorityId == null) {
			if (other.authorityId != null) {
				return false;
			}
		} else if (!authorityId.equals(other.authorityId)) {
			return false;
		}
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
