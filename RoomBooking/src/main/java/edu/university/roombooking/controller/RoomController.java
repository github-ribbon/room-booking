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

import edu.university.roombooking.domain.BookableRoom;
import edu.university.roombooking.domain.BookableRoomPK;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.util.Decodable;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.RoomValidator;



@Controller
@RequestMapping(value="/room")
@PreAuthorize(value="hasRole('ROLE_ADMIN')")
public class RoomController implements Decodable<Room> {

	@Autowired
	private Pagination<Room> roomPagination;		

	@Autowired
	private Pagination<Object[]> objectArrayPagination;

	@Autowired
	private RoomValidator roomValidator;	

	@Autowired
	private ObjectManagement objectManagement;		

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}		

	public Room getDecodedInstance(String encodedString) throws UnsupportedEncodingException{

		Room room=new Room();	
		room.setId(new RoomPK());

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

			if(name.equals("campus_id")) room.getId().setCampusId(value);
			else if(name.equals("building_id")) room.getId().setBuildingId(value);
			else if(name.equals("room_id")) room.getId().setRoomId(value);		        
			else if(name.equals("description")) room.setDescription(value);  		        	        
		}	

		return room;	
	}

	@RequestMapping(value="/new",params = "choose_campus", method = RequestMethod.POST)
	public String chooseCampus(Room room,BindingResult bindingResult,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String _params="campus_id="+URLEncoder.encode(room.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(room.getId().getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(room.getId().getRoomId(),"utf8")
				+"&description="+URLEncoder.encode(room.getDescription(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/campuses?source=room&_params="+_params;		
	}


	@RequestMapping(value="/new",params = "choose_building", method = RequestMethod.POST)
	public String chooseBuilding(Room room,BindingResult bindingResult,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String _params="campus_id="+URLEncoder.encode(room.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(room.getId().getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(room.getId().getRoomId(),"utf8")
				+"&description="+URLEncoder.encode(room.getDescription(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");			

		return "redirect:/choose/buildings?source=room&campus_id="
		+URLEncoder.encode(room.getId().getCampusId(),"utf8")+"&_params="+_params;		
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayRoomsByBuilding(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {		

		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId(campusId);
		buildingPK.setBuildingId(buildingId);			

		if(objectManagement.isBuilding(buildingPK)){						
			roomPagination.preparePage(model, 
					objectManagement.getRooms(objectManagement.getBuilding(buildingPK)),
					page,"rooms");

			model.addAttribute("campusId",campusId);
			model.addAttribute("buildingId",buildingId);			
		}else{
			model.addAttribute("isBuildingPKIncorrect",true);
		}	

		return "roomList";
	}		

	@RequestMapping(value="/by-room-attribute-type",method = RequestMethod.GET)
	public String displayRoomsByRoomAttributeType(
			@RequestParam(value="room_attribute_type_id", required=true) String roomAttributeTypeId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {		

		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId(roomAttributeTypeId);		

		if(objectManagement.isRoomAttributeType(roomAttributeTypePK)){					
			objectArrayPagination.preparePage(model, 
					objectManagement.getRooms(roomAttributeTypePK), page, "rooms");		

			model.addAttribute("roomAttributeType",
					objectManagement.getRoomAttributeType(roomAttributeTypePK));
			model.addAttribute("roomAttributeTypeId",roomAttributeTypeId);			
		}else{
			model.addAttribute("isRoomAttributeTypePKIncorrect",true);		
		}		

		return "roomListByRoomAttributeType";
	}	

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String displayAllRooms(@RequestParam(value="page", required=false) Integer page
			,HttpServletRequest request,HttpServletResponse response,Model model) {			
		roomPagination.preparePage(model, objectManagement.getAllRooms(), page,"rooms");		
		return "fullRoomList";		
	}		

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createRoom(Room room, BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response,Model model) throws UnsupportedEncodingException {

		roomValidator.validateBeforeCreating(room, bindingResult);

		if(bindingResult.hasErrors()){				
			return "newRoomForm";			
		}else{				
			objectManagement.addRoom(room);			
			return "redirect:/room?campus_id="+URLEncoder.encode(room.getId().getCampusId(),"utf8")
					+"&building_id="+URLEncoder.encode(room.getId().getBuildingId(),"utf8");	
		}					
	}	

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewRoomForm(
			@RequestParam(value="campus_id", required=false) String campusId,
			@RequestParam(value="building_id", required=false) String buildingId,
			@RequestParam(value="_params", required=false) String _params,
			HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException {

		Room room=new Room();
		room.setId(new RoomPK());

		if(_params!=null){				
			room = getDecodedInstance(_params);			
		}	

		if((campusId!=null)&&(!campusId.isEmpty())){
			room.getId().setCampusId(campusId);
		}
		if((buildingId!=null)&&(!buildingId.isEmpty())){
			room.getId().setBuildingId(buildingId);
		}

		model.addAttribute("room",room);						
		return "newRoomForm";		
	}

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String deleteRoom(Room room, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		objectManagement.deleteRoom(room.getId());				
		return "redirect:/room?campus_id="+URLEncoder.encode(room.getId().getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(room.getId().getBuildingId(),"utf8");
	}

	@RequestMapping(value="/manage",params = "update", method = RequestMethod.POST)
	public String updateRoom(Room room, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		roomValidator.validate(room, bindingResult);

		if(bindingResult.hasErrors()){				
			return "roomManagement";			
		}else{			
			objectManagement.modifyRoom(room);		
			return "redirect:/room?campus_id="+URLEncoder.encode(room.getId().getCampusId(),"utf8")
					+"&building_id="+URLEncoder.encode(room.getId().getBuildingId(),"utf8");	
		}			
	}	

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayRoomDetails(@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId(campusId);
		roomPK.setBuildingId(buildingId);
		roomPK.setRoomId(roomId);				

		if(objectManagement.isRoom(roomPK)){			
			model.addAttribute("room",objectManagement.getRoom(roomPK));			

			BookableRoomPK bookableRoomPK=new BookableRoomPK();
			bookableRoomPK.setCampusId(campusId);
			bookableRoomPK.setBuildingId(buildingId);
			bookableRoomPK.setRoomId(roomId);			

			if(objectManagement.isBookableRoom(bookableRoomPK)){
				model.addAttribute("isRoomBookable",true);
				model.addAttribute("bookableRoom",objectManagement.getBookableRoom(bookableRoomPK));	
			}else{
				model.addAttribute("isRoomBookable",false);

				BookableRoom bookableRoom=new BookableRoom();				
				bookableRoom.setIsRobot(true);
				bookableRoom.setId(bookableRoomPK);

				model.addAttribute("bookableRoom",bookableRoom);
			}			
		}else{
			model.addAttribute("isRoomPKIncorrect",true);
		}		

		return "roomManagement";
	}		 

	@RequestMapping(value="/bookable/new",params = "create", method = RequestMethod.POST)
	public String createBookableRoom(BookableRoom bookableRoom,HttpServletRequest request,
			HttpServletResponse response,Model model) throws UnsupportedEncodingException {

		objectManagement.createBookableRoom(bookableRoom);			
		return "redirect:/room/manage?campus_id="
		+URLEncoder.encode(bookableRoom.getId().getCampusId(),"utf8")
		+"&building_id="+URLEncoder.encode(bookableRoom.getId().getBuildingId(),"utf8")
		+"&room_id="+URLEncoder.encode(bookableRoom.getId().getRoomId(),"utf8");

	}	

	@RequestMapping(value="/bookable/manage",params = "update", method = RequestMethod.POST)
	public String updateBookableRoom(BookableRoom bookableRoom,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		objectManagement.updateBookableRoom(bookableRoom);		
		return "redirect:/room/manage?campus_id="
		+URLEncoder.encode(bookableRoom.getId().getCampusId(),"utf8")
		+"&building_id="+URLEncoder.encode(bookableRoom.getId().getBuildingId(),"utf8")
		+"&room_id="+URLEncoder.encode(bookableRoom.getId().getRoomId(),"utf8");

	}	

	@RequestMapping(value="/bookable/manage",params = "delete", method = RequestMethod.POST)
	public String deleteBookableRoom(BookableRoom bookableRoom,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		objectManagement.deleteBookableRoom(bookableRoom.getId());				
		return "redirect:/room/manage?campus_id="
		+URLEncoder.encode(bookableRoom.getId().getCampusId(),"utf8")
		+"&building_id="+URLEncoder.encode(bookableRoom.getId().getBuildingId(),"utf8")
		+"&room_id="+URLEncoder.encode(bookableRoom.getId().getRoomId(),"utf8");
	}	
}

