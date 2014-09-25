package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="db_status")
@Cacheable(false)
public class DbStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DbStatusPK id;	

	@OneToMany(mappedBy="dbStatus", fetch = LAZY)
	@OrderBy
	private List<Reservation> reservations;

	public DbStatus(){		
	}

	public DbStatusPK getId() {
		return id;
	}

	public void setId(DbStatusPK id) {
		this.id = id;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((reservations == null) ? 0 : reservations.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DbStatus))
			return false;
		DbStatus other = (DbStatus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reservations == null) {
			if (other.reservations != null)
				return false;
		} else if (!reservations.equals(other.reservations))
			return false;
		return true;
	}		
}
