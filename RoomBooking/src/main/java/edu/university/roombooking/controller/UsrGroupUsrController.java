package edu.university.roombooking.controller;

import java.io.UnsupportedEncodingException;
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

import edu.university.roombooking.domain.UsrGroupPK;
import edu.university.roombooking.domain.UsrGroupUsr;
import edu.university.roombooking.domain.UsrGroupUsrPK;
import edu.university.roombooking.domain.UsrPK;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.UsrGroupUsrValidator;



@Controller
@RequestMapping(value="/usr-group-usr")
@PreAuthorize(value="hasRole('ROLE_GROUP')")
public class UsrGroupUsrController{

	@Autowired
	private UsrGroupUsrValidator usrGroupUsrValidator;

	@Autowired
	private Pagination<UsrGroupUsr> usrGroupUsrPagination;

	@Autowired
	private GroupManagement groupManagement;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayAllUserGroupUsers(@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model){				
		usrGroupUsrPagination.preparePage(model, 
				groupManagement.getAllUsersAssignedToUserGroup(), page, "usrGroupUsers");		
		return "fullUsrGroupUsrList";
	}	

	@RequestMapping(value="/by-usr-group",method = RequestMethod.GET)
	public String displayUsersByUserGroup(
			@RequestParam(value="usr_group_id", required=true) String usrGroupId,
			@RequestParam(value="page", required=false) Integer page
			,HttpServletRequest request,HttpServletResponse response,Model model) {		

		UsrGroupPK usrGroupPK=new UsrGroupPK();		
		usrGroupPK.setUsrGroupId(usrGroupId);

		if(groupManagement.isUsrGroup(usrGroupPK)){				
			usrGroupUsrPagination.preparePage(model,
					groupManagement.getAssignedUsersToUserGroup(usrGroupPK), page,"usrGroupUsers");				
			model.addAttribute("usrGroupId",usrGroupId);			
		}else{
			model.addAttribute("isUsrGroupPKIncorrect",true);			
		}		

		return "usrGroupUsrList";			
	}	

	@RequestMapping(value="/by-usr",method = RequestMethod.GET)
	public String displayUserGroupsByUser(@RequestParam(value="usr_id", required=true) String usrId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {		

		UsrPK usrPK=new UsrPK();		
		usrPK.setUsrId(usrId);

		if(groupManagement.isUsr(usrPK)){						
			usrGroupUsrPagination.preparePage(model, 
					groupManagement.getAssignedUsersToUserGroup(usrPK), page,"usrGroupUsers");				
			model.addAttribute("usrId",usrId);			
		}else{
			model.addAttribute("isUsrPKIncorrect",true);			
		}			

		return "usrGroupUsrByUsrList";			
	}	

	@RequestMapping(value="/new",params = "choose_usr", method = RequestMethod.POST)
	public String chooseUsr(UsrGroupUsr usrGroupUsr,BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String usrGroupId=usrGroupUsr.getId().getUsrGroupId();	

		return "redirect:/choose/users?source=usr_group_usr" +
		"&usr_group_id="+URLEncoder.encode(usrGroupId,"utf8")+"&_params=";		
	}

	@RequestMapping(value="/new",params = "choose_usr_group", method = RequestMethod.POST)
	public String chooseUsrGroup(UsrGroupUsr usrGroupUsr,BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String usrId=usrGroupUsr.getId().getUsrId();			

		return "redirect:/choose/usr-groups?source=usr_group_usr" +
		"&usr_id="+URLEncoder.encode(usrId,"utf8")+"&_params=";		
	}


	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createUserGroupUser(UsrGroupUsr usrGroupUsr, BindingResult result,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		usrGroupUsrValidator.validate(usrGroupUsr, result);

		if (result.hasErrors()) {				
			return "newUsrGroupUsrForm";			
		} else {			
			groupManagement.assignUserToUserGroup(usrGroupUsr);			
			return "redirect:/usr-group-usr/by-usr-group?usr_group_id="
			+URLEncoder.encode(usrGroupUsr.getId().getUsrGroupId(),"utf8");
		}		
	}		

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewUserGroupUserForm(
			@RequestParam(value="usr_id", required=false) String usrId,
			@RequestParam(value="usr_group_id", required=false) String usrGroupId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		UsrGroupUsr usrGroupUsr=new UsrGroupUsr();		
		usrGroupUsr.setId(new UsrGroupUsrPK());				

		if((usrId!=null)&&(!usrId.isEmpty())){	
			usrGroupUsr.getId().setUsrId(usrId);			
		}			

		if((usrGroupId!=null)&&(!usrGroupId.isEmpty())){		
			usrGroupUsr.getId().setUsrGroupId(usrGroupId);					
		}

		model.addAttribute("usrGroupUsr", usrGroupUsr);		

		return "newUsrGroupUsrForm";		
	}

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String deleteUserGroupUser(UsrGroupUsr usrGroupUsr, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{	

		groupManagement.deleteUserFromUserGroup(usrGroupUsr.getId());

		return "redirect:/usr-group-usr/by-usr-group?usr_group_id="
		+URLEncoder.encode(usrGroupUsr.getId().getUsrGroupId(),"utf8");
	}	

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayUserGroupUserDetails(
			@RequestParam(value="usr_group_id", required=true) String usrGroupId,
			@RequestParam(value="usr_id", required=true) String usrId,			
			HttpServletRequest request,HttpServletResponse response,Model model) {

		UsrGroupUsrPK usrGroupUsrPK=new UsrGroupUsrPK();
		usrGroupUsrPK.setUsrId(usrId);
		usrGroupUsrPK.setUsrGroupId(usrGroupId);

		if(groupManagement.isUserAssignedToUserGroup(usrGroupUsrPK)){
			model.addAttribute("usrGroupUsr",
					groupManagement.getAssignedUserToUserGroup(usrGroupUsrPK));
			model.addAttribute("usr",usrId);
			model.addAttribute("usrGroup",usrGroupId);
		}else{
			model.addAttribute("isUsrGroupUsrPKIncorrect",true);
		}	

		return "usrGroupUsrManagement";
	}	
}
