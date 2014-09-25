package edu.university.roombooking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import edu.university.roombooking.domain.RoomAttribute;
import edu.university.roombooking.domain.RoomAttributePK;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.validation.abstraction.CustomisedValidator;


@Component
public class RoomAttributeValidator implements CustomisedValidator{

	@Autowired
	private ObjectManagement objectManagement;		

	public boolean supports(Class<?> clazz) {
		return RoomAttribute.class.equals(clazz);		
	}	

	public void validate(Object object,Errors errors) {		

		RoomAttribute roomAttribute=(RoomAttribute) object;

		int valueLength=roomAttribute.getValue().length();		

		if((valueLength==0)||(valueLength>50)){
			errors.rejectValue("value", "required.upFifty");
		}		
	}

	public void validateBeforeCreating(Object object,Errors errors) {		

		RoomAttribute roomAttribute=(RoomAttribute) object;

		validate(roomAttribute,errors);		

		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId(roomAttribute.getId().getRoomAttributeTypeId());

		if(!objectManagement.isRoomAttributeType(roomAttributeTypePK)){
			errors.rejectValue("id.roomAttributeTypeId", "required.lackChoice");
		}

		RoomAttributePK roomAttributePK=new RoomAttributePK();
		roomAttributePK.setCampusId(roomAttribute.getId().getCampusId());
		roomAttributePK.setBuildingId(roomAttribute.getId().getBuildingId());;
		roomAttributePK.setRoomId(roomAttribute.getId().getRoomId());
		roomAttributePK.setRoomAttributeTypeId(roomAttribute.getId().getRoomAttributeTypeId());

		if(objectManagement.isRoomAttribute(roomAttributePK)){
			errors.rejectValue("id.roomAttributeTypeId","existing.ra");
		}		

		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId(roomAttribute.getId().getCampusId());
		roomPK.setBuildingId(roomAttribute.getId().getBuildingId());
		roomPK.setRoomId(roomAttribute.getId().getRoomId());

		if(!objectManagement.isRoom(roomPK)){
			errors.rejectValue("id.roomId","notExisting.room");
		}		
	}	
}