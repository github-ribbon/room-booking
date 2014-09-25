package edu.university.roombooking.service.abstraction;

import java.util.List;

import edu.university.roombooking.domain.Building;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.Campus;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.domain.Reservation;
import edu.university.roombooking.domain.ReservationPK;
import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomAttributePK;
import edu.university.roombooking.domain.RoomAttributeType;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.domain.RoomManager;
import edu.university.roombooking.domain.RoomManagerPK;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.domain.UsrPK;



public interface BookingConfirmationInterface
//BookingConfirmation
{

	public abstract RoomManager getRoomManager(RoomManagerPK roomManagerPK);

	public abstract List<Campus> getManagedCampuses(UsrPK usrPK);

	public abstract List<Building> getManagedBuildings(CampusPK campusPK,UsrPK usrPK);

	public abstract List<Building> getAllManagedBuildings(UsrPK usrPK);

	public abstract List<Room> getManagedRooms(BuildingPK buildingPK, UsrPK usrPK);

	public abstract List<Room> getAllManagedRooms(UsrPK usrPK);

	public abstract List<RoomAttributeType> getAllManagedRoomAttributeTypes(UsrPK usrPK);

	public abstract List<Object[]> getManagedRooms(RoomAttributeTypePK roomAttributeTypePK, UsrPK usrPK);

	public abstract boolean isCampusManaged(CampusPK campusPK, UsrPK usrPK);

	public abstract boolean isBuildingManaged(BuildingPK buildingPK, UsrPK usrPK);

	public abstract boolean isRoomManaged(RoomPK roomPK,UsrPK usrPK);

	public abstract boolean isRoomAttributeTypeManaged(RoomAttributeTypePK roomAttributeTypePK,UsrPK usrPK);

	public abstract boolean isRoomAttributeManaged(RoomAttributePK roomAttributePK, UsrPK usrPK);

	public abstract List<Reservation> getPreviousHandledWaitingReservations(UsrPK usrPK);

	public abstract List<Reservation> getUpcomingHandledWaitingReservations(UsrPK usrPK);

	public abstract List<Reservation> getPreviousHandledBookedReservations(UsrPK usrPK);

	public abstract List<Reservation> getUpcomingHandledBookedReservations(UsrPK usrPK);

	public abstract List<Reservation> getPreviousHandledCancelledReservations(UsrPK usrPK);

	public abstract List<Reservation> getUpcomingHandledCancelledReservations(UsrPK usrPK);

	public abstract void confirmReservation(ReservationPK reservationPK);	

	public abstract boolean confirmerHandlesReservation(Reservation reservation,UsrPK usrPK);
}
