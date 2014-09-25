package edu.university.roombooking.service.abstraction;

import java.util.List;

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




public interface ObjectManagementInterface
//ObjectManagement
{

	public abstract RoomAttribute getRoomAttribute(RoomAttributePK roomAttributePK);

	public abstract RoomAttributeType getRoomAttributeType(RoomAttributeTypePK ratPK);

	public abstract Campus getCampus(CampusPK campusPK);

	public abstract Building getBuilding(BuildingPK buildingPK);

	public abstract Room getRoom(RoomPK roomPK);	

	public abstract boolean isCampus(CampusPK campusPK);

	public abstract boolean isBuilding(BuildingPK buildingPK);

	public abstract boolean isRoom(RoomPK roomPK);

	public abstract boolean isRoomAttributeType(RoomAttributeTypePK ratPK);

	public abstract boolean isRoomAttribute(RoomAttributePK roomAttributePK);

	public abstract List<Building> getBuildingsByCampus(Campus campus);

	public abstract List<Room> getRooms(Building building);

	public abstract List<Object[]> getRooms(RoomAttributeTypePK ratPK);

	public abstract List<RoomAttribute> getRoomAttributes(Room room);

	public abstract List<Campus> getAllCampuses();

	public abstract List<Building> getAllBuildings();

	public abstract List<Room> getAllRooms();

	public abstract List<RoomAttributeType> getAllRoomAttributeTypes();

	public abstract void addCampus(Campus campus);

	public abstract void addBuilding(Building building);

	public abstract void addRoom(Room room);

	public abstract void assignRoomAttributeToRoom(RoomAttribute roomAttribute);

	public abstract void addRoomAttributeType(RoomAttributeType rat);

	public abstract void modifyCampus(Campus campus);

	public abstract void modifyBuilding(Building building);

	public abstract void modifyRoom(Room room);

	public abstract void modifyAssignedRoomAttribute(RoomAttribute roomAttribute);

	public abstract void modifyRoomAttributeType(RoomAttributeType rat);

	public abstract void deleteCampus(CampusPK campusPK);

	public abstract void deleteBuilding(BuildingPK buildingPK);

	public abstract void deleteRoom(RoomPK roomPK);

	public abstract void deleteAssignedRoomAttribute(RoomAttributePK roomAttributePK);

	public abstract void deleteRoomAttributeType(RoomAttributeTypePK ratPK);

	public abstract BookableRoom getBookableRoom(BookableRoomPK bookableRoomPK);

	public abstract RoomManager getRoomManager(RoomManagerPK roomManagerPK);

	public abstract boolean isBookableRoom(BookableRoomPK bookableRoomPK);

	public abstract boolean isRoomManager(RoomManagerPK roomManagerPK);		

	public abstract boolean isUsrGroup(UsrGroupPK usrGroupPK);

	public abstract List<RoomManager> getRoomManagers(
			BookableRoomPK bookableRoomPK);

	public abstract List<RoomManager> getRoomManagers(
			UsrGroupPK usrGroupPK);

	public abstract void createBookableRoom(BookableRoom bookableRoom);

	public abstract void createRoomManager(RoomManager roomManager);

	public abstract void updateBookableRoom(BookableRoom bookableRoom);

	public abstract void deleteBookableRoom(BookableRoomPK bookableRoomPK);

	public abstract void deleteRoomManager(RoomManagerPK roomManagerPK);

}
