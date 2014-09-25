package edu.university.roombooking.repository;

import java.io.Serializable;

public interface GenericDAO<T, PK extends Serializable> {
   	
	public abstract T insert(T t);	
	public abstract T read(PK pk);   
	public abstract void update(T t);    
	public abstract void delete(T t);
	
}