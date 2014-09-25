package edu.university.roombooking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import edu.university.roombooking.domain.Building;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.validation.abstraction.CustomisedValidator;


@Component
public class BuildingValidator implements CustomisedValidator {

	@Autowired
	private ObjectManagement objectManagement;	

	public boolean supports(Class<?> clazz) {
		return Building.class.equals(clazz);		
	}	

	public void validate(Object object, Errors errors) {

		Building building=(Building) object;		

		int buildingIdLength=building.getId().getBuildingId().length();
		int nameLength=building.getName().length();		

		if((buildingIdLength<1)||(buildingIdLength>10)){
			errors.rejectValue("id.buildingId", "required.ten");
		}					

		if((nameLength<3)||(nameLength>50)){
			errors.rejectValue("name", "required.fifty");
		}		

		if(building.getDescription().length()>300){
			errors.rejectValue("description", "excess.threeHundred");
		}	

		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId(building.getId().getCampusId());

		if(!objectManagement.isCampus(campusPK)){
			errors.rejectValue("id.campusId","notExisting.campus");
		}		
	}

	public void validateBeforeCreating(Object object,Errors errors) {

		Building building=(Building) object;

		validate(building,errors);

		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId(building.getId().getCampusId());
		buildingPK.setBuildingId(building.getId().getBuildingId());

		if(objectManagement.isBuilding(buildingPK)){			
			errors.rejectValue("id.buildingId","existing.building");
		}		
	}
}
