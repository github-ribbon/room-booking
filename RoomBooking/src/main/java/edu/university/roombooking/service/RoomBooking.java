package edu.university.roombooking.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.university.roombooking.domain.BookableRoom;
import edu.university.roombooking.domain.BookableRoomPK;
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
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.domain.UsrPK;
import edu.university.roombooking.repository.BookableRoomDAO;
//import edu.university.roombooking.service.abstraction.RoomBooking;


@Service
public class RoomBooking extends Booking 
//implements RoomBooking 
{

	@Autowired 
	private BookableRoomDAO bookableRoomDAO;


	//@Override
	public BookableRoom getBookableRoom(BookableRoomPK bookableRoomPK) {			
		return bookableRoomDAO.read(bookableRoomPK);
	}

	//@Override
	public boolean isBookableRoom(BookableRoomPK bookableRoomPK){		
		if(bookableRoomDAO.read(bookableRoomPK)!=null){
			return true;
		}else return false;
	}

	//@Override
	public List<Campus> getAllBookableCampuses(){
		return campusDAO.findBookableCampuses();						
	}	

	//@Override
	public List<Building> getBookableBuildings(CampusPK cadmpusPK){
		return buildingDAO.findBookableBuildings(cadmpusPK);
	}	

	//@Override
	public List<Room> getBookableRooms(BuildingPK buildingPK){
		return roomDAO.findBookableRoomsByBuilding(buildingPK);
	}

	//@Override
	public List<Building> getAllBookableBuildings(){		
		return buildingDAO.findAllBookableBuildings();		
	}

	//@Override
	public List<Room> getAllBookableRooms(){
		return roomDAO.findAllBookableRooms();
	}

	//@Override
	public List<RoomAttributeType> getAllBookableRoomAttributeTypes(){
		return roomAttributeTypeDAO.findBookableRoomAttributeTypes();						
	}	

	//@Override
	public List<Object[]> getBookableRooms(RoomAttributeTypePK roomAttributeTypePK){
		return roomDAO.findBookableRoomsByRoomAttributeType(roomAttributeTypePK);						
	}

	//@Override
	public boolean isCampusBookable(CampusPK campusPK){		
		if(bookableRoomDAO.findBookableCampus(campusPK)!=null){
			return true;
		}else
			return false;		
	}

	//@Override
	public boolean isBuildingBookable(BuildingPK buildingPK){		
		if(bookableRoomDAO.findBookableBuilding(buildingPK)!=null){
			return true;
		}else
			return false;
	}

	//@Override
	public boolean isRoomBookable(RoomPK roomPK){
		if(bookableRoomDAO.findBookableRoom(roomPK)!=null){
			return true;
		}else
			return false;		
	}	

	//@Override
	public boolean isRoomAttributeTypeBookable(RoomAttributeTypePK roomAttributeTypePK){		
		if(roomAttributeTypeDAO.findBookableRoomAttributeType(roomAttributeTypePK)!=null){
			return true;
		}else
			return false;
	}

	//@Override
	public boolean isRoomAttributeBookable(RoomAttributePK roomAttributePK){		
		if(roomAttributeDAO.findBookableRoomAttribute(roomAttributePK)!=null){
			return true;
		}else
			return false;
	}

	//@Override
	public List<Reservation> getAllClientReservations(UsrPK usrPK){
		return reservationDAO.findAllClientReservations(usrPK);
	}

	//@Override
	public List<Reservation> getClientBookedReservations(UsrPK usrPK){
		return reservationDAO.findClientBookedReservations(usrPK);
	}

	//@Override
	public List<Reservation> getClientWaitingReservations(UsrPK usrPK){
		return reservationDAO.findClientWaitingReservations(usrPK);
	}

	//@Override
	public List<Reservation> getClientCancelledReservations(UsrPK usrPK){
		return reservationDAO.findClientCancelledReservations(usrPK);
	}

	//@Override
	public List<BookableRoom> getBusyRooms(Date date){
		return bookableRoomDAO.findBusyRooms(date);
	}

	//@Override
	public List<BookableRoom> getAvailableRooms(Date date){
		return bookableRoomDAO.findAvailableRooms(date);
	}

	//@Override
	@Transactional(readOnly=false)
	public int makeReservation(Reservation reservation,String usrId){

		Date date=new Date();		
		reservation.setCreated(date);
		reservation.setModified(date);

		reservation.setUsrId(usrId);

		BookableRoomPK bookableRoomPK=new BookableRoomPK();		
		bookableRoomPK.setCampusId(reservation.getCampusId());		
		bookableRoomPK.setBuildingId(reservation.getBuildingId());
		bookableRoomPK.setRoomId(reservation.getRoomId());		

		BookableRoom bookableRoom=getBookableRoom(bookableRoomPK);

		int status;

		boolean isRobot=bookableRoom.getIsRobot();								

		if(isRobot){//without confirmation					

			//			overlapping
			if(isOverlapped(reservation.getTimeFrom(), reservation.getTimeTo(), bookableRoomPK)){					
				status=Reservation.ABORTED;											
			}else{				
				//				without overlapping					

				reservation.setDbStatusId("U");		
				status=Reservation.BOOKED; 				
			}

		}else{	
			//			confirmation required
			reservation.setDbStatusId("C");
			status=Reservation.WAITING;						
		}

		if(status!=Reservation.ABORTED)
			reservationDAO.insert(reservation);

		return status;
	}

	//@Override
	@Transactional(readOnly=false)
	public int changeReservation(Reservation modified){

		int status=Reservation.ABORTED;

		ReservationPK reservationPK=new ReservationPK();
		reservationPK.setReservationId(modified.getReservationId());

		Reservation actual=getReservation(reservationPK);

		if(actual.getDbStatusId().equals("C")){

			if((!actual.getReservationDate().equals(modified.getReservationDate()))||
					(!actual.getTimeFrom().equals(modified.getTimeFrom())) ||
					(!actual.getTimeTo().equals(modified.getTimeTo()))){

				//				reservation date changed			

				actual.setReservationDate(modified.getReservationDate());
				actual.setTimeFrom(modified.getTimeFrom());
				actual.setTimeTo(modified.getTimeTo());
			}	

			actual.setPurpose(modified.getPurpose());
			actual.setPersonGroupId(modified.getPersonGroupId());				
			actual.setModified(new Date());				

			if(actual.getBookableRoom().getIsRobot()){

				boolean isOverlapping=isOverlapped(modified.getTimeFrom(),
						modified.getTimeTo(),actual.getBookableRoom().getId());

				//				without overlapping
				if(!isOverlapping){					
					actual.setDbStatusId("U");	
					status=Reservation.BOOKED;
					//					waiting -> booked
				}else{
					status=Reservation.WAITING;
					//					if there is overlapping the reservation will not change its status
					//					waiting -> waiting					
				}

				reservationDAO.update(actual);							

			}else{	
				//				waiting -> waiting				
				reservationDAO.update(actual);				
				status=Reservation.WAITING;
			}	

		}else if(actual.getDbStatusId().equals("U")){

			if((!actual.getReservationDate().equals(modified.getReservationDate()))||
					(!actual.getTimeFrom().equals(modified.getTimeFrom())) ||
					(!actual.getTimeTo().equals(modified.getTimeTo()))){

				//				date changed

				//				adding reservation child 

				Date date=new Date();

				actual.setModified(date);			

				Reservation reservationChild=new Reservation();
				reservationChild.setCampusId(actual.getCampusId());
				reservationChild.setBuildingId(actual.getBuildingId());
				reservationChild.setPersonGroupId(actual.getPersonGroupId());
				reservationChild.setRoomId(actual.getRoomId());
				reservationChild.setUsrId(actual.getUsrId());
				reservationChild.setPurpose(actual.getPurpose());			

				//				sets a new reservation date
				reservationChild.setReservationDate(modified.getReservationDate());
				reservationChild.setTimeFrom(modified.getTimeFrom());
				reservationChild.setTimeTo(modified.getTimeTo());

				//				sets the parent reservation
				reservationChild.setReservationParentId(actual.getReservationId());

				reservationChild.setCreated(date);
				reservationChild.setModified(date);			

				if(actual.getBookableRoom().getIsRobot()){

					boolean isOverlapping=isOverlapped(modified.getTimeFrom(), 
							modified.getTimeTo(),actual.getBookableRoom().getId());

					//					overlapping
					if(isOverlapping){		
						reservationChild.setDbStatusId("C");							
						//						parent: booked, child: waiting
					}else{
						//						without overlapping
						reservationChild.setDbStatusId("U");
						//						parent: booked, child: booked
					}

				}else{				
					reservationChild.setDbStatusId("C");				
				}			

				if(reservationChild.getDbStatusId().equals("C")){
					status=Reservation.WAITING;
				}else if(reservationChild.getDbStatusId().equals("U")){
					status=Reservation.BOOKED;
				}

				reservationDAO.update(actual, reservationChild);				
			}else{ 
				//				reservation date not changed
				//				no actions in this case				
				status=Reservation.BOOKED;
			}						
		}

		return status;		
	}	
}
