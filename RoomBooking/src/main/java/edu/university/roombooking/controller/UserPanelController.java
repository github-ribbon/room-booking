package edu.university.roombooking.controller;

import java.security.Principal;

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

import edu.university.roombooking.domain.Person;
import edu.university.roombooking.domain.PersonPK;
import edu.university.roombooking.domain.Usr;
import edu.university.roombooking.domain.UsrGroup;
import edu.university.roombooking.domain.UsrPK;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.PersonValidator;
import edu.university.roombooking.validation.UsrValidator;


@Controller
@RequestMapping(value="/panel")
@PreAuthorize(value="authenticated")
public class UserPanelController {

	@Autowired
	private Pagination<UsrGroup> usrGroupPagination;

	@Autowired
	private PersonValidator personValidator;

	@Autowired
	private UsrValidator usrValidator;

	@Autowired
	private GroupManagement groupManagement;	


	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));		
	}		

	@RequestMapping(method = RequestMethod.GET)
	public String displayWelcomePage(HttpServletRequest request,HttpServletResponse response) {		
		return "welcome";
	}	

	@RequestMapping(value="/support",method = RequestMethod.GET)
	public String displaySupportPage(HttpServletRequest request,HttpServletResponse response) {			
		return "support";
	}	

	@RequestMapping(value="/account-settings",method = RequestMethod.GET)
	public String displayUserAccountSettings(HttpServletRequest request,
			HttpServletResponse response,
			Model model,Principal principal) {		

		Usr usr=groupManagement.getUsr(principal.getName());
		usr.setPassword("");

		model.addAttribute("usr",usr);

		if(usr.getPersonId()!=null){				
			PersonPK personPK=new PersonPK();
			personPK.setPersonId(usr.getPersonId());					

			Person person=groupManagement.getPerson(personPK);

			if(person==null){
				person=new Person();
			}	

			model.addAttribute("person",person);
		}		

		return "accountSettings";		
	}

	@RequestMapping(value="/update-person",method = RequestMethod.POST)
	public String updateUserPerson(Person person,BindingResult result,HttpServletRequest request,
			HttpServletResponse response,Model model,Principal principal) throws Exception {

		Usr usr=groupManagement.getUsr(principal.getName());		

		personValidator.validate(person, result);

		if(result.hasErrors()){			
			usr.setPassword("");
			model.addAttribute("usr",usr);
			model.addAttribute("person",person);

			return "accountSettings";	
		}else{			
			Person personPopulated=null;

			if(usr.getPersonId()!=null){				
				PersonPK personPK=new PersonPK();
				personPK.setPersonId(usr.getPersonId());					

				personPopulated=groupManagement.getPerson(personPK);			
			}	

			if(personPopulated!=null){
				personPopulated.setGivenName(person.getGivenName());
				personPopulated.setFamilyName(person.getFamilyName());

				groupManagement.updatePerson(personPopulated);
			}else{
				groupManagement.updateUser(usr,person);				
			}

			return "redirect:/panel/account-settings?person_changed";
		}		
	}	

	@RequestMapping(value="/update-user",method = RequestMethod.POST)
	public String updateUser(Usr usr,BindingResult result,HttpServletRequest request,
			HttpServletResponse response,Principal principal,Model model) throws Exception {

		usrValidator.validateUserPassword(usr, result);		

		if(result.hasErrors()){	

			Person person=null;

			if(usr.getPersonId()!=null){				
				PersonPK personPK=new PersonPK();
				personPK.setPersonId(usr.getPersonId());					

				person=groupManagement.getPerson(personPK);			
			}		

			if(person==null){
				person=new Person();
			}

			model.addAttribute("person",person);			
			model.addAttribute("usr",usr);

			return "accountSettings";	
		}else{			
			Usr userPopulated=groupManagement.getUsr(principal.getName());				
			String password=GroupManagement.getEncryptedPassword(usr.getPassword());			
			userPopulated.setPassword(password);			

			groupManagement.updateUsr(userPopulated);
			return "redirect:/panel/account-settings?password_changed";	
		}		
	}	

	@RequestMapping(value="/my-user-groups",method = RequestMethod.GET)
	public String displayMyUserGroups(HttpServletRequest request,HttpServletResponse response,
			Model model,Principal principal) {	

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());	

		model.addAttribute("usrGroups",groupManagement.getMyUserGroups(usrPK));		
		return "myUserGroups";		
	}	
}

