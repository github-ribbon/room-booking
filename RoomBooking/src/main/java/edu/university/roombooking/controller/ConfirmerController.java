package edu.university.roombooking.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import edu.university.roombooking.domain.BookableRoomPK;
import edu.university.roombooking.domain.Building;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.Campus;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.domain.Reservation;
import edu.university.roombooking.domain.ReservationPK;
import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomAttribute;
import edu.university.roombooking.domain.RoomAttributePK;
import edu.university.roombooking.domain.RoomAttributeType;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.domain.UsrPK;
import edu.university.roombooking.service.Booking;
import edu.university.roombooking.service.BookingConfirmation;
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.util.Decodable;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.ReservationValidator;
import edu.university.roombooking.validation.RoomManagerValidator;

@Controller
@RequestMapping(value="/confirmer")
@PreAuthorize(value="hasRole('ROLE_CONFIRMER')")
public class ConfirmerController implements Decodable<Reservation> {

	@Autowired
	private RoomManagerValidator roomManagerValidator;

	@Autowired
	private Pagination<Campus> campusPagination;

	@Autowired
	private Pagination<Building> buildingPagination;

	@Autowired
	private Pagination<Room> roomPagination;

	@Autowired
	private Pagination<RoomAttribute> roomAttributePagination;

	@Autowired
	private Pagination<Object[]> objectArrayPagination;

	@Autowired
	private Pagination<RoomAttributeType> roomAttributeTypePagination;

	@Autowired
	private Pagination<Reservation> reservationPagination;

	@Autowired
	private BookingConfirmation bookingConfirmation;

	@Autowired
	@Qualifier(value="booking")
	private Booking booking;

	@Autowired
	private ObjectManagement objectManagement;

	@Autowired
	private GroupManagement groupManagement;


	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");
		SimpleDateFormat timestampFormat=new SimpleDateFormat("yyyy-MM-dd H:mm:ss");

		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		dataBinder.registerCustomEditor(Date.class, "timeFrom",new CustomDateEditor(timeFormat, true));
		dataBinder.registerCustomEditor(Date.class, "timeTo",new CustomDateEditor(timeFormat, true));
		dataBinder.registerCustomEditor(Date.class, "created",new CustomDateEditor(timestampFormat, true));
		dataBinder.registerCustomEditor(Date.class, "modified",new CustomDateEditor(timestampFormat, true));	
	}

	public Reservation getDecodedInstance(String encodedString) throws UnsupportedEncodingException{			

		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Reservation reservation=new Reservation();		
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

			if(name.equals("campus_id")) reservation.setCampusId(value);
			else if(name.equals("building_id")) reservation.setBuildingId(value);
			else if(name.equals("room_id")) reservation.setRoomId(value);
			else if(name.equals("reservation_date")){
				if(!value.isEmpty()){
					try{	
						reservation.setReservationDate(dateFormat.parse(value));
					}catch (ParseException e) {				
					}
				}
			}else if(name.equals("time_from")){
				if(!value.isEmpty()){
					try{	
						reservation.setTimeFrom(timeFormat.parse(value));
					}catch (ParseException e) {				
					}	
				}
			}else if(name.equals("time_to")){		        	
				if(!value.isEmpty()){
					try{	
						reservation.setTimeTo(timeFormat.parse(value));
					}catch (ParseException e) {				
					}
				}
			}else if(name.equals("person_group_id")){		        	
				reservation.setPersonGroupId(value);      		
			} else if(name.equals("purpose")) reservation.setPurpose(value);  		        	        
		}

		return reservation;	
	}

	@RequestMapping(value="/campus",method = RequestMethod.GET)
	public String displayManagedCampuses(@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());

		campusPagination.preparePage(model, 
				bookingConfirmation.getManagedCampuses(usrPK), page,"campuses");

		return "managedCampusList";
	}	

	@RequestMapping(value="/building",method = RequestMethod.GET)
	public String displayManagedBuildings(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="page", required=false) Integer page,HttpServletRequest request,
			HttpServletResponse response,Model model,Principal principal) {		

		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId(campusId);	

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		if(bookingConfirmation.isCampusManaged(campusPK, usrPK)){									
			buildingPagination.preparePage(model, 
					bookingConfirmation.getManagedBuildings(campusPK, usrPK), page,"buildings");

			model.addAttribute("campusId",campusId);			
		}else{
			model.addAttribute("isCampusPKIncorrect",true);			
		}		
		return "managedBuildingList";
	}	

	@RequestMapping(value="/building/",method = RequestMethod.GET)
	public String displayAllManagedBuildings(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal){		

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());	

		buildingPagination.preparePage(model, 
				bookingConfirmation.getAllManagedBuildings(usrPK), page,"buildings");

		return "fullManagedBuildingList";
	}	

	@RequestMapping(value="/room",method = RequestMethod.GET)
	public String displayManagedRooms(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {		

		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId(campusId);		
		buildingPK.setBuildingId(buildingId);

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());	

		if(bookingConfirmation.isBuildingManaged(buildingPK, usrPK)){					
			roomPagination.preparePage(model, 
					bookingConfirmation.getManagedRooms(buildingPK, usrPK), page,"rooms");

			model.addAttribute("campusId",campusId);
			model.addAttribute("buildingId",buildingId);			
		}else{
			model.addAttribute("isBuildingPKIncorrect",true);			
		}		
		return "managedRoomList";
	}

	@RequestMapping(value="/room/",method = RequestMethod.GET)
	public String displayAllManagedRooms(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {		

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		roomPagination.preparePage(model, 
				bookingConfirmation.getAllManagedRooms(usrPK), page,"rooms");

		return "fullManagedRoomList";
	}	

	@RequestMapping(value="/campus/manage",method = RequestMethod.GET)
	public String displayCampusDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId(campusId);			

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		if(bookingConfirmation.isCampusManaged(campusPK, usrPK)){		
			model.addAttribute("campus",objectManagement.getCampus(campusPK));
		}else{
			model.addAttribute("isCampusPKIncorrect",true);
		}				
		return "managedCampusManagement";
	}

	@RequestMapping(value="/building/manage",method = RequestMethod.GET)
	public String displayBuildingDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId(campusId);
		buildingPK.setBuildingId(buildingId);			

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		if(bookingConfirmation.isBuildingManaged(buildingPK, usrPK)){		
			model.addAttribute("building", objectManagement.getBuilding(buildingPK));			
		}else{
			model.addAttribute("isBuildingPKIncorrect",true);			
		}			
		return "managedBuildingManagement";
	}

	@RequestMapping(value="/room/manage",method = RequestMethod.GET)
	public String displayRoomDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId(campusId);
		roomPK.setBuildingId(buildingId);
		roomPK.setRoomId(roomId);	

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());	

		if(bookingConfirmation.isRoomManaged(roomPK, usrPK)){			
			model.addAttribute("room", objectManagement.getRoom(roomPK));
		}else{
			model.addAttribute("isRoomPKIncorrect",true);		
		}		
		return "managedRoomManagement";
	}	

	@RequestMapping(value="/room-attribute-type",method = RequestMethod.GET)
	public String displayAllRoomAttributeTypes(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());	

		roomAttributeTypePagination.preparePage(model,
				bookingConfirmation.getAllManagedRoomAttributeTypes(usrPK), page,"roomAttributeTypes"); 

		return "managedRoomAttributeTypeList";
	}

	@RequestMapping(value="/room-attribute-type/manage",method = RequestMethod.GET)
	public String displayRoomAttributeTypeDetails(
			@RequestParam(value="room_attribute_type_id", required=true) String roomAttributeTypeId,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId(roomAttributeTypeId);	

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());	

		if(bookingConfirmation.isRoomAttributeTypeManaged(roomAttributeTypePK,usrPK)){					
			model.addAttribute("roomAttributeType",
					objectManagement.getRoomAttributeType(roomAttributeTypePK));		
		}else{
			model.addAttribute("isRoomAttributeTypePKIncorrect",true);
		}		
		return "managedRoomAttributeTypeManagement";		
	}

	@RequestMapping(value="/room/by-room-attribute-type",method = RequestMethod.GET)
	public String displayRoomsByRoomAttributeType(
			@RequestParam(value="room_attribute_type_id", required=true) String roomAttributeTypeId,
			@RequestParam(value="page", required=false) Integer page,HttpServletRequest request,
			HttpServletResponse response,Model model,Principal principal) {		

		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId(roomAttributeTypeId);		

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());	

		if(bookingConfirmation.isRoomAttributeTypeManaged(roomAttributeTypePK,usrPK)){				
			objectArrayPagination.preparePage(model,
					bookingConfirmation.getManagedRooms(roomAttributeTypePK,usrPK), page, "rooms");				

			model.addAttribute("roomAttributeType",
					objectManagement.getRoomAttributeType(roomAttributeTypePK));
			model.addAttribute("roomAttributeTypeId",roomAttributeTypeId);	
		}else{
			model.addAttribute("isRoomAttributeTypePKIncorrect",true);		
		}	

		return "managedRoomListByRoomAttributeType";
	}	

	@RequestMapping(value="/room-attribute",method = RequestMethod.GET)
	public String displayRoomAttributesByRoom(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {	

		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId(campusId);
		roomPK.setBuildingId(buildingId);
		roomPK.setRoomId(roomId);

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());	

		if(bookingConfirmation.isRoomManaged(roomPK,usrPK)){

			roomAttributePagination.preparePage(model,
					objectManagement.getRoomAttributes(objectManagement.getRoom(roomPK)), 
					page,"roomAttributes");

			model.addAttribute("campusId",campusId);
			model.addAttribute("buildingId",buildingId);
			model.addAttribute("roomId",roomId);			
		}else{
			model.addAttribute("isRoomPKIncorrect",true);
		}		

		return "managedRoomAttributeList";
	}	

	@RequestMapping(value="/room-attribute/manage",method = RequestMethod.GET)
	public String displayRoomAttributeDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			@RequestParam(value="room_attribute_type_id", required=true) String roomAttributeTypeId,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		RoomAttributePK roomAttributePK=new RoomAttributePK();
		roomAttributePK.setCampusId(campusId);
		roomAttributePK.setBuildingId(buildingId);
		roomAttributePK.setRoomId(roomId);
		roomAttributePK.setRoomAttributeTypeId(roomAttributeTypeId);

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());	

		if(bookingConfirmation.isRoomAttributeManaged(roomAttributePK,usrPK)){			
			model.addAttribute("roomAttribute",objectManagement.getRoomAttribute(roomAttributePK));		
		}else{			
			model.addAttribute("isRoomAttributePKIncorrect",true);	
		}		

		return "managedRoomAttributeManagement";
	}

	@RequestMapping(value="/reservations/waiting/previous",method = RequestMethod.GET)
	public String displayPreviousWaitingReservations(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		reservationPagination.preparePage(model, 
				bookingConfirmation.getPreviousHandledWaitingReservations(usrPK), page,"reservations");

		model.addAttribute("period","previous");
		model.addAttribute("status", "C");

		return "confirmerReservationList";		
	}

	@RequestMapping(value="/reservations/waiting/upcoming",method = RequestMethod.GET)
	public String displayUpcomingWaitingReservations(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		reservationPagination.preparePage(model,
				bookingConfirmation.getUpcomingHandledWaitingReservations(usrPK), page,"reservations");

		model.addAttribute("period","upcoming");
		model.addAttribute("status", "C");

		return "confirmerReservationList";		
	}

	@RequestMapping(value="/reservations/booked/previous",method = RequestMethod.GET)
	public String displayPreviousBookedReservations(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		reservationPagination.preparePage(model,
				bookingConfirmation.getPreviousHandledBookedReservations(usrPK), page,"reservations");

		model.addAttribute("period","previous");
		model.addAttribute("status", "U");

		return "confirmerReservationList";		
	}

	@RequestMapping(value="/reservations/booked/upcoming",method = RequestMethod.GET)
	public String displayUpcomingBookedReservations(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());			

		reservationPagination.preparePage(model,
				bookingConfirmation.getUpcomingHandledBookedReservations(usrPK), page,"reservations");

		model.addAttribute("period","upcoming");
		model.addAttribute("status", "U");

		return "confirmerReservationList";				
	}

	@RequestMapping(value="/reservations/cancelled/previous",method = RequestMethod.GET)
	public String displayPreviousCancelledReservations(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		reservationPagination.preparePage(model,
				bookingConfirmation.getPreviousHandledCancelledReservations(usrPK), page,"reservations");

		model.addAttribute("period","previous");
		model.addAttribute("status", "D");

		return "confirmerReservationList";		
	}

	@RequestMapping(value="/reservations/cancelled/upcoming",method = RequestMethod.GET)
	public String displayUpcomingCancelledReservations(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		reservationPagination.preparePage(model,
				bookingConfirmation.getUpcomingHandledCancelledReservations(usrPK), page,"reservations");

		model.addAttribute("period","upcoming");
		model.addAttribute("status", "D");

		return "confirmerReservationList";		
	}

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayReservationDetails(
			@RequestParam(value="reservation_id", required=true) int reservationId,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		ReservationPK reservationPK=new ReservationPK();
		reservationPK.setReservationId(reservationId);

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		Reservation reservation=booking.getReservation(reservationPK);

		if(bookingConfirmation.confirmerHandlesReservation(reservation, usrPK)){

			Calendar calendar=Calendar.getInstance();		
			calendar.setTime(reservation.getTimeFrom());
			Date resDate=calendar.getTime();

			if(ReservationValidator.isGivenDatePast(resDate)){
				model.addAttribute("isDatePast",true);	
			}else{
				model.addAttribute("isDatePast",false);	
			}

			String dbStatusId=reservation.getDbStatusId();

			model.addAttribute("status",dbStatusId);		
			model.addAttribute("reservation",reservation);
		}else{
			model.addAttribute("isReservationPKIncorrect", true);
		}							

		return "reservationManagementByConfirmer";
	}	

	@RequestMapping(value="/reservation-children",method = RequestMethod.GET)
	public String displayReservationChildren(
			@RequestParam(value="reservation_id", required=true) int reservationId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal){

		ReservationPK reservationPK=new ReservationPK();
		reservationPK.setReservationId(reservationId);

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());

		Reservation reservation=booking.getReservation(reservationPK);

		if(bookingConfirmation.confirmerHandlesReservation(reservation, usrPK)){			
			reservationPagination.preparePage(model, 
					booking.getReservationChildren(reservation), page,"reservations");
		}else{
			model.addAttribute("isReservationPKIncorrect", true);
		}

		model.addAttribute("role","confirmer");

		return "reservationChildren";	
	}

	@RequestMapping(value="/reservation-list-by-day-form",method = RequestMethod.GET)
	public String displayReservationListByDayForm(
			@RequestParam(value="campus_id", required=false) String campusId,
			@RequestParam(value="building_id", required=false) String buildingId,
			@RequestParam(value="room_id", required=false) String roomId,
			@RequestParam(value="reservation_date", required=false) String reservationDate,
			@RequestParam(value="_params", required=false) String _params,
			HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException {

		if(_params!=null){			
			try{						
				model.addAttribute("reservationDate",getDecodedInstance(_params).getReservationDate());
			}catch(NullPointerException e){				
			}
		}				

		if(reservationDate!=null) {
			model.addAttribute("reservationDate", reservationDate);	
		}		

		if((campusId!=null)&&(!campusId.isEmpty())){
			model.addAttribute("campusId",campusId);	
		}

		if((buildingId!=null)&&(!buildingId.isEmpty())){
			model.addAttribute("buildingId",buildingId);
		}

		if((roomId!=null)&&(!roomId.isEmpty())){
			model.addAttribute("roomId",roomId); 
		}		

		model.addAttribute("role","confirmer");

		return "reservationListByDayForm";		
	}

	@RequestMapping(value="/reservation-list-by-day-processing",params = "advance",method = RequestMethod.POST)
	public String redirectToReservationListByDay(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String campusId=request.getParameter("campus_id");
		String buildingId=request.getParameter("building_id");
		String roomId=request.getParameter("room_id");
		String reservationDate=request.getParameter("reservation_date");

		return "redirect:/confirmer/reservation-list-by-day?campus_id="
		+URLEncoder.encode(campusId,"utf8")+"&building_id="+URLEncoder.encode(buildingId,"utf8")
		+"&room_id="+URLEncoder.encode(roomId,"utf8")+"&reservation_date="+reservationDate;
	}	

	@RequestMapping(value="/reservation-list-by-day",method = RequestMethod.GET)
	public String displayReservationsByDayAndRoom(
			@RequestParam(value="reservation_date", required=true) String reservationDate,
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal){

		model.addAttribute("role","confirmer");

		if(ReservationValidator.isDateParseable(reservationDate)){			

			Date parsedReservationDate=ReservationValidator.getParsedDate(reservationDate);
			model.addAttribute("date",parsedReservationDate);

			RoomPK roomPK=new RoomPK();
			roomPK.setCampusId(campusId);
			roomPK.setBuildingId(buildingId);
			roomPK.setRoomId(roomId);				

			UsrPK usrPK=new UsrPK();
			usrPK.setUsrId(principal.getName());

			if(bookingConfirmation.isRoomManaged(roomPK, usrPK)){			

				BookableRoomPK bookableRoomPK=new BookableRoomPK();
				bookableRoomPK.setCampusId(campusId);
				bookableRoomPK.setBuildingId(buildingId);
				bookableRoomPK.setRoomId(roomId);

				reservationPagination.preparePage(model,
						booking.getReservationsByDayAndRoom(bookableRoomPK,parsedReservationDate), 
						page,"reservations");									

				model.addAttribute("campusId",campusId);
				model.addAttribute("buildingId",buildingId);
				model.addAttribute("roomId",roomId);				

				if(ReservationValidator.isGivenDatePast(parsedReservationDate)){
					model.addAttribute("isNewReservationButtonEnabled",false);	
				}else{
					model.addAttribute("isNewReservationButtonEnabled",true);
				}	
			}else{
				model.addAttribute("isRoomPKIncorrect",true);		
			}			
		}else {
			model.addAttribute("isDateFormatIncorrect",true);		
		}			

		return "reservationListByDay";				
	}

	@RequestMapping(value="/reservation-list-by-day-processing",
			params = "choose_campus", method = RequestMethod.POST)
	public String chooseManagedCampus(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String reservationDate=request.getParameter("reservation_date");
		String _params="reservation_date="+URLEncoder.encode(reservationDate,"utf8");
		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/managed-campuses?source=res-list-by-day&_params="+_params;		
	}

	@RequestMapping(value="/reservation-list-by-day-processing",
			params = "choose_building", method = RequestMethod.POST)
	public String chooseManagedBuilding(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String reservationDate=request.getParameter("reservation_date");
		String campusId=request.getParameter("campus_id");

		String _params="reservation_date="+URLEncoder.encode(reservationDate,"utf8");
		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/managed-buildings?source=res-list-by-day&campus_id="
		+URLEncoder.encode(campusId, "utf8")+"&_params="+_params;		
	}

	@RequestMapping(value="/reservation-list-by-day-processing",
			params = "choose_room", method = RequestMethod.POST)
	public String chooseManagedRoom(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String reservationDate=request.getParameter("reservation_date");
		String campusId=request.getParameter("campus_id");		
		String buildingId=request.getParameter("building_id");

		String _params="reservation_date="+URLEncoder.encode(reservationDate,"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/managed-rooms?source=res-list-by-day&campus_id="
		+URLEncoder.encode(campusId,"utf8")+"&building_id="
		+URLEncoder.encode(buildingId,"utf8")+"&_params="+_params;		
	}

	@RequestMapping(value="/manage",params = "book", method = RequestMethod.POST)
	public String confirmReservation(Reservation reservation,
			BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response,Principal principal){

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		if(bookingConfirmation.confirmerHandlesReservation(reservation, usrPK)){

			ReservationPK reservationPK=new ReservationPK();
			reservationPK.setReservationId(reservation.getReservationId());			

			BookableRoomPK bookableRoomPK=new BookableRoomPK();
			bookableRoomPK.setCampusId(reservation.getCampusId());
			bookableRoomPK.setBuildingId(reservation.getBuildingId());
			bookableRoomPK.setRoomId(reservation.getRoomId());

			if(booking.isOverlapped(reservation.getTimeFrom(), 
					reservation.getTimeTo(), bookableRoomPK)){					
				bindingResult.rejectValue("timeTo", "overlap");	
				return "reservationManagementByConfirmer";
			}else{
				bookingConfirmation.confirmReservation(reservationPK);					
			}	
		}

		return "redirect:/confirmer/reservations/booked/upcoming";		
	}

	@RequestMapping(value="/manage",params = "cancel", method = RequestMethod.POST)
	public String cancelReservation(Reservation reservation, BindingResult result,
			HttpServletRequest request,HttpServletResponse response,Principal principal){

		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(principal.getName());		

		if(bookingConfirmation.confirmerHandlesReservation(reservation, usrPK)){
			ReservationPK reservationPK=new ReservationPK();
			reservationPK.setReservationId(reservation.getReservationId());	

			booking.cancelReservation(reservationPK);
		}		

		return "redirect:/confirmer/reservations/cancelled/upcoming";
	}
}
