package edu.university.roombooking.util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.university.roombooking.domain.RoomManager;
import edu.university.roombooking.domain.RoomManagerPK;

public class RoomManagerTestUtil {	

	public static Set<RoomManagerPK> createSet(RoomManagerPK...roomManagerPKs){

		return new HashSet<RoomManagerPK>(				
				Arrays.asList(roomManagerPKs));
	}

	public static List<RoomManagerPK> getPrimaryKeysAsList(List<RoomManager> roomManagers){

		Iterator<RoomManager> iterator=roomManagers.iterator();

		List<RoomManagerPK> list=new ArrayList<RoomManagerPK>();

		while(iterator.hasNext()){
			list.add(iterator.next().getId());
		}

		return list;
	}

	public static Set<RoomManagerPK> getPrimaryKeysAsSet(List<RoomManager> roomManagers){

		Iterator<RoomManager> iterator=roomManagers.iterator();

		Set<RoomManagerPK> set=new HashSet<RoomManagerPK>();

		while(iterator.hasNext()){
			set.add(iterator.next().getId());
		}

		return set;
	}
}
