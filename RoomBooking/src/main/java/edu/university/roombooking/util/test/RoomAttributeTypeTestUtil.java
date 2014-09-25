package edu.university.roombooking.util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.university.roombooking.domain.RoomAttributeType;
import edu.university.roombooking.domain.RoomAttributeTypePK;

public class RoomAttributeTypeTestUtil {	
	
	public static Set<RoomAttributeTypePK> createSet(RoomAttributeTypePK...roomAttributeTypePKs){
		
		return new HashSet<RoomAttributeTypePK>(				
				Arrays.asList(roomAttributeTypePKs));
	}
	
	public static List<RoomAttributeTypePK> getPrimaryKeysAsList(List<RoomAttributeType> roomAttributeTypes){
	
		Iterator<RoomAttributeType> iterator=roomAttributeTypes.iterator();
		
		List<RoomAttributeTypePK> list=new ArrayList<RoomAttributeTypePK>();
		
		while(iterator.hasNext()){
			list.add(iterator.next().getId());
		}
		
		return list;
	}
	
	public static Set<RoomAttributeTypePK> getPrimaryKeysAsSet(List<RoomAttributeType> roomAttributeTypes){
				
		Iterator<RoomAttributeType> iterator=roomAttributeTypes.iterator();
		
		Set<RoomAttributeTypePK> set=new HashSet<RoomAttributeTypePK>();
		
		while(iterator.hasNext()){
			set.add(iterator.next().getId());
		}
		
		return set;
	}
}
