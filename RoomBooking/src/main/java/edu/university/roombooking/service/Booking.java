package edu.university.roombooking.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.university.roombooking.domain.BookableRoomPK;
import edu.university.roombooking.domain.Reservation;
import edu.university.roombooking.domain.ReservationPK;
import edu.university.roombooking.repository.BuildingDAO;
import edu.university.roombooking.repository.CampusDAO;
import edu.university.roombooking.repository.ReservationDAO;
import edu.university.roombooking.repository.RoomAttributeDAO;
import edu.university.roombooking.repository.RoomAttributeTypeDAO;
import edu.university.roombooking.repository.RoomDAO;


@Service
@Transactional(readOnly=true)
public class Booking {

	@Autowired
	protected CampusDAO campusDAO;	

	@Autowired
	protected BuildingDAO buildingDAO;	

	@Autowired
	protected RoomDAO roomDAO;

	@Autowired
	protected RoomAttributeDAO roomAttributeDAO;

	@Autowired
	protected RoomAttributeTypeDAO roomAttributeTypeDAO;

	@Autowired
	protected ReservationDAO reservationDAO;	


	public Reservation getReservation(ReservationPK reservationPK){
		return reservationDAO.read(reservationPK);
	}

	@Transactional(readOnly=false)
	public void cancelReservation(ReservationPK reservationPK){

		Reservation reservation=getReservation(reservationPK);		
		reservation.setDbStatusId("D");
		reservation.setModified(new Date());

		reservationDAO.update(reservation);
	}	

	public List<Reservation> getReservationsByDayAndRoom(BookableRoomPK bookableRoomPK,Date date){
		return reservationDAO.findReservationsByDayAndRoom(bookableRoomPK,date);
	}	

	public boolean isReservation(ReservationPK reservationPK){

		if(reservationDAO.read(reservationPK)==null){
			return false;
		}else{
			return true;
		}	
	}	

	public boolean isOverlapped(Date start,Date end,BookableRoomPK bookableRoomPK){

		if(reservationDAO.findOverlappedReservation(start, end, bookableRoomPK)!=null){
			return true; 
		}else
			return false;

	}		

	public List<Reservation> getReservationChildren(Reservation reservation){		
		return reservation.getReservationChildren();
	}
}
