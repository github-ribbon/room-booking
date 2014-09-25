package edu.university.roombooking.util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomPK;

public class RoomTestUtil {	
	
	public static Set<RoomPK> createSet(RoomPK...roomPKs){
		
		return new HashSet<RoomPK>(				
				Arrays.asList(roomPKs));
	}
	
	public static List<RoomPK> getPrimaryKeysAsList(List<Room> rooms){
	
		Iterator<Room> iterator=rooms.iterator();
		
		List<RoomPK> list=new ArrayList<RoomPK>();
		
		while(iterator.hasNext()){
			list.add(iterator.next().getId());
		}
		
		return list;
	}
	
	public static Set<RoomPK> getPrimaryKeysAsSet(List<Room> rooms){
				
		Iterator<Room> iterator=rooms.iterator();
		
		Set<RoomPK> set=new HashSet<RoomPK>();
		
		while(iterator.hasNext()){
			set.add(iterator.next().getId());
		}
		
		return set;
	}
}
