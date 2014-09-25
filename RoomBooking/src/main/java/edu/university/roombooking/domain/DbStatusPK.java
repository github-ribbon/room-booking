package edu.university.roombooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DbStatusPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="db_status_id",length=1)
	private String dbStatusId;	

	public DbStatusPK(){		
	}

	public String getDbStatusId() {
		return dbStatusId;
	}

	public void setDbStatusId(String dbStatusId) {
		this.dbStatusId = dbStatusId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dbStatusId == null) ? 0 : dbStatusId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DbStatusPK))
			return false;
		DbStatusPK other = (DbStatusPK) obj;
		if (dbStatusId == null) {
			if (other.dbStatusId != null)
				return false;
		} else if (!dbStatusId.equals(other.dbStatusId))
			return false;
		return true;
	}
}
