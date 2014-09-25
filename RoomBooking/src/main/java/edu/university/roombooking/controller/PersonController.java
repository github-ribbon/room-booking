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

import edu.university.roombooking.domain.Person;
import edu.university.roombooking.domain.PersonPK;
import edu.university.roombooking.domain.Usr;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.PersonValidator;


@Controller
@RequestMapping(value="/person")
@PreAuthorize(value="hasRole('ROLE_GROUP')")
public class PersonController{

	@Autowired
	private PersonValidator personValidator;	

	@Autowired
	private Pagination<Person> personPagination;

	@Autowired
	private GroupManagement groupManagement;	


	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}	

	@RequestMapping(method = RequestMethod.GET)
	public String displayPersons(@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model){			
		personPagination.preparePage(model, groupManagement.getAllPersons(), page,"persons");		
		return "personList";
	}	

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createPerson(Person person, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) {

		personValidator.validate(person, bindingResult);

		if (bindingResult.hasErrors()) {
			return "newPersonForm";
		} else {			
			groupManagement.addPerson(person);			
			return "redirect:/person";
		}		
	}	

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewPersonForm(HttpServletRequest request,
			HttpServletResponse response,Model model) {			
		model.addAttribute("person",new Person());			
		return "newPersonForm";		
	}	

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String deletePerson(Person person, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response){		

		PersonPK personPK=new PersonPK();
		personPK.setPersonId(person.getPersonId());

		groupManagement.deletePerson(personPK);			
		return "redirect:/person";
	}

	@RequestMapping(value="/manage",params = "update", method = RequestMethod.POST)
	public String updatePerson(Person person, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response,Model model){

		personValidator.validate(person, bindingResult);

		if(bindingResult.hasErrors()) {			
			PersonPK personPK=new PersonPK();
			personPK.setPersonId(person.getPersonId());

			if(groupManagement.isPerson(personPK)){				
				Person populated=groupManagement.getPerson(personPK);				
				Usr usr=populated.getUsr();

				if(usr!=null){			
					model.addAttribute("usrId", usr.getId().getUsrId());
				}

			}else{
				model.addAttribute("isPersonPKIncorrect",true);			
			}				

			return "personManagement";			
		}else {			
			groupManagement.updatePerson(person);			
			return "redirect:/person";
		}		
	}

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayPersonDetails(
			@RequestParam(value="person_id", required=true) int personId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		PersonPK personPK=new PersonPK();
		personPK.setPersonId(personId);

		if(groupManagement.isPerson(personPK)){				
			Person person=groupManagement.getPerson(personPK);				
			Usr usr=person.getUsr();

			if(usr!=null){			
				model.addAttribute("usrId", usr.getId().getUsrId());
			}			

			model.addAttribute("person", person);
		}else{
			model.addAttribute("isPersonPKIncorrect",true);			
		}		

		return "personManagement";
	}	
}
