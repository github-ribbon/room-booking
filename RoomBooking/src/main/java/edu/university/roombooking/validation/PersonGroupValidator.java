package edu.university.roombooking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.university.roombooking.domain.PersonGroup;
import edu.university.roombooking.service.GroupManagement;

@Component
public class PersonGroupValidator implements Validator{

	@Autowired
	private GroupManagement groupManagement;

	public boolean supports(Class<?> clazz) {
		return PersonGroup.class.equals(clazz);
	}	

	public void validate(Object object,Errors errors){	

		PersonGroup personGroup=(PersonGroup) object;			

		int personGroupIdLength=personGroup.getId().getPersonGroupId().length();						

		if((personGroupIdLength<3)||(personGroupIdLength>50)){
			errors.rejectValue("id.personGroupId", "required.fifty");
		}else	
			if(!groupManagement.isPersonGroup(personGroup.getId())){
				errors.rejectValue("id.personGroupId", "unique.personGroupName");
			}

		if(personGroup.getDescription().length()>300){
			errors.rejectValue("description", "excess.threeHundred");
		}		
	}			
}
