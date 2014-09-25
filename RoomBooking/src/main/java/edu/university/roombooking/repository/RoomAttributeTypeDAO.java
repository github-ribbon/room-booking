package edu.university.roombooking.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.university.roombooking.domain.RoomAttributeType;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.domain.UsrPK;


@Repository
public class RoomAttributeTypeDAO extends GenericDAOImpl<RoomAttributeType,RoomAttributeTypePK> {

	public RoomAttributeTypeDAO() {
		super(RoomAttributeType.class);		
	}

	public RoomAttributeType findManagedRoomAttributeType(RoomAttributeTypePK roomAttributeTypePK,UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT rat FROM RoomManager rm JOIN rm.bookableRoom br" +
						" JOIN br.room r JOIN r.roomAttributes ra JOIN ra.roomAttributeType rat" +
						" WHERE rat.id.roomAttributeTypeId=:roomAttributeTypeId AND rm.id.usrGroupId" +
						" IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("roomAttributeTypeId", roomAttributeTypePK.getRoomAttributeTypeId());

		query.setMaxResults(1);

		try{
			return (RoomAttributeType) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}	

	@SuppressWarnings("unchecked")
	public List<RoomAttributeType> findAllManagedRoomAttributeTypes(UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT rat FROM RoomManager rm JOIN rm.bookableRoom br" +
						" JOIN br.room r JOIN r.roomAttributes ra JOIN ra.roomAttributeType rat" +
						" WHERE rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");
		query.setParameter("usrId", usrPK.getUsrId());		

		return query.getResultList();					
	}	

	public RoomAttributeType findBookableRoomAttributeType(RoomAttributeTypePK roomAttributeTypePK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT rat FROM BookableRoom br JOIN br.room r" +
						" JOIN r.roomAttributes ra JOIN ra.roomAttributeType rat" +
						" WHERE rat.id.roomAttributeTypeId=:roomAttributeTypeId");				
		query.setParameter("roomAttributeTypeId", roomAttributeTypePK.getRoomAttributeTypeId());

		query.setMaxResults(1);

		try{
			return (RoomAttributeType) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<RoomAttributeType> findBookableRoomAttributeTypes(){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT rat FROM BookableRoom br JOIN br.room r" +
						" JOIN r.roomAttributes ra JOIN ra.roomAttributeType rat" +
						" ORDER BY rat.id.roomAttributeTypeId");	

		return query.getResultList();								
	}	
}
