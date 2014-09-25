package edu.university.roombooking.validation;

import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.university.roombooking.domain.Usr;
import edu.university.roombooking.service.GroupManagement;

@Component
public class UsrValidator implements Validator{

	@Autowired
	private GroupManagement groupManagement;

	public boolean supports(Class<?> clazz) {
		return Usr.class.equals(clazz);
	}	

	public void validate(Object object,Errors errors){	

		Usr usr=(Usr) object;

		int loginLength=usr.getId().getUsrId().length();						
		int emailLength=usr.getEmail().length();

		if((loginLength<3)||(loginLength>50)){
			errors.rejectValue("id.usrId", "required.fifty");
		}else if(!groupManagement.isUsr(usr.getId())){
			errors.rejectValue("id.usrId", "unique.login");		
		}				

		if(!EmailValidator.getInstance().isValid(usr.getEmail())){
			errors.rejectValue("email", "incorrect.email");
		}

		if((emailLength<3)){
			errors.rejectValue("email", "required.fifty");
		}else if(!groupManagement.isEmailUnique(usr.getEmail())){
			errors.rejectValue("email", "unique.email");		
		}			

		if((usr.getPersonId()!=null)&&(!groupManagement.isPersonUnique(usr.getPersonId()))){
			errors.rejectValue("personId", "unique.person");
		}		
	}

	public void validateUserPassword(Usr usr,Errors errors){

		int passwordLength=usr.getPassword().length();

		if(passwordLength<6){
			errors.rejectValue("password", "required.password");
		}		
	}		
}
