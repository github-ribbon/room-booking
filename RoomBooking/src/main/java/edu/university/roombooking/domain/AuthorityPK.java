package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AuthorityPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="authority_id",length=20)
	private String authorityId;

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authorityId == null) ? 0 : authorityId.hashCode());
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
		if (!(obj instanceof AuthorityPK)) {
			return false;
		}
		AuthorityPK other = (AuthorityPK) obj;
		if (authorityId == null) {
			if (other.authorityId != null) {
				return false;
			}
		} else if (!authorityId.equals(other.authorityId)) {
			return false;
		}
		return true;
	}	
}
