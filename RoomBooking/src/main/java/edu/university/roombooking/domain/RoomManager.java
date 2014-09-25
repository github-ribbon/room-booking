package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="room_manager")
@Cacheable(false)
public class RoomManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoomManagerPK id; 

	@ManyToOne(fetch = LAZY)
	@JoinColumns({
		@JoinColumn(name="campus_id", referencedColumnName="campus_id",insertable=false,updatable=false),
		@JoinColumn(name="building_id", referencedColumnName="building_id",insertable=false,updatable=false),
		@JoinColumn(name="room_id", referencedColumnName="room_id",insertable=false,updatable=false)
	})
	private BookableRoom bookableRoom;		

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "usr_group_id", referencedColumnName = "usr_group_id", insertable = false, updatable = false)
	private UsrGroup usrGroup;	

	public RoomManager() {
	}

	public RoomManagerPK getId() {
		return id;
	}

	public void setId(RoomManagerPK id) {
		this.id = id;
	}

	public BookableRoom getBookableRoom() {
		return bookableRoom;
	}

	public void setBookableRoom(BookableRoom bookableRoom) {
		this.bookableRoom = bookableRoom;
	}

	public UsrGroup getUsrGroup() {
		return usrGroup;
	}

	public void setUsrGroup(UsrGroup usrGroup) {
		this.usrGroup = usrGroup;
	}	
}