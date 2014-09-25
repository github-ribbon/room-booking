package edu.university.roombooking.repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.university.roombooking.domain.RoomAttribute;
import edu.university.roombooking.domain.RoomAttributePK;
import edu.university.roombooking.domain.UsrPK;



@Repository
public class RoomAttributeDAO extends GenericDAOImpl<RoomAttribute,RoomAttributePK>{

	public RoomAttributeDAO() {
		super(RoomAttribute.class);		
	}

	public RoomAttribute findManagedRoomAttribute(RoomAttributePK roomAttributePK,UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT ra FROM RoomManager rm JOIN rm.bookableRoom br" +
						" JOIN br.room r JOIN r.roomAttributes ra" +
						" WHERE ra.id.campusId=:campusId AND ra.id.buildingId=:buildingId" +
						" AND ra.id.roomId=:roomId AND ra.id.roomAttributeTypeId=:roomAttributeTypeId" +
						" AND rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");
		query.setParameter("usrId", usrPK.getUsrId());		
		query.setParameter("campusId", roomAttributePK.getCampusId());
		query.setParameter("buildingId", roomAttributePK.getBuildingId());
		query.setParameter("roomId", roomAttributePK.getRoomId());
		query.setParameter("roomAttributeTypeId", roomAttributePK.getRoomAttributeTypeId());

		query.setMaxResults(1);

		try{
			return (RoomAttribute) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}	

	public RoomAttribute findBookableRoomAttribute(RoomAttributePK roomAttributePK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT ra FROM BookableRoom br JOIN br.room r" +
						" JOIN r.roomAttributes ra" +
						" WHERE ra.id.roomAttributeTypeId=:roomAttributeTypeId" +
						" AND ra.id.campusId=:campusId" +
						" AND ra.id.buildingId=:buildingId AND ra.id.roomId=:roomId");

		query.setParameter("roomAttributeTypeId", roomAttributePK.getRoomAttributeTypeId());
		query.setParameter("campusId", roomAttributePK.getCampusId());
		query.setParameter("buildingId", roomAttributePK.getBuildingId());
		query.setParameter("roomId", roomAttributePK.getRoomId());

		query.setMaxResults(1);

		try{
			return (RoomAttribute) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}	
}
