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

import edu.university.roombooking.domain.RoomAttribute;
import edu.university.roombooking.domain.RoomAttributePK;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.util.Decodable;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.RoomAttributeValidator;



@Controller
@RequestMapping(value="/room-attribute")
@PreAuthorize(value="hasRole('ROLE_ADMIN')")
public class RoomAttributeController implements Decodable<RoomAttribute> {

	@Autowired
	private Pagination<RoomAttribute> roomAttributePagination;	

	@Autowired
	private RoomAttributeValidator roomAttributeValidator;	

	@Autowired
	private ObjectManagement objectManagement;		


	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}		

	public RoomAttribute getDecodedInstance(String encodedString) throws UnsupportedEncodingException{

		RoomAttribute roomAttribute=new RoomAttribute();	
		roomAttribute.setId(new RoomAttributePK());

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

			if(name.equals("campus_id")) roomAttribute.getId().setCampusId(value);
			else if(name.equals("building_id")) roomAttribute.getId().setBuildingId(value);
			else if(name.equals("room_id")) roomAttribute.getId().setRoomId(value);
			else if(name.equals("room_attribute_type_id")) roomAttribute.getId().setRoomAttributeTypeId(value);	
			else if(name.equals("value")) roomAttribute.setValue(value); 		        	        
		}

		return roomAttribute;	
	}

	@RequestMapping(value="/new",params = "choose_campus", method = RequestMethod.POST)
	public String chooseCampus(RoomAttribute roomAttribute,BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String _params="campus_id="+URLEncoder.encode(roomAttribute.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(roomAttribute.getId().getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(roomAttribute.getId().getRoomId(),"utf8")
				+"&room_attribute_type_id="+URLEncoder.encode(roomAttribute.getId().getRoomAttributeTypeId(),"utf8")
				+"&value="+URLEncoder.encode(roomAttribute.getValue(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/campuses?source=ra&_params="+_params;		
	}	

	@RequestMapping(value="/new",params = "choose_building", method = RequestMethod.POST)
	public String chooseBuilding(RoomAttribute roomAttribute,BindingResult result,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String _params="campus_id="+URLEncoder.encode(roomAttribute.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(roomAttribute.getId().getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(roomAttribute.getId().getRoomId(),"utf8")
				+"&room_attribute_type_id="+URLEncoder.encode(roomAttribute.getId().getRoomAttributeTypeId(),"utf8")
				+"&value="+URLEncoder.encode(roomAttribute.getValue(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/buildings?source=ra&campus_id="
		+URLEncoder.encode(roomAttribute.getId().getCampusId(),"utf8")+"&_params="+_params;		
	}

	@RequestMapping(value="/new",params = "choose_room", method = RequestMethod.POST)
	public String chooseRoom(RoomAttribute roomAttribute,BindingResult result,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String _params="campus_id="+URLEncoder.encode(roomAttribute.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(roomAttribute.getId().getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(roomAttribute.getId().getRoomId(),"utf8")
				+"&room_attribute_type_id="+URLEncoder.encode(roomAttribute.getId().getRoomAttributeTypeId(),"utf8")
				+"&value="+URLEncoder.encode(roomAttribute.getValue(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/rooms?source=ra&campus_id="
		+URLEncoder.encode(roomAttribute.getId().getCampusId(),"utf8")
		+"&building_id="+URLEncoder.encode(roomAttribute.getId().getBuildingId(),"utf8")+"&_params="+_params;		
	}

	@RequestMapping(value="/new",params = "choose_rat", method = RequestMethod.POST)
	public String chooseRoomAttributeType(RoomAttribute roomAttribute,BindingResult result,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String _params="campus_id="+URLEncoder.encode(roomAttribute.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(roomAttribute.getId().getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(roomAttribute.getId().getRoomId(),"utf8")
				+"&room_attribute_type_id="+URLEncoder.encode(roomAttribute.getId().getRoomAttributeTypeId(),"utf8")
				+"&value="+URLEncoder.encode(roomAttribute.getValue(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/rats?_params="+_params;		
	}	

	@RequestMapping(method = RequestMethod.GET)
	public String displayRoomAttributesByRoom(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {	

		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId(campusId);
		roomPK.setBuildingId(buildingId);
		roomPK.setRoomId(roomId);

		if(objectManagement.isRoom(roomPK)){					
			roomAttributePagination.preparePage(model, 
					objectManagement.getRoomAttributes(objectManagement.getRoom(roomPK)),
					page,"roomAttributes");

			model.addAttribute("campusId",campusId);
			model.addAttribute("buildingId",buildingId);
			model.addAttribute("roomId",roomId);			
		}else{
			model.addAttribute("isRoomPKIncorrect",true);
		}		

		return "roomAttributeList";
	}		

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createRoomAttribute(RoomAttribute roomAttribute, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response,
			Model model) throws UnsupportedEncodingException {

		roomAttributeValidator.validateBeforeCreating(roomAttribute, bindingResult);

		if(bindingResult.hasErrors()){						
			return "newRoomAttributeForm";			
		}else{				
			objectManagement.assignRoomAttributeToRoom(roomAttribute);			
			return "redirect:/room-attribute?campus_id="+URLEncoder.encode(roomAttribute.getId().getCampusId(),"utf8")
					+"&building_id="+URLEncoder.encode(roomAttribute.getId().getBuildingId(),"utf8")
					+"&room_id="+URLEncoder.encode(roomAttribute.getId().getRoomId(),"utf8");
		}		
	}

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewRoomAttributeForm(@RequestParam(value="campus_id", required=false) String campusId,
			@RequestParam(value="building_id", required=false) String buildingId,
			@RequestParam(value="room_id", required=false) String roomId,
			@RequestParam(value="room_attribute_type_id", required=false) String roomAttributeTypeId,
			HttpServletRequest request,@RequestParam(value="_params", required=false) String _params,
			HttpServletResponse response,Model model) throws UnsupportedEncodingException {

		RoomAttribute roomAttribute=new RoomAttribute();		
		roomAttribute.setId(new RoomAttributePK());

		if(_params!=null){				
			roomAttribute=getDecodedInstance(_params);
		}

		if((campusId!=null)&&(!campusId.isEmpty())){
			roomAttribute.getId().setCampusId(campusId);			
		}
		if((buildingId!=null)&&(!buildingId.isEmpty())){
			roomAttribute.getId().setBuildingId(buildingId);			
		}
		if((roomId!=null)&&(!roomId.isEmpty())){
			roomAttribute.getId().setRoomId(campusId);			
		}
		if((roomAttributeTypeId!=null)&&(!roomAttributeTypeId.isEmpty())){
			roomAttribute.getId().setRoomAttributeTypeId(roomAttributeTypeId);			
		}

		model.addAttribute("roomAttribute",roomAttribute);		
		return "newRoomAttributeForm";		
	}

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String deleteRoomAttribute(RoomAttribute roomAttribute, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {			

		objectManagement.deleteAssignedRoomAttribute(roomAttribute.getId());	
		return "redirect:/room-attribute?campus_id="+URLEncoder.encode(roomAttribute.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(roomAttribute.getId().getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(roomAttribute.getId().getRoomId(),"utf8");		
	}	

	@RequestMapping(value="/manage",params = "update", method = RequestMethod.POST)
	public String updateRoomAttribute(RoomAttribute roomAttribute, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		roomAttributeValidator.validate(roomAttribute, bindingResult);

		if(bindingResult.hasErrors()){				
			return "roomAttributeManagement";			
		}else{			
			objectManagement.modifyAssignedRoomAttribute(roomAttribute);
			return "redirect:/room-attribute?campus_id="+URLEncoder.encode(roomAttribute.getId().getCampusId(),"utf8")
					+"&building_id="+URLEncoder.encode(roomAttribute.getId().getBuildingId(),"utf8")
					+"&room_id="+URLEncoder.encode(roomAttribute.getId().getRoomId(),"utf8");
		}			
	}	

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayRoomAttributeDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			@RequestParam(value="room_attribute_type_id", required=true) String roomAttributeTypeId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		RoomAttributePK roomAttributePK=new RoomAttributePK();
		roomAttributePK.setCampusId(campusId);
		roomAttributePK.setBuildingId(buildingId);
		roomAttributePK.setRoomId(roomId);
		roomAttributePK.setRoomAttributeTypeId(roomAttributeTypeId);

		if(objectManagement.isRoomAttribute(roomAttributePK)){			
			model.addAttribute("roomAttribute",objectManagement.getRoomAttribute(roomAttributePK));		
		}else{
			model.addAttribute("isRoomAttributePKIncorrect",true);
		}		

		return "roomAttributeManagement";
	}		
}
