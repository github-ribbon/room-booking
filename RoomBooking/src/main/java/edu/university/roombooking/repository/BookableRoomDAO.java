package edu.university.roombooking.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.university.roombooking.domain.BookableRoom;
import edu.university.roombooking.domain.BookableRoomPK;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.domain.RoomPK;


@Repository
public class BookableRoomDAO extends GenericDAOImpl<BookableRoom,BookableRoomPK>{

	public BookableRoomDAO() {
		super(BookableRoom.class);	
	}

	public BookableRoom findBookableCampus(CampusPK campusPK){		

		Query query = getEntityManager()
				.createQuery("SELECT br FROM BookableRoom br WHERE br.id.campusId=:campusId");			
		query.setParameter("campusId", campusPK.getCampusId());

		query.setMaxResults(1);

		try{			
			return (BookableRoom) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}		
	}	

	public BookableRoom findBookableBuilding(BuildingPK buildingPK){			

		Query query = getEntityManager()
				.createQuery("SELECT br FROM BookableRoom br WHERE br.id.campusId=:campusId" +
						" AND br.id.buildingId=:buildingId");			
		query.setParameter("campusId", buildingPK.getCampusId());
		query.setParameter("buildingId", buildingPK.getBuildingId());
		query.setMaxResults(1);

		try{			
			return (BookableRoom) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}	

	public BookableRoom findBookableRoom(RoomPK roomPK){

		Query query = getEntityManager()
				.createQuery("SELECT br FROM BookableRoom br WHERE br.id.campusId=:campusId" +
						" AND br.id.buildingId=:buildingId AND br.id.roomId=:roomId");			
		query.setParameter("campusId", roomPK.getCampusId());
		query.setParameter("buildingId", roomPK.getBuildingId());
		query.setParameter("roomId", roomPK.getRoomId());

		query.setMaxResults(1);

		try{			
			return (BookableRoom) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public  List<BookableRoom> findBusyRooms(Date reservationDate){

		Calendar calendar=Calendar.getInstance();			
		calendar.setTime(reservationDate);

		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		int day=calendar.get(Calendar.DAY_OF_MONTH);

		Calendar currentCalendar=Calendar.getInstance();
		currentCalendar.clear();
		currentCalendar.set(year, month, day);
		Date currentDate=currentCalendar.getTime();		
		reservationDate=currentDate;

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT br FROM Reservation res" +
						" JOIN res.bookableRoom br WHERE res.reservationDate=:date ORDER BY res.timeFrom");
		query.setParameter("date",reservationDate);

		return query.getResultList();		
	}


	@SuppressWarnings("unchecked")
	public  List<BookableRoom> findAvailableRooms(Date reservationDate){

		/*Query query = entityManager.
				createNativeQuery("SELECT DISTINCT bookable_room.campus_id,bookable_room.building_id,bookable_room.room_id FROM bookable_room LEFT JOIN reservation " +
						"ON bookable_room.campus_id=reservation.campus_id AND bookable_room.building_id=reservation.building_id " +
						"AND bookable_room.room_id=reservation.room_id AND reservation.reservation_date=? WHERE reservation.reservation_date IS NULL " +
						"ORDER BY bookable_room.room_id,bookable_room.building_id,bookable_room.room_id",BookableRoom.class);		

		 */

		Calendar calendar=Calendar.getInstance();			
		calendar.setTime(reservationDate);

		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		int day=calendar.get(Calendar.DAY_OF_MONTH);

		Calendar currentCalendar=Calendar.getInstance();
		currentCalendar.clear();
		currentCalendar.set(year, month, day);
		Date currentDate=currentCalendar.getTime();		
		reservationDate=currentDate;

		Query query=getEntityManager()
				.createQuery("SELECT DISTINCT br FROM BookableRoom br" +
						" LEFT JOIN br.reservations res ON res.reservationDate=:date" +
						" WHERE res.reservationDate IS NULL");
		query.setParameter("date", reservationDate);			

		return query.getResultList();							
	}	
}
