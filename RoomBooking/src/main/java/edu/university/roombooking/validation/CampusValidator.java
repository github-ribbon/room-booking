package edu.university.roombooking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import edu.university.roombooking.domain.Campus;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.validation.abstraction.CustomisedValidator;

@Component
public class CampusValidator implements CustomisedValidator {

	@Autowired
	private ObjectManagement objectManagement;

	public boolean supports(Class<?> clazz) {
		return Campus.class.equals(clazz);
	}	

	public void validate(Object object,Errors errors) {

		Campus campus=(Campus) object;	

		int campusIdLength=campus.getId().getCampusId().length();
		int nameLength=campus.getName().length();		

		if((campusIdLength<1)||(campusIdLength>10)){
			errors.rejectValue("id.campusId", "required.ten");
		}					

		if((nameLength<3)||(nameLength>50)){
			errors.rejectValue("name", "required.fifty");
		}		

		if(campus.getDescription().length()>300){
			errors.rejectValue("description", "excess.threeHundred");
		}		
	}

	public void validateBeforeCreating(Object object,Errors errors) {

		Campus campus=(Campus) object;	

		validate(campus,errors);

		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId(campus.getId().getCampusId());

		if(objectManagement.isCampus(campusPK)){
			errors.rejectValue("id.campusId","existing.campus");
		}		
	}			
}
