package edu.university.roombooking.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.university.roombooking.domain.Building;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.Campus;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.domain.Person;
import edu.university.roombooking.domain.PersonGroup;
import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomAttributeType;
import edu.university.roombooking.domain.Usr;
import edu.university.roombooking.domain.UsrGroup;
import edu.university.roombooking.domain.UsrPK;
import edu.university.roombooking.service.BookingConfirmation;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.service.RoomBooking;
import edu.university.roombooking.util.Pagination;



@Controller
@RequestMapping(value="/choose")
public class ChooseController {

	@Autowired
	private Pagination<Campus> campusPagination;	

	@Autowired
	private Pagination<Building> buildingPagination;	

	@Autowired
	private Pagination<Room> roomPagination;

	@Autowired
	private Pagination<RoomAttributeType> roomAttributeTypePagination;	

	@Autowired
	private Pagination<Person> personPagination;

	@Autowired
	private Pagination<Usr> usrPagination;

	@Autowired
	private Pagination<PersonGroup> personGroupPagination;

	@Autowired
	private Pagination<UsrGroup> usrGroupPagination;

	@Autowired
	private ObjectManagement objectManagement;

	@Autowired
	private RoomBooking roomBooking;

	@Autowired
	private BookingConfirmation bookingConfirmation;

	@Autowired
	private GroupManagement groupManagement;


	@PreAuthorize(value="hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/campuses",method = RequestMethod.GET)
	public String chooseCampus(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="_params", required=true) String _params,
			@RequestParam(value="source", required=true) String source,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		model.addAttribute("role","admin"); 

		if(source.equals("building")||source.equals("room")||source.equals("ra")){							
			campusPagination.preparePage(model, objectManagement.getAllCampuses(), page,"campuses");		

			model.addAttribute("source",source);
			model.addAttribute("_params",_params);			
		}else{
			model.addAttribute("isSourceIncorrect",true);			
		}		

		return "chooseCampus";
	}

	@PreAuthorize(value="hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
	@RequestMapping(value="/bookable-campuses",method = RequestMethod.GET)
	public String chooseBookableCampus(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="_params", required=true) String _params,
			@RequestParam(value="source", required=true) String source,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		boolean isClient;

		if((isClient=(source.equals("res-list-by-day")||(source.equals("reservation"))))
				||(source.equals("room_manager"))){

			if(isClient){
				model.addAttribute("role","client");
			}else{
				model.addAttribute("role","admin");
			}

			campusPagination.preparePage(model, roomBooking.getAllBookableCampuses(), page,"campuses");

			model.addAttribute("source",source);
			model.addAttribute("_params",_params);			
		}else{
			model.addAttribute("isSourceIncorrect",true);			
		}	

		return "chooseCampus";
	}

	@PreAuthorize(value="hasRole('ROLE_CONFIRMER')")
	@RequestMapping(value="/managed-campuses",method = RequestMethod.GET)
	public String chooseManagedCampus(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="_params", required=true) String _params,			
			HttpServletRequest request,HttpServletResponse response,
			Model model,Principal principal) {				

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		campusPagination.preparePage(model, 
				bookingConfirmation.getManagedCampuses(usrPK), page,"campuses"); 		

		model.addAttribute("source","res-list-by-day");	
		model.addAttribute("_params",_params);
		model.addAttribute("role","confirmer");		

		return "chooseCampus";	
	}

	@PreAuthorize(value="hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/buildings",method = RequestMethod.GET)
	public String chooseBuilding(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="_params", required=true) String _params,
			@RequestParam(value="source", required=true) String source,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		model.addAttribute("role","admin");	

		if(source.equals("room")||source.equals("ra")){				
			CampusPK campusPK=new CampusPK();
			campusPK.setCampusId(campusId);									

			if(objectManagement.isCampus(campusPK)){								
				buildingPagination.preparePage(model, 
						objectManagement.getBuildingsByCampus(objectManagement.getCampus(campusPK)),
						page,"buildings");

				model.addAttribute("campusId",campusId);		
				model.addAttribute("source",source);	
				model.addAttribute("_params",_params);				
			}else{
				model.addAttribute("isCampusPKIncorrect",true);	
			}			
		}else{
			model.addAttribute("isSourceIncorrect",true);			
		}

		return "chooseBuilding";
	}

	@PreAuthorize(value="hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
	@RequestMapping(value="/bookable-buildings",method = RequestMethod.GET)
	public String chooseBookableBuilding(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="_params", required=true) String _params,
			@RequestParam(value="source", required=true) String source,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		boolean isClient;

		if((isClient=(source.equals("res-list-by-day")||(source.equals("reservation"))))
				||(source.equals("room_manager"))){

			if(isClient){
				model.addAttribute("role","client");
			}else{
				model.addAttribute("role","admin");
			}				

			CampusPK campusPK=new CampusPK();
			campusPK.setCampusId(campusId);		

			if(roomBooking.isCampusBookable(campusPK)){					
				buildingPagination.preparePage(model, 
						roomBooking.getBookableBuildings(campusPK), page,"buildings");

				model.addAttribute("campusId",campusId);		
				model.addAttribute("source",source);
				model.addAttribute("_params",_params);	
			}else{
				model.addAttribute("isCampusPKIncorrect",true);		
			}			
		}else{
			model.addAttribute("isSourceIncorrect",true);			
		}

		return "chooseBuilding";
	}

	@PreAuthorize(value="hasRole('ROLE_CONFIRMER')")
	@RequestMapping(value="/managed-buildings",method = RequestMethod.GET)
	public String chooseManagedBuilding(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="_params", required=true) String _params,			
			HttpServletRequest request,HttpServletResponse response,
			Model model,Principal principal) {

		model.addAttribute("role","confirmer");

		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId(campusId);		

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());				

		if(bookingConfirmation.isCampusManaged(campusPK, usrPK)){				
			buildingPagination.preparePage(model, 
					bookingConfirmation.getManagedBuildings(campusPK,usrPK), page,"buildings");

			model.addAttribute("campusId",campusId);		
			model.addAttribute("source","res-list-by-day");
			model.addAttribute("_params",_params);			
		}else{
			model.addAttribute("isCampusPKIncorrect",true);	
		}			

		return "chooseBuilding";	
	}	

	@PreAuthorize(value="hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/rooms",method = RequestMethod.GET)
	public String chooseRoom(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="_params", required=true) String _params,			
			HttpServletRequest request,HttpServletResponse response,Model model) {

		model.addAttribute("role","admin");	

		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId(campusId);
		buildingPK.setBuildingId(buildingId);			

		if(objectManagement.isBuilding(buildingPK)){			
			roomPagination.preparePage(model, 
					objectManagement.getRooms(objectManagement.getBuilding(buildingPK)),
					page,"rooms");		

			model.addAttribute("campusId",campusId);
			model.addAttribute("buildingId",buildingId);

			model.addAttribute("source","ra");	
			model.addAttribute("_params",_params);			
		}else{
			model.addAttribute("isBuildingPKIncorrect",true);
		}			

		return "chooseRoom";	
	}	

	@PreAuthorize(value="hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
	@RequestMapping(value="/bookable-rooms",method = RequestMethod.GET)
	public String chooseBookableRoom(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="_params", required=true) String _params,
			@RequestParam(value="source", required=true) String source,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		boolean isClient;

		if((isClient=(source.equals("res-list-by-day")||(source.equals("reservation"))))
				||(source.equals("room_manager"))){

			if(isClient){
				model.addAttribute("role","client");
			}else{
				model.addAttribute("role","admin");
			}			

			BuildingPK buildingPK=new BuildingPK();
			buildingPK.setCampusId(campusId);
			buildingPK.setBuildingId(buildingId);			

			if(roomBooking.isBuildingBookable(buildingPK)){					
				roomPagination.preparePage(model,
						roomBooking.getBookableRooms(buildingPK), page,"rooms");	

				model.addAttribute("campusId",campusId);
				model.addAttribute("buildingId",buildingId);

				model.addAttribute("source",source);
				model.addAttribute("_params",_params);	
			}else{
				model.addAttribute("isBuildingPKIncorrect",true);		
			}		
		}else{
			model.addAttribute("isSourceIncorrect",true);			
		}

		return "chooseRoom";
	}	

	@PreAuthorize(value="hasRole('ROLE_CONFIRMER')")
	@RequestMapping(value="/managed-rooms",method = RequestMethod.GET)
	public String chooseManagedRoom(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="_params", required=true) String _params,			
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		model.addAttribute("role","confirmer");

		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId(campusId);
		buildingPK.setBuildingId(buildingId);	

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());

		if(bookingConfirmation.isBuildingManaged(buildingPK, usrPK)){				
			roomPagination.preparePage(model, 
					bookingConfirmation.getManagedRooms(buildingPK, usrPK), page,"rooms");			

			model.addAttribute("campusId",campusId);
			model.addAttribute("buildingId",buildingId);

			model.addAttribute("source","res-list-by-day");
			model.addAttribute("_params",_params);				
		}else{
			model.addAttribute("isBuildingPKIncorrect",true);	
		}		

		return "chooseRoom";
	}

	@PreAuthorize(value="hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/rats",method = RequestMethod.GET)
	public String chooseRoomAttributeType(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="_params", required=true) String _params,			
			HttpServletRequest request,HttpServletResponse response,Model model) {

		roomAttributeTypePagination.preparePage(model,
				objectManagement.getAllRoomAttributeTypes(), page,"roomAttributeTypes");		
		model.addAttribute("_params",_params);			
		return "chooseRoomAttributeType";	
	}	

	@PreAuthorize(value="hasRole('ROLE_GROUP')")
	@RequestMapping(value="/persons",method = RequestMethod.GET)
	public String choosePerson(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="_params", required=true) String _params,
			@RequestParam(value="source", required=true) String source,	
			@RequestParam(value="person_group_id",required=false) String personGroupId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		model.addAttribute("role","groupManagement");

		boolean isPersonGroupPerson;

		if((isPersonGroupPerson=source.equals("person_group_person"))||source.equals("usr")){					
			personPagination.preparePage(model, groupManagement.getAllPersons(), page,"persons");	

			model.addAttribute("source",source);					
			model.addAttribute("_params",_params);

			if(isPersonGroupPerson){
				model.addAttribute("personGroupId",personGroupId);	
			}
		}else{
			model.addAttribute("isSourceIncorrect",true);
		}			

		return "choosePerson";	
	}

	@PreAuthorize(value="hasRole('ROLE_GROUP')")
	@RequestMapping(value="/users",method = RequestMethod.GET)
	public String chooseUser(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="_params", required=true) String _params,
			@RequestParam(value="usr_group_id",required=true) String usrGroupId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		usrPagination.preparePage(model, groupManagement.getAllUsers(), page,"users");		

		model.addAttribute("_params",_params);	
		model.addAttribute("usrGroupId",usrGroupId);

		return "chooseUsr";
	}

	@PreAuthorize(value="hasAnyRole('ROLE_GROUP','ROLE_CLIENT')")
	@RequestMapping(value="/person-groups",method = RequestMethod.GET)
	public String choosePersonGroup(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="_params", required=true) String _params,
			@RequestParam(value="source", required=true) String source,
			@RequestParam(value="action", required=false) String action,
			@RequestParam(value="reservation_id", required=false) Integer reservationId,
			@RequestParam(value="person_id",required=false) String personId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		boolean isReservation;

		if((isReservation=(source.equals("reservation")&&((action!=null)
				&&(action.equals("create")||((action.equals("manage"))
						&&((reservationId!=null)&&(reservationId>0)))))))
						||source.equals("person_group_person")){			

			if(isReservation){
				model.addAttribute("action",action);
				model.addAttribute("reservationId", reservationId);
				model.addAttribute("role","client");
			}else{
				model.addAttribute("personId",personId);
				model.addAttribute("role","groupManagement");
			}

			personGroupPagination.preparePage(model, 
					groupManagement.getAllPersonGroups(), page,"personGroups");

			model.addAttribute("source",source);
			model.addAttribute("_params",_params);				
		}else{
			model.addAttribute("isSourceIncorrect",true);
		}	

		return "choosePersonGroup";	
	}

	@PreAuthorize(value="hasAnyRole('ROLE_ADMIN','ROLE_GROUP')")
	@RequestMapping(value="/usr-groups",method = RequestMethod.GET)
	public String chooseUsrGroup(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="_params", required=true) String _params,
			@RequestParam(value="source", required=true) String source,		
			@RequestParam(value="usr_id",required=false) String usrId,
			HttpServletRequest request,HttpServletResponse response,Model model) {		

		boolean isRoomManager;

		if((isRoomManager=source.equals("room_manager"))||(source.equals("usr_group_usr"))){

			if(isRoomManager){
				model.addAttribute("role","admin");
			}else{
				model.addAttribute("role","groupManagement");
				model.addAttribute("usrId",usrId);
			}					

			usrGroupPagination.preparePage(model,
					groupManagement.getAllUserGroups(), page,"usrGroups");

			model.addAttribute("source",source);
			model.addAttribute("_params",_params);				
		}else{
			model.addAttribute("isSourceIncorrect",true);
		}	

		return "chooseUsrGroup";	
	}
}
