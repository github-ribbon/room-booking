package edu.university.roombooking.util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.university.roombooking.domain.Campus;
import edu.university.roombooking.domain.CampusPK;

public class CampusTestUtil {	
	
	public static Set<CampusPK> createSet(CampusPK...campusPKs){
		
		return new HashSet<CampusPK>(				
				Arrays.asList(campusPKs));
	}
	
	public static List<CampusPK> getPrimaryKeysAsList(List<Campus> campuses){
	
		Iterator<Campus> iterator=campuses.iterator();
		
		List<CampusPK> list=new ArrayList<CampusPK>();
		
		while(iterator.hasNext()){
			list.add(iterator.next().getId());
		}
		
		return list;
	}
	
	public static Set<CampusPK> getPrimaryKeysAsSet(List<Campus> campuses){
				
		Iterator<Campus> iterator=campuses.iterator();
		
		Set<CampusPK> set=new HashSet<CampusPK>();
		
		while(iterator.hasNext()){
			set.add(iterator.next().getId());
		}
		
		return set;
	}
}
