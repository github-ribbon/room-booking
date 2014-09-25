package edu.university.roombooking.controller;

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

import edu.university.roombooking.domain.RoomAttributeType;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.RoomAttributeTypeValidator;


@Controller
@RequestMapping(value="/room-attribute-type")
@PreAuthorize(value="hasRole('ROLE_ADMIN')")
public class RoomAttributeTypeController {

	@Autowired
	private Pagination<RoomAttributeType> roomAttributeTypePagination;		

	@Autowired
	private RoomAttributeTypeValidator roomAttributeTypeValidator;	

	@Autowired
	private ObjectManagement objectManagement;	


	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayAllRoomAttributeTypes(@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {		
		roomAttributeTypePagination.preparePage(model, 
				objectManagement.getAllRoomAttributeTypes(), page,"roomAttributeTypes");		
		return "roomAttributeTypeList";
	}		

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createRoomAttributeType(RoomAttributeType roomAttributeType, 
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {

		roomAttributeTypeValidator.validateBeforeCreating(roomAttributeType, bindingResult);

		if(bindingResult.hasErrors()){			
			return "newRoomAttributeTypeForm";					
		}else{			
			objectManagement.addRoomAttributeType(roomAttributeType);			
			return "redirect:/room-attribute-type";			
		}			
	}		

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewRoomAttributeTypeForm(HttpServletRequest request,
			HttpServletResponse response,Model model) {			
		model.addAttribute("roomAttributeType",new RoomAttributeType());			
		return "newRoomAttributeTypeForm";		
	}		

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String deleteRoomAttributeType(RoomAttributeType roomAttributeType,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {		
		objectManagement.deleteRoomAttributeType(roomAttributeType.getId());			
		return "redirect:/room-attribute-type";		
	}	

	@RequestMapping(value="/manage",params = "update", method = RequestMethod.POST)
	public String updateRoomAttributeType(RoomAttributeType roomAttributeType,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response){

		roomAttributeTypeValidator.validate(roomAttributeType, bindingResult);

		if(bindingResult.hasErrors()){			
			return "roomAttributeTypeManagement";			
		}else{			
			objectManagement.modifyRoomAttributeType(roomAttributeType);		
			return "redirect:/room-attribute-type";			
		}		
	}	

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayRoomAttributeTypeDetails(
			@RequestParam(value="room_attribute_type_id", required=true) String roomAttributeTypeId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId(roomAttributeTypeId);				

		if(objectManagement.isRoomAttributeType(roomAttributeTypePK)){			
			model.addAttribute("roomAttributeType",
					objectManagement.getRoomAttributeType(roomAttributeTypePK));			
		}else{			
			model.addAttribute("isRoomAttributeTypePKIncorrect",true);			
		}		

		return "roomAttributeTypeManagement";
	}	
}
