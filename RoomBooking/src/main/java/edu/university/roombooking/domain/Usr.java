package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;


@Entity
@Cacheable(false)
public class Usr implements Serializable {

	private static final long serialVersionUID = 1L;	

	@EmbeddedId
	private UsrPK id;	

	@Column(name="person_id",unique = true)
	private Integer personId;	

	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "person_id", referencedColumnName = "person_id", insertable = false, updatable = false)
	private Person person;

	@Column(nullable = false, length = 50)
	private String password;

	@Column(nullable = false, length = 50, unique = true)
	private String email;

	@Column(nullable = false, name = "is_enabled")
	private boolean isEnabled;

	@OneToMany(mappedBy="usr", fetch = LAZY)
	@OrderBy
	private List<UsrGroupUsr> usrGroupUsers;

	@OneToMany(mappedBy="usr", fetch = LAZY)  
	private List<Reservation> reservations;

	public Usr(){		
	}	

	public UsrPK getId() {
		return id;
	}

	public void setId(UsrPK id) {
		this.id = id;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<UsrGroupUsr> getUsrGroupUsers() {
		return usrGroupUsers;
	}

	public void setUsrGroupUsers(List<UsrGroupUsr> usrGroupUsrs) {
		this.usrGroupUsers = usrGroupUsrs;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}	
}
