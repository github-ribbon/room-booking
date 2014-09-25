package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;


@Entity
@IdClass(value = ReservationPK.class)
@Cacheable(false)
public class Reservation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Transient
	public static final int WAITING=0;
	@Transient
	public static final int BOOKED=1;
	@Transient
	public static final int ABORTED=2;	

	/*
	@EmbeddedId
	private ReservationPK id;
	 */

	@Id
	@Column(name="reservation_id")
	@GeneratedValue(strategy = SEQUENCE, generator = "reservation_seq")
	@SequenceGenerator(name = "reservation_seq", initialValue = 10000, allocationSize = 1)
	private int reservationId;

	@Column(name="time_from",nullable=false)
	@Temporal(TIMESTAMP)
	private Date timeFrom;

	@Column(name="time_to",nullable=false)
	@Temporal(TIMESTAMP)
	private Date timeTo;

	@Column(name="reservation_date",nullable=false)
	@Temporal(TIMESTAMP)
	private Date reservationDate;

	@Column(nullable=false)
	@Temporal(TIMESTAMP)
	private Date created;

	@Column(nullable=false)
	@Temporal(TIMESTAMP)
	private Date modified;

	@Column(length=300)
	private String purpose;

	@Column(name="campus_id", length=10,updatable = false, nullable = false)
	private String campusId;

	@Column(name="building_id",length=10,updatable = false, nullable = false)
	private String buildingId;

	@Column(name="room_id",length=10,updatable = false, nullable = false)
	private String roomId;

	@Column(name="usr_id",length=50,nullable=false, updatable = false)
	private String usrId;	

	@Column(name="person_group_id",length=50,nullable=false)
	private String personGroupId;

	@Column(name="reservation_parent_id", nullable = true)
	@Basic(fetch = LAZY)
	private Integer reservationParentId;

	@Column(name="db_status_id",length=1,nullable = false)
	private String dbStatusId;	

	@ManyToOne(fetch = LAZY)
	@JoinColumns({
		@JoinColumn(name="campus_id", referencedColumnName="campus_id",insertable=false,updatable=false),
		@JoinColumn(name="building_id", referencedColumnName="building_id",insertable=false,updatable=false),
		@JoinColumn(name="room_id", referencedColumnName="room_id",insertable=false,updatable=false)
	})
	private BookableRoom bookableRoom;	

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "usr_id", referencedColumnName = "usr_id", insertable = false, updatable = false)
	private Usr usr;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "person_group_id", referencedColumnName = "person_group_id", insertable = false, updatable = false)
	private PersonGroup personGroup;

	@OneToMany(fetch = LAZY)
	@JoinColumn(name = "reservation_parent_id", referencedColumnName = "reservation_id", insertable = false, updatable = false)	
	private List<Reservation> reservationChildren;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "reservation_id", referencedColumnName = "reservation_parent_id", insertable = false, updatable = false)	
	private Reservation reservationParent;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "db_status_id", referencedColumnName = "db_status_id", insertable = false, updatable = false)
	private DbStatus dbStatus;	

	public Reservation(){		
	}

	public Date getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Date getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getCampusId() {
		return campusId;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getPersonGroupId() {
		return personGroupId;
	}

	public void setPersonGroupId(String personGroupId) {
		this.personGroupId = personGroupId;
	}

	public Integer getReservationParentId() {
		return reservationParentId;
	}

	public void setReservationParentId(Integer reservationParentId) {
		this.reservationParentId = reservationParentId;
	}

	public String getDbStatusId() {
		return dbStatusId;
	}

	public void setDbStatusId(String dbStatusId) {
		this.dbStatusId = dbStatusId;
	}

	public BookableRoom getBookableRoom() {
		return bookableRoom;
	}

	public void setBookableRoom(BookableRoom bookableRoom) {
		this.bookableRoom = bookableRoom;
	}

	public Usr getUsr() {
		return usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

	public PersonGroup getPersonGroup() {
		return personGroup;
	}

	public void setPersonGroup(PersonGroup personGroup) {
		this.personGroup = personGroup;
	}

	public List<Reservation> getReservationChildren() {
		return reservationChildren;
	}

	public void setReservationChildren(List<Reservation> reservationChildren) {
		this.reservationChildren = reservationChildren;
	}

	public DbStatus getDbStatus() {
		return dbStatus;
	}

	public void setDbStatus(DbStatus dbStatus) {
		this.dbStatus = dbStatus;
	}

	public Reservation getReservationParent() {
		return reservationParent;
	}

	public void setReservationParent(Reservation reservationParent) {
		this.reservationParent = reservationParent;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}	
}
