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

import edu.university.roombooking.domain.Person;
import edu.university.roombooking.domain.PersonGroupPK;
import edu.university.roombooking.domain.PersonGroupPerson;
import edu.university.roombooking.domain.PersonGroupPersonPK;
import edu.university.roombooking.domain.PersonPK;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.PersonGroupPersonValidator;


@Controller
@RequestMapping(value="/person-group-person")
@PreAuthorize(value="hasRole('ROLE_GROUP')")
public class PersonGroupPersonController{

	@Autowired
	private PersonGroupPersonValidator personGroupPersonValidator;

	@Autowired
	private Pagination<PersonGroupPerson> personGroupPersonPagination;

	@Autowired
	private GroupManagement groupManagement;


	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayAllPersonGroupPersons(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model){				
		personGroupPersonPagination.preparePage(model, 
				groupManagement.getAllAssignedPersonsToPersonGroup(), page,"personGroupPersons");		
		return "fullPersonGroupPersonList";
	}

	@RequestMapping(value="/by-person-group",method = RequestMethod.GET)
	public String displayPersonsByPersonGroup(
			@RequestParam(value="person_group_id", required=true) String personGroupId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {		

		PersonGroupPK personGroupPK=new PersonGroupPK();	
		personGroupPK.setPersonGroupId(personGroupId);

		if(groupManagement.isPersonGroup(personGroupPK)){						
			personGroupPersonPagination.preparePage(model, 
					groupManagement.getAssignedPersonsToPersonGroup(personGroupPK), 
					page,"personGroupPersons");				
			model.addAttribute("personGroupId",personGroupId);			
		}else{
			model.addAttribute("isPersonGroupPKIncorrect",true);			
		}

		return "personGroupPersonList";			
	}	

	@RequestMapping(value="/by-person",method = RequestMethod.GET)
	public String displayPersonGroupsByPerson(
			@RequestParam(value="person_id", required=true) int personId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {		

		PersonPK personPK=new PersonPK();		
		personPK.setPersonId(personId);

		if(groupManagement.isPerson(personPK)){							
			personGroupPersonPagination.preparePage(model,
					groupManagement.getAssignedPersonsToPersonGroup(personPK),
					page,"personGroupPersons");				
			model.addAttribute("personId",personId);			
		}else{
			model.addAttribute("isPersonPKIncorrect",true);			
		}		

		return "personGroupPersonByPersonList";			
	}	

	@RequestMapping(value="/new",params = "choose_person", method = RequestMethod.POST)
	public String choosePerson(PersonGroupPerson personGroupPerson,BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String personGroupId=personGroupPerson.getId().getPersonGroupId();			

		return "redirect:/choose/persons?source=person_group_person&person_group_id="
		+URLEncoder.encode(personGroupId,"utf8")+"&_params=";		
	}

	@RequestMapping(value="/new",params = "choose_person_group", method = RequestMethod.POST)
	public String choosePersonGroup(PersonGroupPerson personGroupPerson,BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String personId;		

		try{		
			personId=String.valueOf(personGroupPerson.getId().getPersonId());
		}			
		catch(NumberFormatException e){
			personId="";
		}		

		return "redirect:/choose/person-groups?source=person_group_person&person_id="
		+personId+"&_params=";		
	}	

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createPersonGroupPerson(PersonGroupPerson personGroupPerson, BindingResult result,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		personGroupPersonValidator.validate(personGroupPerson, result);

		if (result.hasErrors()) {		
			PersonPK personPK=new PersonPK();
			personPK.setPersonId(personGroupPerson.getId().getPersonId());

			if(groupManagement.isPerson(personPK)){
				Person person=groupManagement.getPerson(personPK);					
				request.setAttribute("person",person.getGivenName()+" "+person.getFamilyName());
			}							

			return "newPersonGroupPersonForm";			
		} else {			
			groupManagement.assignPersonToPersonGroup(personGroupPerson);			
			return "redirect:/person-group-person/by-person-group?person_group_id="
			+URLEncoder.encode(personGroupPerson.getId().getPersonGroupId(),"utf8");
		}		
	}

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewPersonGroupPersonForm(
			@RequestParam(value="person_id", required=false) Integer personId,
			@RequestParam(value="person_group_id", required=false) String personGroupId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		PersonGroupPerson personGroupPerson=new PersonGroupPerson();		
		personGroupPerson.setId(new PersonGroupPersonPK());		

		if(personId!=null){
			PersonPK personPK=new PersonPK();
			personPK.setPersonId(personId);

			if(groupManagement.isPerson(personPK)){
				Person person=groupManagement.getPerson(personPK);
				personGroupPerson.getId().setPersonId(personId);

				model.addAttribute("person",person.getGivenName()+" "+person.getFamilyName());
			}
		}

		if((personGroupId!=null)&&(!personGroupId.isEmpty())){			
			PersonGroupPK personGroupPK=new PersonGroupPK();
			personGroupPK.setPersonGroupId(personGroupId);

			if(groupManagement.isPersonGroup(personGroupPK)){					
				personGroupPerson.getId().setPersonGroupId(personGroupId);				
			}						
		}

		model.addAttribute("personGroupPerson", personGroupPerson);	

		return "newPersonGroupPersonForm";		
	}	

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayPersonGroupPersonDetails(
			@RequestParam(value="person_group_id", required=true) String personGroupId,
			@RequestParam(value="person_id", required=true) int personId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		PersonGroupPersonPK personGroupPersonPK=new PersonGroupPersonPK();
		personGroupPersonPK.setPersonId(personId);
		personGroupPersonPK.setPersonGroupId(personGroupId);

		if(groupManagement.isPersonAssignedToPersonGroup(personGroupPersonPK)){
			model.addAttribute("personGroupPerson",
					groupManagement.getAssignedPersonToPersonGroup(personGroupPersonPK));			

			PersonPK personPK=new PersonPK();
			personPK.setPersonId(personId);			

			Person person=groupManagement.getPerson(personPK);			
			model.addAttribute("person",person.getGivenName()+" "+person.getFamilyName());						
		}else{
			model.addAttribute("isPersonGroupPersonPKIncorrect",true);
		}			

		return "personGroupPersonManagement";
	}	

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String deletePersonFromPersonGroup(
			PersonGroupPerson personGroupPerson, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response){				
		groupManagement.deletePersonFromPersonGroup(personGroupPerson.getId());		
		return "redirect:/person-group-person/by-person-group?person_group_id="
		+personGroupPerson.getId().getPersonGroupId();
	}
}
