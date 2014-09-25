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

import edu.university.roombooking.domain.UsrGroup;
import edu.university.roombooking.domain.UsrGroupPK;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.UsrGroupValidator;


@Controller
@RequestMapping(value="/usr-group")
@PreAuthorize(value="hasRole('ROLE_GROUP')")
public class UsrGroupController{

	@Autowired
	private UsrGroupValidator usrGroupValidator;	

	@Autowired
	private Pagination<UsrGroup> usrGroupPagination;

	@Autowired
	private GroupManagement groupManagement;	

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}	

	@RequestMapping(method = RequestMethod.GET)
	public String displayAllUserGroups(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model){			
		usrGroupPagination.preparePage(model, 
				groupManagement.getAllUserGroups(), page,"usrGroups");			
		return "usrGroupList";
	}	

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createUserGroup(UsrGroup usrGroup, BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {

		usrGroupValidator.validate(usrGroup, bindingResult);

		if (bindingResult.hasErrors()){				
			return "newUsrGroupForm";			
		} else {			
			groupManagement.createUserGroup(usrGroup);			
			return "redirect:/usr-group";
		}		
	}

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewUserGroupForm(
			HttpServletRequest request,HttpServletResponse response,Model model) {								
		model.addAttribute("usrGroup",new UsrGroup());							
		return "newUsrGroupForm";		
	}

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String deleteUserGroup(UsrGroup usrGroup, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response){				
		groupManagement.deleteUserGroup(usrGroup.getId());		
		return "redirect:/usr-group";
	}

	@RequestMapping(value="/manage",params = "update", method = RequestMethod.POST)
	public String updateUserGroup(UsrGroup usrGroup, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response){

		usrGroupValidator.validate(usrGroup, bindingResult);

		if(bindingResult.hasErrors()){			
			return "usrGroupManagement";			
		}else{			
			groupManagement.updateUserGroup(usrGroup);
			return "redirect:/usr-group";
		}		
	}

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayUserGroupDetails(
			@RequestParam(value="usr_group_id", required=true) String usrGroupId,
			HttpServletRequest request,HttpServletResponse response,Model model){

		UsrGroupPK usrGroupPK=new UsrGroupPK();
		usrGroupPK.setUsrGroupId(usrGroupId);			

		if(groupManagement.isUsrGroup(usrGroupPK)){
			model.addAttribute("usrGroup",groupManagement.getUsrGroup(usrGroupPK));					
		}else{
			model.addAttribute("isUsrGroupPKIncorrect",true);		
		}

		return "usrGroupManagement";
	}	
}
