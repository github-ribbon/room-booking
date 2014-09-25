package edu.university.roombooking.repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.university.roombooking.domain.Person;
import edu.university.roombooking.domain.Usr;
import edu.university.roombooking.domain.UsrPK;



@Repository
public class UsrDAO extends GenericDAOImpl<Usr,UsrPK>{

	public UsrDAO() {
		super(Usr.class);		
	}

	public Usr findByLoginAndEmail(String login,String email){	

		Query query = getEntityManager()
				.createQuery("SELECT u FROM Usr u WHERE u.id.usrId=:login AND u.email=:email");				
		query.setParameter("login",login);
		query.setParameter("email",email);	

		try{
			return (Usr) query.getSingleResult();			
		}catch(NoResultException e){
			return null;
		}						
	}	

	public void updateUser(Usr usr,Person person){			
		getEntityManager().persist(person);
		usr.setPersonId(person.getPersonId());
		getEntityManager().merge(usr);	
	}

	public Usr findByPerson(int personId){

		Query query=getEntityManager()
				.createQuery("SELECT u FROM Usr u WHERE u.personId=:personId");
		query.setParameter("personId", personId);

		try{			
			return (Usr) query.getSingleResult();				
		}catch(NoResultException e){
			return null;
		}
	}

	public Usr findByEmail(String email){

		Query query=getEntityManager()
				.createQuery("SELECT u FROM Usr u WHERE u.email=:email",Usr.class);
		query.setParameter("email", email);	

		try{			
			return (Usr) query.getSingleResult();				
		}catch(NoResultException e){
			return null;
		}
	}
}
