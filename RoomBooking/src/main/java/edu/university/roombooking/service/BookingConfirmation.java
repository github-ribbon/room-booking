package edu.university.roombooking.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.university.roombooking.domain.Building;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.Campus;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.domain.Reservation;
import edu.university.roombooking.domain.ReservationPK;
import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomAttributePK;
import edu.university.roombooking.domain.RoomAttributeType;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.domain.RoomManager;
import edu.university.roombooking.domain.RoomManagerPK;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.domain.UsrPK;
import edu.university.roombooking.repository.RoomManagerDAO;
//import edu.university.roombooking.service.abstraction.BookingConfirmation;



@Service
public class BookingConfirmation extends Booking 
//implements BookingConfirmation
{

	@Autowired
	private RoomManagerDAO roomManagerDAO;


	public RoomManager getRoomManager(RoomManagerPK roomManagerPK){
		return roomManagerDAO.read(roomManagerPK);
	}	

	public List<Campus> getManagedCampuses(UsrPK usrPK){		
		return campusDAO.findManagedCampuses(usrPK);
	}	

	//@Override
	public List<Building> getManagedBuildings(CampusPK campusPK,UsrPK usrPK){		
		return buildingDAO.findManagedBuildings(campusPK,usrPK);
	}

	//@Override
	public List<Building> getAllManagedBuildings(UsrPK usrPK){		
		return buildingDAO.findAllManagedBuildings(usrPK);
	}

	//@Override
	public List<Room> getManagedRooms(BuildingPK buildingPK,UsrPK usrPK){		
		return roomDAO.findManagedRoomsByBuilding(buildingPK,usrPK);
	}	

	//@Override
	public List<Room> getAllManagedRooms(UsrPK usrPK){		
		return roomDAO.findAllManagedRooms(usrPK);
	}

	//@Override
	public List<RoomAttributeType> getAllManagedRoomAttributeTypes(UsrPK usrPK){		
		return roomAttributeTypeDAO.findAllManagedRoomAttributeTypes(usrPK);
	}

	//@Override
	public List<Object[]> getManagedRooms(RoomAttributeTypePK roomAttributeTypePK,UsrPK usrPK){
		return roomDAO.findManagedRoomsByRoomAttributeType(roomAttributeTypePK,usrPK);						
	}

	//@Override
	public boolean isCampusManaged(CampusPK campusPK,UsrPK usrPK){
		if(roomManagerDAO.findManagedCampus(campusPK, usrPK)!=null){
			return true;
		}else
			return false;
	}

	//@Override
	public boolean isBuildingManaged(BuildingPK buildingPK,UsrPK usrPK){
		if(roomManagerDAO.findManagedBuilding(buildingPK, usrPK)!=null){
			return true;
		}else
			return false;
	}

	//@Override
	public boolean isRoomManaged(RoomPK roomPK,UsrPK usrPK){
		if(roomManagerDAO.findManagedRoom(roomPK, usrPK)!=null){
			return true;
		}else
			return false;
	}

	//@Override
	public boolean isRoomAttributeTypeManaged(RoomAttributeTypePK roomAttributeTypePK,UsrPK usrPK){
		if(roomAttributeTypeDAO.findManagedRoomAttributeType(roomAttributeTypePK, usrPK)!=null){
			return true;
		}else
			return false;
	}

	//@Override
	public boolean isRoomAttributeManaged(RoomAttributePK roomAttributePK,UsrPK usrPK){
		if(roomAttributeDAO.findManagedRoomAttribute(roomAttributePK, usrPK)!=null){
			return true;
		}else
			return false;
	}

	//@Override
	public List<Reservation> getPreviousHandledWaitingReservations(UsrPK usrPK){
		return reservationDAO.findPreviousHandledWaitingReservations(usrPK);		
	}	

	//@Override
	public List<Reservation> getUpcomingHandledWaitingReservations(UsrPK usrPK){
		return reservationDAO.findUpcomingHandledWaitingReservations(usrPK);		
	}	

	//@Override
	public List<Reservation> getPreviousHandledBookedReservations(UsrPK usrPK){
		return reservationDAO.findPreviousHandledBookedReservations(usrPK);		
	}

	//@Override
	public List<Reservation> getUpcomingHandledBookedReservations(UsrPK usrPK){
		return reservationDAO.findUpcomingHandledBookedReservations(usrPK);		
	}	

	//@Override
	public List<Reservation> getPreviousHandledCancelledReservations(UsrPK usrPK){
		return reservationDAO.findPreviousHandledCancelledReservations(usrPK);		
	}	

	//@Override
	public List<Reservation> getUpcomingHandledCancelledReservations(UsrPK usrPK){
		return reservationDAO.findUpcomingHandledCancelledReservations(usrPK);		
	}

	//@Override
	@Transactional(readOnly=false)
	public void confirmReservation(ReservationPK reservationPK){

		Reservation reservation=getReservation(reservationPK);		

		reservation.setDbStatusId("U");
		reservation.setModified(new Date());

		reservationDAO.update(reservation);
	}

	//@Override
	public boolean confirmerHandlesReservation(Reservation reservation,UsrPK usrPK) {	

		if(reservation!=null){
			RoomPK roomPK=new RoomPK();
			roomPK.setCampusId(reservation.getCampusId());
			roomPK.setBuildingId(reservation.getBuildingId());
			roomPK.setRoomId(reservation.getRoomId());

			if(roomManagerDAO.findRoomManager(roomPK, usrPK)!=null){
				return true;
			}else{
				return false;
			}	
		}else{
			return false;
		}
	}	
}
