package edu.university.roombooking.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.university.roombooking.domain.PersonGroupPK;
import edu.university.roombooking.domain.PersonGroupPerson;
import edu.university.roombooking.domain.PersonGroupPersonPK;


@Repository
public class PersonGroupPersonDAO extends GenericDAOImpl<PersonGroupPerson,PersonGroupPersonPK> {

	public PersonGroupPersonDAO() {
		super(PersonGroupPerson.class);		
	}	

	@SuppressWarnings("unchecked")
	public List<PersonGroupPerson> findAssignedPersonsToPersonGroup(PersonGroupPK personGroupPK){		

		Query query = getEntityManager()
				.createQuery("SELECT pgp FROM PersonGroupPerson pgp " +
						" WHERE pgp.id.personGroupId=:personGroupId");
		query.setParameter("personGroupId", personGroupPK.getPersonGroupId());

		return query.getResultList();			
	}	
}
