package edu.university.roombooking.service.abstraction;

import java.util.Date;
import java.util.List;

import edu.university.roombooking.domain.BookableRoom;
import edu.university.roombooking.domain.BookableRoomPK;
import edu.university.roombooking.domain.Building;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.Campus;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.domain.Reservation;
import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomAttributePK;
import edu.university.roombooking.domain.RoomAttributeType;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.domain.UsrPK;


public interface RoomBookingInterface
//RoomBooking
{

	public abstract BookableRoom getBookableRoom(BookableRoomPK bookableRoomPK);

	public abstract boolean isBookableRoom(BookableRoomPK bookableRoomPK);

	public abstract List<Campus> getAllBookableCampuses();

	public abstract List<Building> getBookableBuildings(CampusPK campusPK);

	public abstract List<Room> getBookableRooms(BuildingPK buildingPK);

	public abstract List<Building> getAllBookableBuildings();

	public abstract List<Room> getAllBookableRooms();

	public abstract List<RoomAttributeType> getAllBookableRoomAttributeTypes();

	public abstract List<Object[]> getBookableRooms(RoomAttributeTypePK roomAttributeTypePK);

	public abstract boolean isCampusBookable(CampusPK campusPK);

	public abstract boolean isBuildingBookable(BuildingPK buildingPK);

	public abstract boolean isRoomBookable(RoomPK roomPK);

	public abstract boolean isRoomAttributeTypeBookable(RoomAttributeTypePK roomAttributeTypePK);

	public abstract boolean isRoomAttributeBookable(RoomAttributePK roomAttributePK);

	public abstract List<Reservation> getAllClientReservations(UsrPK usrPK);

	public abstract List<Reservation> getClientBookedReservations(UsrPK usrPK);

	public abstract List<Reservation> getClientWaitingReservations(UsrPK usrPK);

	public abstract List<Reservation> getClientCancelledReservations(UsrPK usrPK);

	public abstract List<BookableRoom> getBusyRooms(Date date);

	public abstract List<BookableRoom> getAvailableRooms(Date date);

	public abstract int makeReservation(Reservation reservation,String usrId);

	public abstract int changeReservation(Reservation modified);
}
