package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

@Entity
@IdClass(value = PersonPK.class)
@Cacheable(false)
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="person_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = SEQUENCE, generator = "person_seq")
	@SequenceGenerator(name = "person_seq", initialValue = 10000, allocationSize = 1)
	private int personId;

	/*
	@EmbeddedId
	private PersonPK id;	
	 */

	@Column(name="given_name",length=50,nullable=false)
	private String givenName;

	@Column(name="family_name",length=50,nullable=false)
	private String familyName;

	@OneToOne(mappedBy="person", fetch = LAZY)
	@OrderBy
	private Usr usr;


	@OneToMany(mappedBy="person", fetch = LAZY)
	@OrderBy
	private List<PersonGroupPerson> personGroupPersons;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public Person(){		
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public Usr getUsr() {
		return usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

	public List<PersonGroupPerson> getPersonGroupPersons() {
		return personGroupPersons;
	}

	public void setPersonGroupPersons(List<PersonGroupPerson> personGroupPersons) {
		this.personGroupPersons = personGroupPersons;
	}	
}
