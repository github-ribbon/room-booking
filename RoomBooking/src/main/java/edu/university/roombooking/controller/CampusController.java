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

import edu.university.roombooking.domain.Campus;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.CampusValidator;



@Controller
@RequestMapping(value="/campus")
@PreAuthorize(value="hasRole('ROLE_ADMIN')")
public class CampusController{

	@Autowired
	private Pagination<Campus> campusPagination;	

	@Autowired
	private CampusValidator campusValidator;

	@Autowired
	private ObjectManagement objectManagement;	

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}	

	@RequestMapping(method = RequestMethod.GET)
	public String displayAllCampuses(@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model){

		campusPagination.preparePage(model, objectManagement.getAllCampuses(), page,"campuses");		
		return "campusList";
	}	

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String createCampus(Campus campus, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response) {

		campusValidator.validateBeforeCreating(campus, bindingResult);

		if(bindingResult.hasErrors()){
			return "newCampusForm";
		}else{			
			objectManagement.addCampus(campus);
			return "redirect:/campus";
		}			
	}	

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewCampusForm(HttpServletRequest request,
			HttpServletResponse response,Model model) {

		model.addAttribute("campus",new Campus());			
		return "newCampusForm";		
	}		

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String deleteCampus(Campus campus,HttpServletRequest request,
			HttpServletResponse response,Model model){

		objectManagement.deleteCampus(campus.getId());						
		return "redirect:/campus";
	}	

	@RequestMapping(value="/manage",params = "update", method = RequestMethod.POST)
	public String updateCampus(Campus campus, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response){

		campusValidator.validate(campus, bindingResult);

		if(bindingResult.hasErrors()){
			return "campusManagement";
		}else{			
			objectManagement.modifyCampus(campus);			
			return "redirect:/campus";			
		}		
	}	

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayCampusDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId(campusId);			

		if(objectManagement.isCampus(campusPK)){			
			model.addAttribute("campus", objectManagement.getCampus(campusPK));
		}else{
			model.addAttribute("isCampusPKIncorrect",true);
		}		

		return "campusManagement";
	} 	
}
