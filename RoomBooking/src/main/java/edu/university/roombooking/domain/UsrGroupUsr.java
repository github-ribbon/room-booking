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
@Table(name="usr_group_usr")
@Cacheable(false)
public class UsrGroupUsr implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsrGroupUsrPK id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "usr_id", referencedColumnName = "usr_id", insertable = false, updatable = false)
	private Usr usr;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "usr_group_id", referencedColumnName = "usr_group_id", insertable = false, updatable = false)
	private UsrGroup usrGroup;    

	public UsrGroupUsr(){
	}

	public UsrGroupUsrPK getId() {
		return id;
	}

	public void setId(UsrGroupUsrPK id) {
		this.id = id;
	}

	public Usr getUsr() {
		return usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

	public UsrGroup getUsrGroup() {
		return usrGroup;
	}

	public void setUsrGroup(UsrGroup usrGroup) {
		this.usrGroup = usrGroup;
	}
}