package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="person_group_person")
@Cacheable(false)
public class PersonGroupPerson implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonGroupPersonPK id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "person_id", referencedColumnName = "person_id", insertable = false, updatable = false)
	private Person person;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "person_group_id", referencedColumnName = "person_group_id", insertable = false, updatable = false)
	private PersonGroup personGroup;    

	public PersonGroupPerson(){
	}

	public PersonGroupPersonPK getId() {
		return id;
	}

	public void setId(PersonGroupPersonPK id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public PersonGroup getPersonGroup() {
		return personGroup;
	}

	public void setPersonGroup(PersonGroup personGroup) {
		this.personGroup = personGroup;
	}
}
