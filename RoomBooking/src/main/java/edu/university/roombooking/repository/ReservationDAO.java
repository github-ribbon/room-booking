package edu.university.roombooking.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.university.roombooking.domain.BookableRoomPK;
import edu.university.roombooking.domain.Reservation;
import edu.university.roombooking.domain.ReservationPK;
import edu.university.roombooking.domain.UsrPK;


@Repository
public class ReservationDAO extends GenericDAOImpl<Reservation,ReservationPK> {

	public ReservationDAO() {
		super(Reservation.class);	
	}

	public void update(Reservation parent,Reservation child){		
		getEntityManager().merge(parent);
		getEntityManager().merge(child);	
	}	

	@SuppressWarnings("unchecked")
	public List<Reservation> findClientBookedReservations(UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT r FROM Reservation r" +
						" WHERE r.usrId=:usrId AND r.dbStatusId='U'" +
						" ORDER BY r.reservationDate DESC,r.timeFrom DESC,r.timeTo DESC");				
		query.setParameter("usrId",usrPK.getUsrId());		

		return query.getResultList();			
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> findClientWaitingReservations(UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT r FROM Reservation r" +
						" WHERE r.usrId=:usrId AND r.dbStatusId='C'" +
						" ORDER BY r.reservationDate DESC,r.timeFrom DESC,r.timeTo DESC");				
		query.setParameter("usrId",usrPK.getUsrId());			

		return query.getResultList();				
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> findClientCancelledReservations(UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT r FROM Reservation r" +
						" WHERE r.usrId=:usrId AND r.dbStatusId='D'" +
						" ORDER BY r.reservationDate DESC,r.timeFrom DESC,r.timeTo DESC");				
		query.setParameter("usrId",usrPK.getUsrId());

		return query.getResultList();				
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> findAllClientReservations(UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT r FROM Reservation r" +
						" WHERE r.usrId=:usrId" +
						" ORDER BY r.reservationDate DESC,r.timeFrom DESC,r.timeTo DESC");				
		query.setParameter("usrId",usrPK.getUsrId());		

		return query.getResultList();					
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> findReservationsByDayAndRoom(
			BookableRoomPK roomBookingPK,Date reservationDate){

		Query query = getEntityManager()
				.createQuery("SELECT r FROM Reservation r" +
						" WHERE r.reservationDate=:reservationDate" +
						" AND r.campusId=:campusId AND r.buildingId=:buildingId" +
						" AND r.roomId=:roomId ORDER BY r.timeFrom DESC,r.timeTo DESC");				
		query.setParameter("campusId",roomBookingPK.getCampusId());		
		query.setParameter("buildingId",roomBookingPK.getBuildingId());
		query.setParameter("roomId",roomBookingPK.getRoomId());
		query.setParameter("reservationDate",reservationDate);

		return query.getResultList();			
	}	

	@SuppressWarnings("unchecked")
	public List<Reservation> findPreviousHandledWaitingReservations(UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT res FROM Reservation res" +
						" JOIN res.bookableRoom br JOIN br.roomManagers rm" +
						" WHERE res.dbStatusId='C' AND res.timeFrom<:now" +
						" AND rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)  ORDER BY res.reservationDate DESC," +
						"res.timeFrom DESC,res.timeTo DESC");

		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("now", new Date());		

		return query.getResultList();				
	}	

	@SuppressWarnings("unchecked")
	public List<Reservation> findUpcomingHandledWaitingReservations(UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT res FROM Reservation res" +
						" JOIN res.bookableRoom br JOIN br.roomManagers rm" +
						" WHERE res.dbStatusId='C' AND res.timeFrom>:now" +
						" AND rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId) ORDER BY res.reservationDate DESC," +
						"res.timeFrom DESC,res.timeTo DESC");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("now", new Date());		

		return query.getResultList();				
	}


	@SuppressWarnings("unchecked")
	public List<Reservation> findPreviousHandledBookedReservations(UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT res FROM Reservation res" +
						" JOIN res.bookableRoom br JOIN br.roomManagers rm" +
						" WHERE res.dbStatusId='U' AND res.timeFrom<:now" +
						" AND rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)" +
						" ORDER BY res.reservationDate DESC,res.timeFrom DESC,res.timeTo DESC");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("now", new Date());		

		return query.getResultList();			
	}	

	@SuppressWarnings("unchecked")
	public List<Reservation> findUpcomingHandledBookedReservations(UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT res FROM Reservation res" +
						" JOIN res.bookableRoom br JOIN br.roomManagers rm " +
						"WHERE res.dbStatusId='U' AND res.timeFrom>:now" +
						" AND rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)" +
						" ORDER BY res.reservationDate DESC,res.timeFrom DESC,res.timeTo DESC");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("now", new Date());		

		return query.getResultList();			
	}	

	@SuppressWarnings("unchecked")
	public List<Reservation> findPreviousHandledCancelledReservations(UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT res FROM Reservation res" +
						" JOIN res.bookableRoom br JOIN br.roomManagers rm" +
						" WHERE res.dbStatusId='D' AND res.timeFrom<:now" +
						" AND rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)" +
						" ORDER BY res.reservationDate DESC,res.timeFrom DESC,res.timeTo DESC");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("now", new Date());		

		return query.getResultList();				
	}	

	@SuppressWarnings("unchecked")
	public List<Reservation> findUpcomingHandledCancelledReservations(UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT res FROM Reservation res" +
						" JOIN res.bookableRoom br JOIN br.roomManagers rm" +
						" WHERE res.dbStatusId='D' AND res.timeFrom>:now" +
						" AND rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)" +
						" ORDER BY res.reservationDate DESC,res.timeFrom DESC,res.timeTo DESC");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("now", new Date());	

		return query.getResultList();				
	}	

	public Reservation findOverlappedReservation(Date start,Date end,BookableRoomPK bookableRoomPK){

		Query query = getEntityManager().createQuery("SELECT r FROM Reservation r" +
				" WHERE r.dbStatusId='U' AND r.campusId=:campusId AND r.buildingId=:buildingId AND " +
				"r.roomId=:roomId AND (((:timeFrom<=r.timeFrom) AND (:timeTo>=r.timeTo)) OR " +
				"((:timeFrom>=r.timeFrom) AND (:timeFrom<=r.timeTo)) OR " +
				"((:timeTo<=r.timeTo) AND (:timeTo>=r.timeFrom)))");				
		query.setParameter("campusId",bookableRoomPK.getCampusId());
		query.setParameter("buildingId", bookableRoomPK.getBuildingId());
		query.setParameter("roomId", bookableRoomPK.getRoomId());
		query.setParameter("timeFrom", start);
		query.setParameter("timeTo", end);

		query.setMaxResults(1);

		try{
			return (Reservation) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}			
	}
}
