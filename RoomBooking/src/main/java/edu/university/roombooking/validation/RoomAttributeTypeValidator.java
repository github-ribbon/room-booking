package edu.university.roombooking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import edu.university.roombooking.domain.RoomAttributeType;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.validation.abstraction.CustomisedValidator;

@Component
public class RoomAttributeTypeValidator implements CustomisedValidator {

	@Autowired
	private ObjectManagement objectManagement;	

	public boolean supports(Class<?> clazz) {
		return RoomAttributeType.class.equals(clazz);		
	}	

	public void validate(Object object,Errors errors) {			

		RoomAttributeType roomAttributeType=(RoomAttributeType) object;		

		if(roomAttributeType.getDescription().length()>300){			
			errors.rejectValue("description", "excess.threeHundred");
		}			
	}

	public void validateBeforeCreating(Object object,Errors errors) {

		RoomAttributeType roomAttributeType=(RoomAttributeType) object;

		validate(roomAttributeType,errors);

		int roomAttributeTypeIdLength=roomAttributeType.getId().getRoomAttributeTypeId().length();

		if((roomAttributeTypeIdLength<3)||(roomAttributeTypeIdLength>50)){			
			errors.rejectValue("id.roomAttributeTypeId", "required.fifty");
		}	

		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId(roomAttributeType.getId().getRoomAttributeTypeId());

		if(objectManagement.isRoomAttributeType(roomAttributeTypePK)){			
			errors.rejectValue("id.roomAttributeTypeId","existing.rat");
		}		
	}		
}
