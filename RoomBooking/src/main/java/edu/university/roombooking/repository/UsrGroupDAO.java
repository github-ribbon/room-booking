package edu.university.roombooking.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.university.roombooking.domain.UsrGroup;
import edu.university.roombooking.domain.UsrGroupPK;
import edu.university.roombooking.domain.UsrPK;


@Repository
public class UsrGroupDAO extends GenericDAOImpl<UsrGroup,UsrGroupPK> {

	public UsrGroupDAO() {
		super(UsrGroup.class);	
	}

	@SuppressWarnings("unchecked")
	public List<UsrGroup> findByUser(UsrPK usrPK){

		Query query=getEntityManager()
				.createQuery("SELECT ug FROM UsrGroupUsr ugu JOIN ugu.usrGroup ug " +
						"WHERE ugu.id.usrId=:usrId ORDER BY ug.id.usrGroupId");
		query.setParameter("usrId",usrPK.getUsrId());

		return query.getResultList();			
	}

}