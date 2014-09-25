package edu.university.roombooking.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.university.roombooking.domain.Campus;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.domain.UsrPK;


@Repository
public class CampusDAO extends GenericDAOImpl<Campus,CampusPK> {

	public CampusDAO() {
		super(Campus.class);		
	}

	@SuppressWarnings("unchecked")
	public List<Campus> findManagedCampuses(UsrPK usrPK){		

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT c FROM RoomManager rm JOIN rm.bookableRoom br" +
						" JOIN br.room r JOIN r.building b JOIN b.campus c" +
						" WHERE rm.id.usrGroupId IN(SELECT ugu.id.usrGroupId FROM UsrGroupUsr ugu" +
						" WHERE ugu.id.usrId=:usrId)");
		query.setParameter("usrId", usrPK.getUsrId());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Campus> findBookableCampuses(){

		Query query = getEntityManager()
				.createQuery("SELECT DISTINCT c FROM BookableRoom br JOIN br.room r" +
						" JOIN r.building b JOIN b.campus c ORDER BY c.id.campusId");

		return query.getResultList();						
	}
}
