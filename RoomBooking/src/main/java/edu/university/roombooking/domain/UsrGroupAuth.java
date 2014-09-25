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
@Cacheable(false)
@Table(name = "usr_group_auth")
public class UsrGroupAuth implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsrGroupAuthPK id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "usr_group_id", referencedColumnName = "usr_group_id", insertable = false, updatable = false)
	private UsrGroup usrGroup;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "authority_id", referencedColumnName = "authority_id", insertable = false, updatable = false)
	private Authority authority;

	public UsrGroupAuthPK getId() {
		return id;
	}

	public void setId(UsrGroupAuthPK id) {
		this.id = id;
	}	

	public UsrGroup getUsrGroup() {
		return usrGroup;
	}

	public void setUsrGroup(UsrGroup usrGroup) {
		this.usrGroup = usrGroup;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}	
}
