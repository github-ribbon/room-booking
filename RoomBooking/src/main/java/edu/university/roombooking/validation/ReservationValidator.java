package edu.university.roombooking.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.university.roombooking.domain.BookableRoomPK;
import edu.university.roombooking.domain.PersonGroupPK;
import edu.university.roombooking.domain.Reservation;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.service.RoomBooking;


@Component
public class ReservationValidator implements Validator {

	@Autowired
	private GroupManagement groupManagement;

	@Autowired
	private RoomBooking roomBooking;	

	public boolean supports(Class<?> clazz) {
		return Reservation.class.equals(clazz);
	}	

	public void validate(Object object,Errors errors){

		Reservation reservation=(Reservation) object;

		try{
			reservation.getReservationDate().toString();
		}catch(NullPointerException nullPointerException){		
			errors.rejectValue("reservationDate", "required.reservationDate");
		}				

		try{
			reservation.getTimeFrom().toString();
		}catch(NullPointerException nullPointerException){	
			errors.rejectValue("timeFrom", "required.timeFrom");
		}	

		try{
			reservation.getTimeTo().toString();
		}catch(NullPointerException nullPointerException){	
			errors.rejectValue("timeTo", "required.timeTo");
		}		

		try{			

			Date timeFrom=reservation.getTimeFrom();
			Date timeTo=reservation.getTimeTo();

			SimpleDateFormat timeFormat=new SimpleDateFormat("H:mm");

			Date hour7=timeFormat.parse("07:00");
			Date hour22=timeFormat.parse("22:00");

			if(timeFrom.before(hour7)||timeFrom.after(hour22)){
				errors.rejectValue("timeFrom", "incorrect.hours");
			}

			if(timeTo.before(hour7)||timeTo.after(hour22)){
				errors.rejectValue("timeTo", "incorrect.hours");
			}

			if(timeFrom.after(timeTo)||(timeFrom.equals(timeTo))){
				errors.rejectValue("timeFrom", "incorrect.interval");
			}			

			//			Setting correct timeFrom and timeTo

			Calendar calendar=Calendar.getInstance();
			calendar.setTime(reservation.getReservationDate());

			int year=calendar.get(Calendar.YEAR);
			int month=calendar.get(Calendar.MONTH);
			int day=calendar.get(Calendar.DAY_OF_MONTH);

			calendar.setTime(reservation.getTimeFrom());
			calendar.set(year, month, day);			
			reservation.setTimeFrom(calendar.getTime());

			calendar.setTime(reservation.getTimeTo());
			calendar.set(year, month, day);
			reservation.setTimeTo(calendar.getTime());

			//			

			if(ReservationValidator.isGivenDatePast(reservation.getTimeFrom())){
				errors.rejectValue("reservationDate", "pastDate");
			}

		} catch(NullPointerException nullPointerException){			
		} catch (ParseException e) {
		}			

		BookableRoomPK bookableRoomPK=new BookableRoomPK();
		bookableRoomPK.setCampusId(reservation.getCampusId());
		bookableRoomPK.setBuildingId(reservation.getBuildingId());
		bookableRoomPK.setRoomId(reservation.getRoomId());

		if(!roomBooking.isBookableRoom(bookableRoomPK)){
			errors.rejectValue("roomId", "required.bookableRoom");
		}

		PersonGroupPK personGroupPK=new PersonGroupPK();
		personGroupPK.setPersonGroupId(reservation.getPersonGroupId());

		if(!groupManagement.isPersonGroup(personGroupPK)){
			errors.rejectValue("personGroupId", "required.personGroup");
		}		
	}	

	public static boolean isToday(Date date){

		Calendar calendar=Calendar.getInstance();			
		calendar.setTime(new Date());

		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		int day=calendar.get(Calendar.DAY_OF_MONTH);

		Calendar currentCalendar=Calendar.getInstance();
		currentCalendar.set(year, month, day);
		Date currentDate=currentCalendar.getTime();

		calendar.setTime(date);

		year=calendar.get(Calendar.YEAR);
		month=calendar.get(Calendar.MONTH);
		day=calendar.get(Calendar.DAY_OF_MONTH);

		Calendar reservationCalendar=Calendar.getInstance();
		reservationCalendar.set(year, month, day);
		date=reservationCalendar.getTime();					


		if((!date.before(currentDate))&&(!date.after(currentDate))){
			return true;					
		}else
		{
			return false;
		}	
	}


	/**
	 * Checks whether given date is past.
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isGivenDatePast(Date date){

		Date currentDate=new Date();

		if(date.before(currentDate)){
			return true;					
		}else{
			return false;
		}		
	}


	public static boolean isDateParseable(String dateInString){

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	

		try{
			dateFormat.parse(dateInString);
			return true;
		}

		catch (ParseException e) {
			return false;
		}
	}

	public static Date getParsedDate(String dateInString){

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	

		try {
			return dateFormat.parse(dateInString);
		} catch (ParseException e) {
			return null;
		}		
	}		
}
