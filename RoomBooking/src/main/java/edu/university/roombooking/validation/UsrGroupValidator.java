package edu.university.roombooking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.university.roombooking.domain.UsrGroup;
import edu.university.roombooking.service.GroupManagement;

@Component
public class UsrGroupValidator implements Validator{

	@Autowired
	private GroupManagement groupManagement;	

	public boolean supports(Class<?> clazz) {
		return UsrGroup.class.equals(clazz);
	}	

	public void validate(Object object,Errors errors){	

		UsrGroup usrGroup=(UsrGroup) object;		

		int usrGroupIdLength=usrGroup.getId().getUsrGroupId().length();						

		if((usrGroupIdLength<3)||(usrGroupIdLength>50)){
			errors.rejectValue("id.usrGroupId", "required.fifty");
		}else if(!groupManagement.isUsrGroup(usrGroup.getId())){
			errors.rejectValue("id.usrGroupId", "unique.usrGroupName");
		}		

		if(usrGroup.getDescription().length()>300){
			errors.rejectValue("description", "excess.threeHundred");
		}			
	}			
}
