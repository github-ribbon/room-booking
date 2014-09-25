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

import edu.university.roombooking.domain.BookableRoomPK;
import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomManager;
import edu.university.roombooking.domain.RoomManagerPK;
import edu.university.roombooking.domain.UsrGroupPK;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.util.Decodable;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.RoomManagerValidator;


@Controller
@RequestMapping(value="/room-manager")
@PreAuthorize(value="hasRole('ROLE_ADMIN')")
public class RoomManagerController implements Decodable<RoomManager>{

	@Autowired
	private Pagination<Room> roomPagination;

	@Autowired
	private Pagination<RoomManager> roomManagerPagination;	

	@Autowired
	private RoomManagerValidator roomManagerValidator;	

	@Autowired
	private ObjectManagement objectManagement;	

	@Autowired
	private GroupManagement groupManagement;


	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}		

	public RoomManager getDecodedInstance(String encodedString) throws UnsupportedEncodingException{

		RoomManager roomManager=new RoomManager();			
		roomManager.setId(new RoomManagerPK());

		String decodedString="";

		decodedString = URLDecoder.decode(encodedString,"utf8");

		String[] params = decodedString.split("&");     


		for (String param : params){  	

			String name = param.split("=")[0]; 

			String value = "";

			if(param.split("=").length==2){
				value=param.split("=")[1];
			}

			value = URLDecoder.decode(value, "utf8");

			if(name.equals("campus_id")) roomManager.getId().setCampusId(value);
			else if(name.equals("building_id")) roomManager.getId().setBuildingId(value);
			else if(name.equals("room_id")) roomManager.getId().setRoomId(value);
			else if(name.equals("usr_group_id")){		        	
				roomManager.getId().setUsrGroupId(value);

				UsrGroupPK usrGroupPK=new UsrGroupPK();
				usrGroupPK.setUsrGroupId(value);

				roomManager.setUsrGroup(groupManagement.getUsrGroup(usrGroupPK));	        		
			}  		        	        
		}

		return roomManager;	
	}	

	@RequestMapping(value="/new",params = "choose_campus", method = RequestMethod.POST)
	public String chooseBookableCampus(
			RoomManager roomManager,BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String _params="campus_id="+URLEncoder.encode(roomManager.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(roomManager.getId().getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(roomManager.getId().getRoomId(),"utf8")
				+"&usr_group_id="+URLEncoder.encode(roomManager.getId().getUsrGroupId(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/bookable-campuses?source=room_manager&_params="+_params;		
	}

	@RequestMapping(value="/new",params = "choose_building", method = RequestMethod.POST)
	public String chooseBookableBuilding(
			RoomManager roomManager,BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String _params="campus_id="+URLEncoder.encode(roomManager.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(roomManager.getId().getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(roomManager.getId().getRoomId(),"utf8")
				+"&usr_group_id="+URLEncoder.encode(roomManager.getId().getUsrGroupId(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/bookable-buildings?source=room_manager&campus_id="
		+URLEncoder.encode(roomManager.getId().getCampusId(),"utf8")+"&_params="+_params;		
	}	

	@RequestMapping(value="/new",params = "choose_room", method = RequestMethod.POST)
	public String chooseBookableRoom(RoomManager roomManager,BindingResult result,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String _params="campus_id="+URLEncoder.encode(roomManager.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(roomManager.getId().getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(roomManager.getId().getRoomId(),"utf8")
				+"&usr_group_id="+URLEncoder.encode(roomManager.getId().getUsrGroupId(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");	

		return "redirect:/choose/bookable-rooms?source=room_manager&campus_id="
		+URLEncoder.encode(roomManager.getId().getCampusId(),"utf8")
		+"&building_id="+URLEncoder.encode(roomManager.getId().getBuildingId(),"utf8")+"&_params="+_params;		
	}

	@RequestMapping(value="/new",params = "choose_usr_group", method = RequestMethod.POST)
	public String chooseUsrGroup(RoomManager roomManager,BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String _params="campus_id="+URLEncoder.encode(roomManager.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(roomManager.getId().getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(roomManager.getId().getRoomId(),"utf8")
				+"&usr_group_id="+URLEncoder.encode(roomManager.getId().getUsrGroupId(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");	

		return "redirect:/choose/usr-groups?source=room_manager&_params="+_params;		
	}

	@RequestMapping(value="/by-room",method = RequestMethod.GET)
	public String displayRoomManagersByRoom(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {		

		BookableRoomPK bookableRoomPK=new BookableRoomPK();
		bookableRoomPK.setCampusId(campusId);
		bookableRoomPK.setBuildingId(buildingId);
		bookableRoomPK.setRoomId(roomId);				

		if(objectManagement.isBookableRoom(bookableRoomPK)){							
			roomManagerPagination.preparePage(model,
					objectManagement.getRoomManagers(bookableRoomPK), 
					page,"roomManagers");

			model.addAttribute("campusId",campusId);
			model.addAttribute("buildingId",buildingId);
			model.addAttribute("roomId",roomId);
		}else{
			model.addAttribute("isBookableRoomPKIncorrect",true);		
		}		

		return "roomManagerListByRoom";
	}	

	@RequestMapping(value="/by-usr-group",method = RequestMethod.GET)
	public String displayRoomManagersByUsrGroup(
			@RequestParam(value="usr_group_id", required=true) String usrGroupId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {		

		UsrGroupPK usrGroupPK=new UsrGroupPK();		
		usrGroupPK.setUsrGroupId(usrGroupId);

		if(objectManagement.isUsrGroup(usrGroupPK)){								
			roomManagerPagination.preparePage(model, 
					objectManagement.getRoomManagers(usrGroupPK),
					page,"roomManagers");

			model.addAttribute("usrGroupId",usrGroupId);
		}else{
			model.addAttribute("isUsrGroupPKIncorrect",true);		
		}	

		return "roomManagerListByUsrGroup";
	}

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewRoomManagerForm(
			@RequestParam(value="campus_id", required=false) String campusId,
			@RequestParam(value="building_id", required=false) String buildingId,
			@RequestParam(value="room_id", required=false) String roomId,
			@RequestParam(value="usr_group_id", required=false) String usrGroupId,
			@RequestParam(value="_params", required=false) String _params,
			HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{

		RoomManager roomManager=new RoomManager();;
		roomManager.setId(new RoomManagerPK());

		if(_params!=null){			
			roomManager = getDecodedInstance(_params);
		}

		if((campusId!=null)&&(!campusId.isEmpty())){
			roomManager.getId().setCampusId(campusId);
		}
		if((buildingId!=null)&&(!buildingId.isEmpty())){
			roomManager.getId().setBuildingId(buildingId);
		}
		if((roomId!=null)&&(!roomId.isEmpty())){
			roomManager.getId().setRoomId(roomId);
		}						
		if(usrGroupId!=null){							
			roomManager.getId().setUsrGroupId(usrGroupId);		
		}		

		model.addAttribute("roomManager",roomManager);							
		return "newRoomManagerForm";		
	}	

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayRoomManagerDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			@RequestParam(value="usr_group_id", required=true) String usrGroupId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		RoomManagerPK roomManagerPK=new RoomManagerPK();
		roomManagerPK.setCampusId(campusId);
		roomManagerPK.setBuildingId(buildingId);
		roomManagerPK.setRoomId(roomId);		
		roomManagerPK.setUsrGroupId(usrGroupId);

		if(objectManagement.isRoomManager(roomManagerPK)){				
			model.addAttribute("roomManager",
					objectManagement.getRoomManager(roomManagerPK));				
		}else{
			model.addAttribute("isRoomManagerPKIncorrect",true);
		}				

		return "roomManagerManagement";
	}	

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createRoomManager(RoomManager roomManager,BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response,
			Model model) throws UnsupportedEncodingException {

		roomManagerValidator.validate(roomManager, bindingResult);

		if(bindingResult.hasErrors()){						
			return "newRoomManagerForm";			
		}else{					
			objectManagement.createRoomManager(roomManager);			
			return "redirect:/room-manager/by-room?campus_id="
			+URLEncoder.encode(roomManager.getId().getCampusId(),"utf8")
			+"&building_id="+URLEncoder.encode(roomManager.getId().getBuildingId(),"utf8")
			+"&room_id="+URLEncoder.encode(roomManager.getId().getRoomId(),"utf8");			
		}					
	}	

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String deleteRoomManager(RoomManager roomManager,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		objectManagement.deleteRoomManager(roomManager.getId());				
		return "redirect:/room-manager/by-room?campus_id="
		+URLEncoder.encode(roomManager.getId().getCampusId(),"utf8")
		+"&building_id="+URLEncoder.encode(roomManager.getId().getBuildingId(),"utf8")
		+"&room_id="+URLEncoder.encode(roomManager.getId().getRoomId(),"utf8");
	}
}

