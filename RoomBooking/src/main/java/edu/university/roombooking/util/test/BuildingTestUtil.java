package edu.university.roombooking.util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.university.roombooking.domain.Building;
import edu.university.roombooking.domain.BuildingPK;

public class BuildingTestUtil {	
	
	public static Set<BuildingPK> createSet(BuildingPK...buildingPKs){
		
		return new HashSet<BuildingPK>(				
				Arrays.asList(buildingPKs));
	}
	
	public static List<BuildingPK> getPrimaryKeysAsList(List<Building> buildings){
	
		Iterator<Building> iterator=buildings.iterator();
		
		List<BuildingPK> list=new ArrayList<BuildingPK>();
		
		while(iterator.hasNext()){
			list.add(iterator.next().getId());
		}
		
		return list;
	}
	
	public static Set<BuildingPK> getPrimaryKeysAsSet(List<Building> buildings){
				
		Iterator<Building> iterator=buildings.iterator();
		
		Set<BuildingPK> set=new HashSet<BuildingPK>();
		
		while(iterator.hasNext()){
			set.add(iterator.next().getId());
		}
		
		return set;
	}
}
