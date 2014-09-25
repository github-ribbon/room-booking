package edu.university.roombooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.university.roombooking.domain.BookableRoom;
import edu.university.roombooking.domain.BookableRoomPK;
import edu.university.roombooking.domain.Building;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.Campus;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomAttribute;
import edu.university.roombooking.domain.RoomAttributePK;
import edu.university.roombooking.domain.RoomAttributeType;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.domain.RoomManager;
import edu.university.roombooking.domain.RoomManagerPK;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.domain.UsrGroupPK;
import edu.university.roombooking.repository.BookableRoomDAO;
import edu.university.roombooking.repository.BuildingDAO;
import edu.university.roombooking.repository.CampusDAO;
import edu.university.roombooking.repository.RoomAttributeDAO;
import edu.university.roombooking.repository.RoomAttributeTypeDAO;
import edu.university.roombooking.repository.RoomDAO;
import edu.university.roombooking.repository.RoomManagerDAO;
import edu.university.roombooking.repository.UsrGroupDAO;
//import edu.university.roombooking.service.abstraction.ObjectManagement;



@Service
@Transactional(readOnly=true)
public class ObjectManagement
{

	@Autowired
	private UsrGroupDAO usrGroupDAO;

	@Autowired
	private CampusDAO campusDAO;	

	@Autowired
	private BuildingDAO buildingDAO;	

	@Autowired
	private RoomDAO roomDAO;

	@Autowired
	private RoomAttributeDAO roomAttributeDAO;

	@Autowired
	private RoomAttributeTypeDAO roomAttributeTypeDAO;

	@Autowired
	private RoomManagerDAO roomManagerDAO;

	@Autowired
	private BookableRoomDAO bookableRoomDAO;



	//@Override
	public Campus getCampus(CampusPK campusPK) {			
		return campusDAO.read(campusPK);
	}	

	//@Override
	public Building getBuilding(BuildingPK buildingPK) {			
		return buildingDAO.read(buildingPK);
	}		

	//@Override
	public Room getRoom(RoomPK roomPK) {			
		return roomDAO.read(roomPK);
	}

	//@Override
	public boolean isCampus(CampusPK campusPK) {				
		if(campusDAO.read(campusPK)==null){
			return false;
		}else return true;	
	}

	//@Override
	public boolean isBuilding(BuildingPK buildingPK){				
		if(buildingDAO.read(buildingPK)==null){
			return false;
		}else return true;	
	}	

	//@Override
	public boolean isRoom(RoomPK roomPK){		
		if(roomDAO.read(roomPK)==null){
			return false;
		}else return true;	
	}

	//@Override
	public boolean isRoomAttributeType(RoomAttributeTypePK ratPK) {		
		if(roomAttributeTypeDAO.read(ratPK)==null){
			return false;
		}else return true;		
	}	

	//@Override
	public boolean isRoomAttribute(RoomAttributePK roomAttributePK){			
		if(roomAttributeDAO.read(roomAttributePK)==null){
			return false;
		}else return true;	
	}	

	//@Override
	public RoomAttribute getRoomAttribute(RoomAttributePK roomAttributePK) {			
		return roomAttributeDAO.read(roomAttributePK);
	}	

	//@Override
	public RoomAttributeType getRoomAttributeType(RoomAttributeTypePK ratPK) {			
		return roomAttributeTypeDAO.read(ratPK);
	}	

	//@Override
	public BookableRoom getBookableRoom(BookableRoomPK bookableRoomPK) {			
		return bookableRoomDAO.read(bookableRoomPK);
	}

	//@Override
	public RoomManager getRoomManager(RoomManagerPK roomManagerPK) {			
		return roomManagerDAO.read(roomManagerPK);
	}

	//@Override
	public boolean isBookableRoom(BookableRoomPK bookableRoomPK){		
		if(bookableRoomDAO.read(bookableRoomPK)!=null){
			return true;
		}else return false;
	}

	//@Override
	public boolean isRoomManager(RoomManagerPK roomManagerPK){		
		if(roomManagerDAO.read(roomManagerPK)!=null){
			return true;
		}else
			return false;
	}		

	//@Override
	public boolean isUsrGroup(UsrGroupPK usrGroupPK){				
		if(usrGroupDAO.read(usrGroupPK)==null){
			return false;
		}else return true;	
	}

	//@Override
	public List<RoomManager> getRoomManagers(BookableRoomPK bookableRoomPK){		
		return bookableRoomDAO.read(bookableRoomPK).getRoomManagers();					
	}

	//@Override
	public List<RoomManager> getRoomManagers(UsrGroupPK usrGroupPK){		
		return usrGroupDAO.read(usrGroupPK).getRoomManagers();					
	}	   

	//@Override
	public List<Building> getBuildingsByCampus(Campus campus){			
		return campus.getBuildings();		 		
	}

	//@Override
	public List<Room> getRooms(Building building){		
		return building.getRooms();						
	}   

	//@Override
	public List<Object[]> getRooms(RoomAttributeTypePK ratPK){		
		return roomDAO.findRoomsByRoomAttributeType(ratPK.getRoomAttributeTypeId());		
	}

	//@Override
	public List<RoomAttribute> getRoomAttributes(Room room){		
		return room.getRoomAttributes();			
	}	

	//@Override
	public List<Campus> getAllCampuses(){		
		return campusDAO.getAllRows();		
	}

	//@Override
	public List<Building> getAllBuildings(){		
		return buildingDAO.getAllRows();	
	}	

	//@Override
	public List<Room> getAllRooms(){		
		return roomDAO.getAllRows();		
	}	

	//@Override
	public List<RoomAttributeType> getAllRoomAttributeTypes(){		
		return roomAttributeTypeDAO.getAllRows();		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void addCampus(Campus campus){				
		campusDAO.insert(campus);		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void addBuilding(Building building){				
		buildingDAO.insert(building);		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void addRoom(Room room){				
		roomDAO.insert(room);		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void assignRoomAttributeToRoom(RoomAttribute roomAttribute){				
		roomAttributeDAO.insert(roomAttribute);		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void addRoomAttributeType(RoomAttributeType rat){				
		roomAttributeTypeDAO.insert(rat);		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void createBookableRoom(BookableRoom bookableRoom){				
		bookableRoomDAO.insert(bookableRoom);		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void createRoomManager(RoomManager roomManager){				
		roomManagerDAO.insert(roomManager);		
	}

	//@Override
	@Transactional(readOnly=false)
	public void modifyCampus(Campus campus){				
		campusDAO.update(campus);		
	}

	//@Override
	@Transactional(readOnly=false)
	public void modifyBuilding(Building building){				
		buildingDAO.update(building);		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void modifyRoom(Room room){				
		roomDAO.update(room);		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void updateBookableRoom(BookableRoom bookableRoom){				
		bookableRoomDAO.update(bookableRoom);		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void modifyAssignedRoomAttribute(RoomAttribute roomAttribute){				
		roomAttributeDAO.update(roomAttribute);		
	}

	//@Override
	@Transactional(readOnly=false)
	public void modifyRoomAttributeType(RoomAttributeType rat){				
		roomAttributeTypeDAO.update(rat);		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void deleteCampus(CampusPK campusPK){		
		campusDAO.delete(campusDAO.read(campusPK));		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void deleteBuilding(BuildingPK buildingPK){		
		buildingDAO.delete(buildingDAO.read(buildingPK));		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void deleteRoom(RoomPK roomPK){				
		roomDAO.delete(roomDAO.read(roomPK));		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void deleteAssignedRoomAttribute(RoomAttributePK roomAttributePK){			
		roomAttributeDAO.delete(roomAttributeDAO.read(roomAttributePK));		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void deleteRoomAttributeType(RoomAttributeTypePK ratPK){			
		roomAttributeTypeDAO.delete(roomAttributeTypeDAO.read(ratPK));		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void deleteBookableRoom(BookableRoomPK bookableRoomPK){			
		bookableRoomDAO.delete(bookableRoomDAO.read(bookableRoomPK));		
	}	

	//@Override
	@Transactional(readOnly=false)
	public void deleteRoomManager(RoomManagerPK roomManagerPK){		
		roomManagerDAO.delete(roomManagerDAO.read(roomManagerPK));		
	}	
}
