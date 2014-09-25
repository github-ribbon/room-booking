package edu.university.roombooking.domain;

import java.io.Serializable;

public class ReservationPK implements Serializable{

	private static final long serialVersionUID = 1L;		

	private int reservationId;	

	public ReservationPK(){		
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reservationId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ReservationPK))
			return false;
		ReservationPK other = (ReservationPK) obj;
		if (reservationId != other.reservationId)
			return false;
		return true;
	}		
}
