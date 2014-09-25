package edu.university.roombooking.controller;

import java.io.IOException;
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

import edu.university.roombooking.domain.Person;
import edu.university.roombooking.domain.PersonPK;
import edu.university.roombooking.domain.Usr;
import edu.university.roombooking.domain.UsrPK;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.util.Decodable;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.UsrValidator;

@Controller
@RequestMapping(value="/usr")
@PreAuthorize(value="hasRole('ROLE_GROUP')")
public class UsrController implements Decodable<Usr>{

	@Autowired
	private UsrValidator usrValidator;	

	@Autowired
	private Pagination<Usr> usrPagination;

	@Autowired
	private GroupManagement groupManagement;


	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}	

	public Usr getDecodedInstance(String encodedString) throws UnsupportedEncodingException{

		Usr usr=new Usr();
		usr.setId(new UsrPK());

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

			if(name.equals("usr_id")){		        	
				usr.getId().setUsrId(value);		        	
			}else if(name.equals("person_id")){
				try{
					usr.setPersonId(Integer.decode(value));
				}catch(NumberFormatException e){
					usr.setPersonId(null);
				}
			} 		        	        
		}

		return usr;	
	}	

	@RequestMapping(value="/new",params = "choose_person", method = RequestMethod.POST)
	public String choosePerson(Usr usr,BindingResult bindingResult,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String personId;

		try{		
			personId=usr.getPersonId().toString();
		}catch(NullPointerException e){
			personId="";
		}

		String usrId=usr.getId().getUsrId();				
		String _params="usr_id="+URLEncoder.encode(usrId,"utf8")
				+"&person_id="+URLEncoder.encode(personId,"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/persons?source=usr&_params="+_params;		
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayAllUsers(@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model){			
		usrPagination.preparePage(model, groupManagement.getAllUsers(), page,"users");		
		return "usrList";
	}

	@RequestMapping(value="/new",params = "delete_person", method = RequestMethod.POST)
	public String deletePersonFromNewUsrForm(Usr usr, BindingResult result,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		usr.setPersonId(null);			
		model.addAttribute("usr", usr);

		return "newUsrForm";		
	}

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createUser(Usr usr, BindingResult result,HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		usrValidator.validate(usr, result);

		if(result.hasErrors()) {			
			PersonPK personPK=new PersonPK();

			try{
				personPK.setPersonId(usr.getPersonId().intValue());				
				Person pers=groupManagement.getPerson(personPK);				
				String person=pers.getGivenName()+" "+pers.getFamilyName();				
				request.setAttribute("person",person);				
			}catch(NullPointerException e){				
			}			

			return "newUsrForm";			
		}else {				
			groupManagement.createUser(usr);			
			return "redirect:/usr";
		}		
	}

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewUserForm(@RequestParam(value="person_id", required=false) Integer personId,
			@RequestParam(value="_params", required=false) String _params,
			HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException {

		Usr usr=new Usr();	

		if(_params!=null){			
			usr=getDecodedInstance(_params);		
		}

		if(personId!=null){
			usr.setPersonId(personId);

			PersonPK personPK=new PersonPK();
			personPK.setPersonId(personId);

			Person person=groupManagement.getPerson(personPK);

			if(person!=null){					
				usr.setPersonId(person.getPersonId());					

				String personDetails=person.getGivenName()+" "+person.getFamilyName();					
				model.addAttribute("person", personDetails);
			}			
		}

		model.addAttribute("usr",usr);			
		return "newUsrForm";		
	}

	@RequestMapping(value="/manage",params = "disable", method = RequestMethod.POST)
	public String disableUserAccount(Usr usr,BindingResult result,HttpServletRequest request,
			HttpServletResponse response) throws IOException{			

		groupManagement.disableUserAccount(usr.getId());		
		return "redirect:/usr";		
	}	

	@RequestMapping(value="/manage",params = "enable", method = RequestMethod.POST)
	public String enableUserAccount(Usr usr,BindingResult result,HttpServletRequest request,
			HttpServletResponse response) throws IOException{			

		groupManagement.enableUserAccount(usr.getId());				
		return "redirect:/usr";		
	}		

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayUserDetails(@RequestParam(value="usr_id", required=true) String usrId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		UsrPK usrPK=new UsrPK();			
		usrPK.setUsrId(usrId);		

		if(groupManagement.isUsr(usrPK)){
			Usr usr=groupManagement.getUsr(usrPK);			
			model.addAttribute("usr",usr);

			if(usr.getPersonId()!=null){				
				PersonPK personPK=new PersonPK();
				personPK.setPersonId(usr.getPersonId());					

				Person person=groupManagement.getPerson(personPK);

				if(person!=null){					
					String personDetails=person.getGivenName()+" "+person.getFamilyName();					
					model.addAttribute("person", personDetails);
				}		
			}	
		}else{
			model.addAttribute("isUsrPKIncorrect",true);
		}				

		return "usrManagement";
	}	
}

