package edu.university.roombooking.validation.abstraction;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public interface CustomisedValidator extends Validator {	
	public abstract void validateBeforeCreating(Object object,Errors errors);	
}
