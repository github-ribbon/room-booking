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
@Table(name="usr_group")
@Cacheable(false)
public class UsrGroup implements Serializable{

	private static final long serialVersionUID = 1L;	

	@EmbeddedId
	private UsrGroupPK id;

	@Column(length=300)
	private String description;	

	@OneToMany(mappedBy="usrGroup", fetch = LAZY)
	@OrderBy
	private List<UsrGroupUsr> usrGroupUsers;

	@OneToMany(mappedBy="usrGroup", fetch = LAZY)	
	private List<RoomManager> roomManagers;

	@OneToMany(mappedBy="usrGroup", fetch = LAZY)	
	private List<UsrGroupAuth> usrGroupAuths;

	public UsrGroup(){
	}

	public UsrGroupPK getId() {
		return id;
	}

	public void setId(UsrGroupPK id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UsrGroupUsr> getUsrGroupUsers() {
		return usrGroupUsers;
	}

	public void setUsrGroupUsers(List<UsrGroupUsr> usrGroupUsrs) {
		this.usrGroupUsers = usrGroupUsrs;
	}	

	public List<UsrGroupAuth> getUsrGroupAuths() {
		return usrGroupAuths;
	}

	public void setUsrGroupAuths(List<UsrGroupAuth> usrGroupAuths) {
		this.usrGroupAuths = usrGroupAuths;
	}

	public List<RoomManager> getRoomManagers() {
		return roomManagers;
	}

	public void setRoomManagers(List<RoomManager> roomManagers) {
		this.roomManagers = roomManagers;
	}
}
