package edu.university.roombooking.util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.university.roombooking.domain.RoomAttribute;
import edu.university.roombooking.domain.RoomAttributePK;

public class RoomAttributeTestUtil {	

	public static Set<RoomAttributePK> createSet(RoomAttributePK...roomAttributePKs){

		return new HashSet<RoomAttributePK>(				
				Arrays.asList(roomAttributePKs));
	}

	public static List<RoomAttributePK> getPrimaryKeysAsList(List<RoomAttribute> roomAttributes){

		Iterator<RoomAttribute> iterator=roomAttributes.iterator();

		List<RoomAttributePK> list=new ArrayList<RoomAttributePK>();

		while(iterator.hasNext()){
			list.add(iterator.next().getId());
		}

		return list;
	}

	public static Set<RoomAttributePK> getPrimaryKeysAsSet(List<RoomAttribute> roomAttributes){

		Iterator<RoomAttribute> iterator=roomAttributes.iterator();

		Set<RoomAttributePK> set=new HashSet<RoomAttributePK>();

		while(iterator.hasNext()){
			set.add(iterator.next().getId());
		}

		return set;
	}
}
