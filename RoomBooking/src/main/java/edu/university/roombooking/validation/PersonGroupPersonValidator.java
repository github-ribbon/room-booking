package edu.university.roombooking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.university.roombooking.domain.PersonGroupPK;
import edu.university.roombooking.domain.PersonGroupPerson;
import edu.university.roombooking.domain.PersonPK;
import edu.university.roombooking.service.GroupManagement;

@Component
public class PersonGroupPersonValidator implements Validator{

	@Autowired
	private GroupManagement groupManagement;

	public boolean supports(Class<?> clazz) {
		return PersonGroupPerson.class.equals(clazz);
	}	

	public void validate(Object object,Errors errors){	

		PersonGroupPerson personGroupPerson=(PersonGroupPerson) object;	

		PersonPK personPK=new PersonPK();
		personPK.setPersonId(personGroupPerson.getId().getPersonId());

		if(!groupManagement.isPerson(personPK)){
			errors.rejectValue("id.personId", "notExisting.person");
		}

		PersonGroupPK personGroupPK=new PersonGroupPK();
		personGroupPK.setPersonGroupId(personGroupPerson.getId().getPersonGroupId());

		if(!groupManagement.isPersonGroup(personGroupPK)){
			errors.rejectValue("id.personGroupId", "notExisting.personGroup");
		}	

		if(groupManagement.isPersonAssignedToPersonGroup(personGroupPerson.getId())){
			errors.rejectValue("id.personId","existing.personGroupPerson");
		}		
	}
}
