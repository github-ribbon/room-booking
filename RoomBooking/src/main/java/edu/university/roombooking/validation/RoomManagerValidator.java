package edu.university.roombooking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.university.roombooking.domain.BookableRoomPK;
import edu.university.roombooking.domain.RoomManager;
import edu.university.roombooking.domain.UsrGroupPK;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.service.ObjectManagement;


@Component
public class RoomManagerValidator implements Validator {

	@Autowired
	private ObjectManagement objectManagement;	

	@Autowired
	private GroupManagement groupManagement;		

	public boolean supports(Class<?> clazz) {
		return RoomManager.class.equals(clazz);
	}

	public void validate(Object object,Errors errors) {		

		RoomManager roomManager=(RoomManager) object;			

		BookableRoomPK bookableRoomPK=new BookableRoomPK();
		bookableRoomPK.setCampusId(roomManager.getId().getCampusId());		
		bookableRoomPK.setBuildingId(roomManager.getId().getBuildingId());
		bookableRoomPK.setRoomId(roomManager.getId().getRoomId());

		if(!objectManagement.isBookableRoom(bookableRoomPK)){
			errors.rejectValue("id.roomId", "required.bookableRoom");
		}else if(objectManagement.isRoomManager(roomManager.getId())){
			errors.rejectValue("id.roomId", "existing.roomManager");
		}

		UsrGroupPK usrGroupPK=new UsrGroupPK();
		usrGroupPK.setUsrGroupId(roomManager.getId().getUsrGroupId());

		if(!groupManagement.isUsrGroup(usrGroupPK)){
			errors.rejectValue("id.usrGroupId","required.userGroup");
		}		
	}			
}
