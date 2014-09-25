package edu.university.roombooking.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.university.roombooking.domain.Building;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.util.Decodable;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.BuildingValidator;



@Controller
@RequestMapping(value="/building")
@PreAuthorize(value="hasRole('ROLE_ADMIN')")
public class BuildingController implements Decodable<Building>{	

	@Autowired
	private Pagination<Building> buildingPagination;

	@Autowired
	private BuildingValidator buildingValidator;	

	@Autowired
	private ObjectManagement objectManagement;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}

	public Building getDecodedInstance(String encodedString) throws UnsupportedEncodingException{

		Building building=new Building();
		building.setId(new BuildingPK());

		String decodedString="";		
		decodedString = URLDecoder.decode(encodedString,"utf8");

		String[] params = decodedString.split("&"); 		  

		for (String param : params) {

			String name = param.split("=")[0]; 		        
			String value = "";

			if(param.split("=").length==2){
				value=param.split("=")[1];
			}	        		        	        	

			value = URLDecoder.decode(value, "utf8");				

			if(name.equals("campus_id")) building.getId().setCampusId(value);
			else if(name.equals("building_id")) building.getId().setBuildingId(value);
			else if(name.equals("name")) building.setName(value);
			else if(name.equals("description")) building.setDescription(value); 		        	        
		}		

		return building;	
	}		

	@RequestMapping(value="/new",params = "choose_campus", method = RequestMethod.POST)
	public String chooseCampus(Building building,BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String _params="campus_id="+URLEncoder.encode(building.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(building.getId().getBuildingId(),"utf8")
				+"&name="+URLEncoder.encode(building.getName(),"utf8")
				+"&description="+URLEncoder.encode(building.getDescription(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/campuses?source=building&_params="+_params;		
	}	

	@RequestMapping(method = RequestMethod.GET)
	public String displayBuildingsByCampus(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="page", required=false) Integer page,HttpServletRequest request,
			HttpServletResponse response,Model model) {		

		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId(campusId);		

		if(objectManagement.isCampus(campusPK)){			
			buildingPagination.preparePage(model, 
					objectManagement.getBuildingsByCampus(objectManagement.getCampus(campusPK)),
					page, "buildings");			
			model.addAttribute("campusId",campusId);			
		}else{
			model.addAttribute("isCampusPKIncorrect",true);			
		}

		return "buildingList";			
	}			

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String displayAllBuildings(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {			

		buildingPagination.preparePage(model, objectManagement.getAllBuildings(), page,"buildings");		
		return "fullBuildingList";	
	}

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createBuilding(Building building, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		buildingValidator.validateBeforeCreating(building, bindingResult);

		if(bindingResult.hasErrors()){			
			return "newBuildingForm";			
		}else{			
			objectManagement.addBuilding(building);			
			return "redirect:/building?campus_id="
			+URLEncoder.encode(building.getId().getCampusId(),"utf8");		
		}		
	}	

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewBuildingForm(
			@RequestParam(value="campus_id", required=false) String campusId,
			@RequestParam(value="_params", required=false) String _params,HttpServletRequest request,
			HttpServletResponse response,Model model) throws UnsupportedEncodingException {

		Building building=new Building();
		building.setId(new BuildingPK());

		if(_params!=null){				
			building = getDecodedInstance(_params);			
		}				

		if((campusId!=null)&&(!campusId.isEmpty())){
			building.getId().setCampusId(campusId);
		}

		model.addAttribute("building",building);			

		return "newBuildingForm";		
	}		

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String deleteBuilding(Building building,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {		

		objectManagement.deleteBuilding(building.getId());				
		return "redirect:/building?campus_id="
		+URLEncoder.encode(building.getId().getCampusId(),"utf8");
	}	

	@RequestMapping(value="/manage",params = "update", method = RequestMethod.POST)
	public String updateBuilding(Building building, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		buildingValidator.validate(building, bindingResult);

		if(bindingResult.hasErrors()){					
			return "buildingManagement";
		}else{			
			objectManagement.modifyBuilding(building);		
			return "redirect:/building?campus_id="
			+URLEncoder.encode(building.getId().getCampusId(),"utf8");		
		}		
	}		

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayBuildingDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId(campusId);
		buildingPK.setBuildingId(buildingId);

		if(objectManagement.isBuilding(buildingPK)){
			model.addAttribute("building",objectManagement.getBuilding(buildingPK));			
		}else{
			model.addAttribute("isBuildingPKIncorrect",true);			
		}		

		return "buildingManagement";
	}	
}
