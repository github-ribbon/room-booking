package edu.university.roombooking.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.university.roombooking.service.GroupManagement;


@Controller
public class HomeController {

	@Autowired
	private GroupManagement groupManagement;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request,HttpServletResponse response, Model model) {	

		return "redirect:/panel";		
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLoginPage(@RequestParam(value="failure", required=false) String failure,
			@RequestParam(value="logged-out", required=false) String logout,
			HttpServletRequest request,HttpServletResponse response,Model model) {	

		if(failure!=null){
			model.addAttribute("isAuthenticationError",true);
		}

		if(logout!=null){
			model.addAttribute("isLogout",true);
		}

		return "loginPage";		
	}	

	@RequestMapping(value = "/forgotten-password", method = RequestMethod.GET)
	public String displayForgottenPasswordForm(HttpServletRequest request,
			HttpServletResponse response) {		
		return "forgottenPassword";		
	} 

	@RequestMapping(value="/forgotten-password",method = RequestMethod.POST)
	public String resetPassword(HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		String login=request.getParameter("login");
		String email=request.getParameter("email");		

		if(groupManagement.isUser(login, email)){				
			groupManagement.resetPassword(login,email);
			return "redirect:/forgotten-password/success";			
		}else{
			return "redirect:/forgotten-password/failure?login="+URLEncoder.encode(login,"utf8")+
					"&email="+URLEncoder.encode(email,"utf8");	
		}			
	}	

	@RequestMapping(value="/forgotten-password/success",method = RequestMethod.GET)
	public String resettingPasswordSuccess(HttpServletRequest request,
			HttpServletResponse response,Model model){				
		model.addAttribute("isSuccess",true);
		return "forgottenPassword";			
	}

	@RequestMapping(value="/forgotten-password/failure",method = RequestMethod.GET)
	public String resettingPasswordFailure(@RequestParam(value="login", required=false) String login,
			@RequestParam(value="email", required=false) String email,HttpServletRequest request,
			HttpServletResponse response,Model model){

		model.addAttribute("login",login);
		model.addAttribute("email", email);
		model.addAttribute("isFailure",true);

		return "forgottenPassword";			
	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String accessDenied(HttpServletRequest request,
			HttpServletResponse response,Model model){			
		return "errors/accessDenied";		
	}	
}
