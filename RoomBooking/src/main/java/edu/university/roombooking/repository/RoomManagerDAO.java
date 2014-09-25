package edu.university.roombooking.repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.domain.RoomManager;
import edu.university.roombooking.domain.RoomManagerPK;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.domain.UsrPK;


@Repository
public class RoomManagerDAO extends GenericDAOImpl<RoomManager,RoomManagerPK> {

	public RoomManagerDAO() {
		super(RoomManager.class);		
	}		

	public RoomManager findManagedCampus(CampusPK campusPK,UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT rm FROM RoomManager rm "+
						" WHERE rm.id.campusId=:campusId AND rm.id.usrGroupId" +
						" IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("campusId", campusPK.getCampusId());

		query.setMaxResults(1);

		try{			
			return (RoomManager) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}	
	}	

	public RoomManager findManagedBuilding(BuildingPK buildingPK,UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT rm FROM RoomManager rm " +						
						" WHERE rm.id.campusId=:campusId AND rm.id.buildingId=:buildingId" +
						" AND rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("campusId", buildingPK.getCampusId());
		query.setParameter("buildingId", buildingPK.getBuildingId());

		query.setMaxResults(1);

		try{			
			return (RoomManager) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}		

	public RoomManager findManagedRoom(RoomPK roomPK,UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT rm FROM RoomManager rm " +
						" WHERE rm.id.campusId=:campusId AND rm.id.buildingId=:buildingId" +
						" AND rm.id.roomId=:roomId AND rm.id.usrGroupId" +
						" IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("campusId", roomPK.getCampusId());
		query.setParameter("buildingId", roomPK.getBuildingId());
		query.setParameter("roomId", roomPK.getRoomId());

		query.setMaxResults(1);

		try{		
			return (RoomManager) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	public RoomManager findRoomManager(RoomPK roomPK,UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT rm FROM RoomManager rm "+
						" WHERE rm.id.campusId=:campusId AND " +
						" rm.id.buildingId=:buildingId AND rm.id.roomId=:roomId " +
						" AND rm.id.usrGroupId" +
						" IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");

		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("campusId", roomPK.getCampusId());
		query.setParameter("buildingId",roomPK.getBuildingId());
		query.setParameter("roomId",roomPK.getRoomId());

		query.setMaxResults(1);

		try{			
			return (RoomManager) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}	
	}	
}
