package edu.university.roombooking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.university.roombooking.domain.UsrGroupPK;
import edu.university.roombooking.domain.UsrGroupUsr;
import edu.university.roombooking.domain.UsrPK;
import edu.university.roombooking.service.GroupManagement;


@Component
public class UsrGroupUsrValidator implements Validator{

	@Autowired
	private GroupManagement groupManagement;

	public boolean supports(Class<?> clazz) {
		return UsrGroupUsr.class.equals(clazz);
	}		

	public void validate(Object object,Errors errors){	

		UsrGroupUsr usrGroupUsr=(UsrGroupUsr) object;	

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(usrGroupUsr.getId().getUsrId());

		if(!groupManagement.isUsr(usrPK)){
			errors.rejectValue("id.usrId", "notExisting.usr");
		}

		UsrGroupPK usrGroupPK=new UsrGroupPK();
		usrGroupPK.setUsrGroupId(usrGroupUsr.getId().getUsrGroupId());

		if(!groupManagement.isUsrGroup(usrGroupPK)){
			errors.rejectValue("id.usrGroupId", "notExisting.usrGroup");
		}	

		if(groupManagement.isUserAssignedToUserGroup(usrGroupUsr.getId())){
			errors.rejectValue("id.usrId","existing.usrGroupUsr");
		}		
	}
}
