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

import edu.university.roombooking.domain.PersonGroup;
import edu.university.roombooking.domain.PersonGroupPK;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.PersonGroupValidator;


@Controller
@RequestMapping(value="/person-group")
@PreAuthorize(value="hasRole('ROLE_GROUP')")
public class PersonGroupController{

	@Autowired
	private PersonGroupValidator personGroupValidator;	

	@Autowired
	private Pagination<PersonGroup> personGroupPagination;	

	@Autowired
	private GroupManagement groupManagement;	

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}	

	@RequestMapping(method = RequestMethod.GET)
	public String displayAllPersonGroups(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model){					
		personGroupPagination.preparePage(model,
				groupManagement.getAllPersonGroups(), page,"personGroups");		
		return "personGroupList";
	}		

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createPersonGroup(PersonGroup personGroup, BindingResult result,
			HttpServletRequest request,HttpServletResponse response) {

		personGroupValidator.validate(personGroup, result);

		if (result.hasErrors()) {				
			return "newPersonGroupForm";			
		} else {			
			groupManagement.createPersonGroup(personGroup);			
			return "redirect:/person-group";
		}		
	}

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewPersonGroupForm(
			HttpServletRequest request,HttpServletResponse response,Model model) {						
		model.addAttribute("personGroup",new PersonGroup());					
		return "newPersonGroupForm";		
	}	

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String deletePersonGroup(PersonGroup personGroup, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response){				
		groupManagement.deletePersonGroup(personGroup.getId());		
		return "redirect:/person-group";
	}

	@RequestMapping(value="/manage",params = "update", method = RequestMethod.POST)
	public String updatePersonGroup(PersonGroup personGroup, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response){

		personGroupValidator.validate(personGroup, bindingResult);

		if (bindingResult.hasErrors()) {				
			return "personGroupManagement";			
		} else {			
			groupManagement.updatePersonGroup(personGroup);			
			return "redirect:/person-group";
		}		
	}

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayPersonGroupDetails(
			@RequestParam(value="person_group_id", required=true) String personGroupId,			
			HttpServletRequest request,HttpServletResponse response,Model model) {

		PersonGroupPK personGroupPK=new PersonGroupPK();			
		personGroupPK.setPersonGroupId(personGroupId);			

		if(groupManagement.isPersonGroup(personGroupPK)){
			model.addAttribute("personGroup",groupManagement.getPersonGroup(personGroupPK));						
		}else{
			model.addAttribute("isPersonGroupPKIncorrect",true);
		}

		return "personGroupManagement";		
	}	
}
