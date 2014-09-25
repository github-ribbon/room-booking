package edu.university.roombooking.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.university.roombooking.domain.Building;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.domain.UsrPK;


@Repository
public class BuildingDAO extends GenericDAOImpl<Building,BuildingPK> {

	public BuildingDAO() {
		super(Building.class);		
	}

	@SuppressWarnings("unchecked")
	public List<Building> findAllManagedBuildings(UsrPK usrPK){		

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT b FROM RoomManager rm" +
						" JOIN rm.bookableRoom br JOIN br.room r JOIN r.building b" +
						" WHERE rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");
		query.setParameter("usrId", usrPK.getUsrId());

		return query.getResultList();			
	}	

	@SuppressWarnings("unchecked")
	public List<Building> findManagedBuildings(CampusPK campusPK,UsrPK usrPK){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT b FROM RoomManager rm" +
						" JOIN rm.bookableRoom br JOIN br.room r" +
						" JOIN r.building b WHERE rm.id.campusId=:campusId" +
						" AND rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");
		query.setParameter("usrId", usrPK.getUsrId());
		query.setParameter("campusId", campusPK.getCampusId());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Building> findAllBookableBuildings(){		

		Query query=getEntityManager()
				.createQuery("SELECT DISTINCT b FROM BookableRoom br JOIN br.room r" +
						" JOIN r.building b ORDER BY b.id.campusId,b.id.buildingId");

		return query.getResultList();			
	}


	@SuppressWarnings("unchecked")
	public List<Building> findBookableBuildings(CampusPK campusPK){

		Query query=getEntityManager()
				.createQuery("SELECT DISTINCT b FROM BookableRoom br" +
						" JOIN br.room r JOIN r.building b WHERE b.id.campusId=:campusId" +
						" ORDER BY b.id.campusId,b.id.buildingId");
		query.setParameter("campusId", campusPK.getCampusId());

		return query.getResultList();			
	}	

	@SuppressWarnings("unchecked")
	public List<Building> findBuildingsByCampus(CampusPK campusPK){		

		Query query = getEntityManager()
				.createQuery("SELECT b FROM Building b" +
						" WHERE b.id.campusId=:campusId)");
		query.setParameter("campusId", campusPK.getCampusId());

		return query.getResultList();			
	}	
}
