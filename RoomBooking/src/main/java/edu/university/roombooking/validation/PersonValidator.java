package edu.university.roombooking.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.university.roombooking.domain.Person;

@Component
public class PersonValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}	

	public void validate(Object object,Errors errors){

		Person person=(Person) object;	

		int givenNameLength=person.getGivenName().length();
		int familyNameLength=person.getFamilyName().length();					

		if((givenNameLength<1)||(givenNameLength>50)){
			errors.rejectValue("givenName", "required.upFifty");
		}		

		if((familyNameLength<1)||(familyNameLength>50)){
			errors.rejectValue("familyName", "required.upFifty");
		}		
	}		
}
