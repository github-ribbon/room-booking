package edu.university.roombooking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.validation.abstraction.CustomisedValidator;


@Component
public class RoomValidator implements CustomisedValidator {

	@Autowired
	private ObjectManagement objectManagement;

	public boolean supports(Class<?> clazz) {
		return Room.class.equals(clazz);		
	}

	public void validate(Object object,Errors errors) {		

		Room room=(Room) object;		

		if(room.getDescription().length()>300){
			errors.rejectValue("description", "excess.threeHundred");
		}

		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId(room.getId().getCampusId());
		buildingPK.setBuildingId(room.getId().getBuildingId());

		if(!objectManagement.isBuilding(buildingPK)){
			errors.rejectValue("id.buildingId","notExisting.building");
		}		
	}

	public void validateBeforeCreating(Object object,Errors errors) {		

		Room room=(Room) object;

		validate(room,errors);

		int roomIdLength=room.getId().getRoomId().length();

		if((roomIdLength<1)||(roomIdLength>10)){
			errors.rejectValue("id.roomId", "required.ten");
		}	

		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId(room.getId().getCampusId());
		roomPK.setBuildingId(room.getId().getBuildingId());;
		roomPK.setRoomId(room.getId().getRoomId());

		if(objectManagement.isRoom(roomPK)){
			errors.rejectValue("id.roomId","existing.room");
		}			
	}			
}
