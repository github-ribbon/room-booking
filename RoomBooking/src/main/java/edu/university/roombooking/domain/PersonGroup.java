package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="person_group")
@Cacheable(false)
public class PersonGroup implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonGroupPK id;

	@Column(length=300)
	private String description;

	@OneToMany(mappedBy="personGroup", fetch = LAZY)
	@OrderBy
	private List<PersonGroupPerson> personGroupPersons;

	@OneToMany(mappedBy="personGroup", fetch = LAZY)
	@OrderBy
	private List<Reservation> reservations;

	public PersonGroup(){
	}	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	

	public List<PersonGroupPerson> getPersonGroupPersons() {
		return personGroupPersons;
	}

	public void setPersonGroupPersons(List<PersonGroupPerson> personGroupPersons) {
		this.personGroupPersons = personGroupPersons;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public PersonGroupPK getId() {
		return id;
	}

	public void setId(PersonGroupPK id) {
		this.id = id;
	}	
}
