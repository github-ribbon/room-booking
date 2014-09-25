package edu.university.roombooking.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericDAOImpl<T, PK extends Serializable> implements GenericDAO<T, PK>{

	private Class<T> entityClass;  

	@PersistenceContext
	private EntityManager entityManager;

	public GenericDAOImpl(){}

	public GenericDAOImpl(Class<T> entityClass){       
		setEntityClass(entityClass);	
	}   

	public Class<T> getEntityClass(){
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass){
		this.entityClass = entityClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public T insert(T t){		
		getEntityManager().persist(t);

		return t;
	}

	public T read(PK pk){  	

		T t=getEntityManager().find(getEntityClass(), pk);    	

		return t;       
	}

	public void update(T t){   	
		getEntityManager().merge(t);    	
	}



	public void delete(T t){ 	   	
		t = getEntityManager().merge(t);
		getEntityManager().remove(t);      
	}     

	public List<T> getAllRows(){

		String tableName=getEntityClass().getSimpleName();
		String orderBy = null;

		if(tableName.equals("Campus")) orderBy="o.id.campusId";
		else if(tableName.equals("Building")) orderBy="o.id.campusId, o.id.buildingId";
		else if(tableName.equals("Room")) orderBy="o.id.campusId, o.id.buildingId, o.id.roomId";
		else if(tableName.equals("RoomAttribute")) orderBy="o.id.roomAttributeTypeId,o.value";
		else if(tableName.equals("RoomAttributeType")) orderBy="o.id.roomAttributeTypeId";
		else if(tableName.equals("Person")) orderBy="o.givenName,o.familyName";
		else if(tableName.equals("Usr")) orderBy="o.id.usrId";
		else if(tableName.equals("PersonGroup")) orderBy="o.id.personGroupId";
		else if(tableName.equals("UsrGroup")) orderBy="o.id.usrGroupId";
		else if(tableName.equals("PersonGroupPerson")) orderBy="o.id.personGroupId,o.person.givenName,o.person.familyName";
		else if(tableName.equals("UsrGroupUsr")) orderBy="o.id.usrGroupId,o.id.usrId";
		else if(tableName.equals("Reservation")) orderBy="o.reservationDate";


		Query query=getEntityManager()
				.createQuery("SELECT o FROM "+tableName+" o ORDER BY "+orderBy);

		@SuppressWarnings("unchecked")
		List<T> resultList = query.getResultList();

		return resultList;		
	}	
}

