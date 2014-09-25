package edu.university.roombooking.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerController{	

	@ExceptionHandler(DataIntegrityViolationException.class)
	public String handleDataIntegrityViolationException(HttpServletRequest request,
			DataIntegrityViolationException e) {		 
		e.printStackTrace();		
		return "errors/dataIntegrityViolationException";
	}

	@ExceptionHandler(DataAccessException.class)
	public String handleDataAccessException(HttpServletRequest request,
			DataAccessException e) {		  
		e.printStackTrace();		
		return "errors/dataAccessException";
	}

	@ExceptionHandler(MailSendException.class)
	public String handleMailSendException(HttpServletRequest request,
			MailSendException e) {		
		e.printStackTrace();
		return "errors/mailSendException";
	}
}
