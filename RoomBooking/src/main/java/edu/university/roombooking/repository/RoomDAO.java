package edu.university.roombooking.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.domain.UsrPK;


@Repository
public class RoomDAO extends GenericDAOImpl<Room,RoomPK> {

	public RoomDAO() {
		super(Room.class);	
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findRoomsByRoomAttributeType(String roomAttributeTypeId){		

		Query query = getEntityManager()
				.createQuery("SELECT r,ra.value FROM RoomAttribute ra JOIN ra.room r" +
						" WHERE ra.id.roomAttributeTypeId=:roomAttributeTypeId" +
						" ORDER BY r.id.roomId,r.id.buildingId,r.id.campusId");				
		query.setParameter("roomAttributeTypeId",roomAttributeTypeId);			

		return query.getResultList();			
	}	

	@SuppressWarnings("unchecked")
	public List<Room> findAllManagedRooms(UsrPK usrPK){		

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT r FROM RoomManager rm" +
						" JOIN rm.bookableRoom br JOIN br.room r" +
						" WHERE rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");
		query.setParameter("usrId", usrPK.getUsrId());

		return query.getResultList();			
	}

	@SuppressWarnings("unchecked")
	public List<Room> findManagedRoomsByBuilding(BuildingPK buildingPK,UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT r FROM RoomManager rm JOIN rm.bookableRoom br" +
						" JOIN br.room r WHERE rm.id.campusId=:campusId" +
						" AND rm.id.buildingId=:buildingId AND rm.id.usrGroupId" +
						" IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("campusId", buildingPK.getCampusId());
		query.setParameter("buildingId", buildingPK.getBuildingId());

		return query.getResultList();				
	}	

	@SuppressWarnings("unchecked")
	public List<Object[]> findManagedRoomsByRoomAttributeType(RoomAttributeTypePK roomAttributeTypePK,UsrPK usrPK){		

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT r,ra.value FROM RoomManager rm JOIN rm.bookableRoom br JOIN br.room r" +
						" JOIN r.roomAttributes ra WHERE ra.id.roomAttributeTypeId=:roomAttributeTypeId" +
						" AND rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)" +
						" ORDER BY r.id.campusId,r.id.buildingId,r.id.roomId");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("roomAttributeTypeId", roomAttributeTypePK.getRoomAttributeTypeId());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Room> findBookableRoomsByBuilding(BuildingPK buildingPK){

		Query query = getEntityManager()
				.createQuery("SELECT r FROM BookableRoom b JOIN b.room r" +
						" WHERE r.id.campusId=:campusId AND r.id.buildingId=:buildingId" +
						" ORDER BY r.id.roomId,r.id.buildingId,r.id.campusId");				
		query.setParameter("campusId", buildingPK.getCampusId());
		query.setParameter("buildingId", buildingPK.getBuildingId());

		return query.getResultList();					
	}		

	@SuppressWarnings("unchecked")
	public List<Room> findAllBookableRooms(){

		Query query = getEntityManager()
				.createQuery("SELECT r FROM BookableRoom b JOIN b.room r" +
						" ORDER BY r.id.roomId,r.id.buildingId,r.id.campusId");				

		return query.getResultList();			
	}	

	@SuppressWarnings("unchecked")
	public List<Object[]> findBookableRoomsByRoomAttributeType(RoomAttributeTypePK roomAttributeTypePK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT r,ra.value FROM BookableRoom br JOIN br.room r" +
						" JOIN r.roomAttributes ra WHERE ra.id.roomAttributeTypeId=:roomAttributeTypeId" +
						" ORDER BY r.id.campusId,r.id.buildingId,r.id.roomId");
		query.setParameter("roomAttributeTypeId", roomAttributeTypePK.getRoomAttributeTypeId());		

		return query.getResultList();							
	}	
}
