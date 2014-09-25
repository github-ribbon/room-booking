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

import edu.university.roombooking.domain.BookableRoom;
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
import edu.university.roombooking.service.GroupManagement;
import edu.university.roombooking.service.ObjectManagement;
import edu.university.roombooking.service.RoomBooking;
import edu.university.roombooking.util.Decodable;
import edu.university.roombooking.util.Pagination;
import edu.university.roombooking.validation.ReservationValidator;


@Controller
@RequestMapping(value="/client")
@PreAuthorize(value="hasRole('ROLE_CLIENT')")
public class ClientController implements Decodable<Reservation>{

	@Autowired
	private ReservationValidator reservationValidator;

	@Autowired
	private Pagination<Campus> campusPagination;

	@Autowired
	private Pagination<Building> buildingPagination;

	@Autowired
	private Pagination<Room> roomPagination;

	@Autowired
	private Pagination<Object[]> objectArrayPagination;

	@Autowired
	private Pagination<RoomAttribute> roomAttributePagination;

	@Autowired
	private Pagination<RoomAttributeType> roomAttributeTypePagination;

	@Autowired
	private Pagination<BookableRoom> bookableRoomPagination;

	@Autowired
	private Pagination<Reservation> reservationPagination;

	@Autowired
	private RoomBooking roomBooking;	

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
	public String displayAllBookableCampuses(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {	

		campusPagination.preparePage(model, roomBooking.getAllBookableCampuses(), page,"campuses"); 
		return "bookableCampusList";
	}	

	@RequestMapping(value="/building",method = RequestMethod.GET)
	public String displayBookableBuildingsByCampus(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model){		

		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId(campusId);			

		if(roomBooking.isCampusBookable(campusPK)){					
			buildingPagination.preparePage(model, 
					roomBooking.getBookableBuildings(campusPK), page,"buildings");
			model.addAttribute("campusId",campusId);			
		}else{			
			model.addAttribute("isCampusPKIncorrect",true);			
		}

		return "bookableBuildingList";
	}	

	@RequestMapping(value="/building/",method = RequestMethod.GET)
	public String displayAllBookableBuildings(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {	
		buildingPagination.preparePage(model,
				roomBooking.getAllBookableBuildings(), page,"buildings");
		return "fullBookableBuildingList";
	}	

	@RequestMapping(value="/room",method = RequestMethod.GET)
	public String displayBookableRoomsByBuilding(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {		

		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId(campusId);
		buildingPK.setBuildingId(buildingId);

		if(roomBooking.isBuildingBookable(buildingPK)){					
			roomPagination.preparePage(model, 
					roomBooking.getBookableRooms(buildingPK), page,"rooms");

			model.addAttribute("campusId",campusId);
			model.addAttribute("buildingId",buildingId);			
		}else{
			model.addAttribute("isBuildingPKIncorrect",true);	
		}	

		return "bookableRoomList";
	}

	@RequestMapping(value="/room/",method = RequestMethod.GET)
	public String displayAllBookableRooms(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {			
		roomPagination.preparePage(model, 
				roomBooking.getAllBookableRooms(), page,"rooms");
		return "fullBookableRoomList";
	}	

	@RequestMapping(value="/room-attribute-type",method = RequestMethod.GET)
	public String displayAllBookableRoomAttributeTypes(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {		
		roomAttributeTypePagination.preparePage(model, 
				roomBooking.getAllBookableRoomAttributeTypes(), page,"roomAttributeTypes"); 
		return "bookableRoomAttributeTypeList";
	}	

	@RequestMapping(value="/campus/manage",method = RequestMethod.GET)
	public String displayBookableCampusDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			HttpServletRequest request,
			HttpServletResponse response,Model model) {

		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId(campusId);		

		if(roomBooking.isCampusBookable(campusPK)){				
			model.addAttribute("campus",objectManagement.getCampus(campusPK));		
		}else{
			model.addAttribute("isCampusPKIncorrect",true);
		}	

		return "bookableCampusManagement";		
	}

	@RequestMapping(value="/building/manage",method = RequestMethod.GET)
	public String displayBuildingDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId(campusId);
		buildingPK.setBuildingId(buildingId);

		if(roomBooking.isBuildingBookable(buildingPK)){					
			model.addAttribute("building",objectManagement.getBuilding(buildingPK));			
		}else{
			model.addAttribute("isBuildingPKIncorrect",true);
		}	

		return "bookableBuildingManagement";			
	}

	@RequestMapping(value="/room/manage",method = RequestMethod.GET)
	public String displayRoomDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId(campusId);
		roomPK.setBuildingId(buildingId);
		roomPK.setRoomId(roomId);

		if(roomBooking.isRoomBookable(roomPK)){					
			model.addAttribute("room",objectManagement.getRoom(roomPK));			
		}else{
			model.addAttribute("isRoomPKIncorrect",true);
		}		

		return "bookableRoomManagement";	
	}

	@RequestMapping(value="/room-attribute-type/manage",method = RequestMethod.GET)
	public String displayRoomAttributeTypeDetails(
			@RequestParam(value="room_attribute_type_id", required=true) String roomAttributeTypeId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId(roomAttributeTypeId);	

		if(roomBooking.isRoomAttributeTypeBookable(roomAttributeTypePK)){					
			model.addAttribute("roomAttributeType",
					objectManagement.getRoomAttributeType(roomAttributeTypePK));		
		}else{
			model.addAttribute("isRoomAttributeTypePKIncorrect",true);
		}	

		return "bookableRoomAttributeTypeManagement";		
	}

	@RequestMapping(value="/room/by-room-attribute-type",method = RequestMethod.GET)
	public String displayRoomsByRoomAttributeType(
			@RequestParam(value="room_attribute_type_id", required=true) String roomAttributeTypeId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {		

		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId(roomAttributeTypeId);		

		if(roomBooking.isRoomAttributeTypeBookable(roomAttributeTypePK)){
			objectArrayPagination.preparePage(model, 
					roomBooking.getBookableRooms(roomAttributeTypePK), page, "rooms");	

			model.addAttribute("roomAttributeType",
					objectManagement.getRoomAttributeType(roomAttributeTypePK));
			model.addAttribute("roomAttributeTypeId",roomAttributeTypeId);		
		}else{
			model.addAttribute("isRoomAttributeTypePKIncorrect",true);		
		}		

		return "bookableRoomListByRoomAttributeType";
	}		

	@RequestMapping(value="/room-attribute",method = RequestMethod.GET)
	public String displayRoomAttributesByRoom(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model) {	

		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId(campusId);
		roomPK.setBuildingId(buildingId);
		roomPK.setRoomId(roomId);

		if(roomBooking.isRoomBookable(roomPK)){	
			roomAttributePagination.preparePage(model, 
					objectManagement.getRoomAttributes(objectManagement.getRoom(roomPK)),
					page,"roomAttributes");

			model.addAttribute("campusId",campusId);
			model.addAttribute("buildingId",buildingId);
			model.addAttribute("roomId",roomId);			
		}else{
			model.addAttribute("isRoomPKIncorrect",true);
		}		

		return "bookableRoomAttributeList";
	}	

	@RequestMapping(value="/room-attribute/manage",method = RequestMethod.GET)
	public String displayRoomAttributeDetails(
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			@RequestParam(value="room_attribute_type_id", required=true) String roomAttributeTypeId,
			HttpServletRequest request,HttpServletResponse response,Model model) {

		RoomAttributePK roomAttributePK=new RoomAttributePK();
		roomAttributePK.setCampusId(campusId);
		roomAttributePK.setBuildingId(buildingId);
		roomAttributePK.setRoomId(roomId);
		roomAttributePK.setRoomAttributeTypeId(roomAttributeTypeId);		

		if(roomBooking.isRoomAttributeBookable(roomAttributePK)){			
			model.addAttribute("roomAttribute",objectManagement.getRoomAttribute(roomAttributePK));		
		}else{			
			model.addAttribute("isRoomAttributePKIncorrect",true);	
		}		

		return "bookableRoomAttributeManagement";
	}

	@RequestMapping(value="/my-reservations",method = RequestMethod.GET)
	public String displayAllClientReservations(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		UsrPK usrPK=new UsrPK();		
		usrPK.setUsrId(principal.getName());		

		reservationPagination.preparePage(model, 
				roomBooking.getAllClientReservations(usrPK), page,"reservations");

		return "myAllReservations";		
	}

	@RequestMapping(value="/my-reservations/booked",method = RequestMethod.GET)
	public String displayClientBookedReservations(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Principal principal,Model model) {

		UsrPK usrPK=new UsrPK();		
		usrPK.setUsrId(principal.getName());		

		reservationPagination.preparePage(model, 
				roomBooking.getClientBookedReservations(usrPK), page,"reservations");

		return "myBookedReservations";		
	}

	@RequestMapping(value="/my-reservations/waiting",method = RequestMethod.GET)
	public String displayClientWaitingReservations(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		UsrPK usrPK=new UsrPK();		
		usrPK.setUsrId(principal.getName());		

		reservationPagination.preparePage(model, 
				roomBooking.getClientWaitingReservations(usrPK), page,"reservations");						

		return "myWaitingReservations";		
	}

	@RequestMapping(value="/my-reservations/cancelled",method = RequestMethod.GET)
	public String displayClientCancelledReservations(
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal) {

		UsrPK usrPK=new UsrPK();		
		usrPK.setUsrId(principal.getName());			

		reservationPagination.preparePage(model, 
				roomBooking.getClientCancelledReservations(usrPK), page,"reservations");

		return "myCancelledReservations";		
	}

	@RequestMapping(value="/reservation-list-by-day",method = RequestMethod.GET)
	public String displayReservationListByDayAndRoom(
			@RequestParam(value="reservation_date", required=true) String reservationDate,
			@RequestParam(value="campus_id", required=true) String campusId,
			@RequestParam(value="building_id", required=true) String buildingId,
			@RequestParam(value="room_id", required=true) String roomId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model){

		model.addAttribute("role","client");

		if(ReservationValidator.isDateParseable(reservationDate)){			
			Date parsedReservationDate=ReservationValidator.getParsedDate(reservationDate);
			model.addAttribute("date",parsedReservationDate);

			BookableRoomPK bookableRoomPK=new BookableRoomPK();
			bookableRoomPK.setCampusId(campusId);
			bookableRoomPK.setBuildingId(buildingId);
			bookableRoomPK.setRoomId(roomId);				

			if(objectManagement.isBookableRoom(bookableRoomPK)){
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
		}else{
			model.addAttribute("isDateFormatIncorrect",true);			
		}		

		return "reservationListByDay";	
	}

	@RequestMapping(value="/room-statuses",params="status=busy",method = RequestMethod.GET)
	public String displayBusyRoomsByDay(@RequestParam(value="date", required=true) String date,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model){

		if(ReservationValidator.isDateParseable(date)){			
			Date reservationDate=ReservationValidator.getParsedDate(date);

			if(!ReservationValidator.isToday(reservationDate)
					&&ReservationValidator.isGivenDatePast(reservationDate)){
				model.addAttribute("isDatePast",true);				
			}else{
				model.addAttribute("reservationDate",reservationDate);					
				bookableRoomPagination.preparePage(model, 
						roomBooking.getBusyRooms(reservationDate), page,"bookableRooms");		
			}			
		}else{
			model.addAttribute("isDateFormatIncorrect",true);			
		}		

		return "busyRooms";		
	}	

	@RequestMapping(value="/room-statuses",params = "status=available",method = RequestMethod.GET)
	public String displayAvailableRoomsByDay(@RequestParam(value="date", required=true) String date,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model){

		if(ReservationValidator.isDateParseable(date)){			
			Date reservationDate=ReservationValidator.getParsedDate(date);

			if(!ReservationValidator.isToday(reservationDate)
					&&ReservationValidator.isGivenDatePast(reservationDate)){	
				model.addAttribute("isDatePast",true);				
			}else{
				model.addAttribute("reservationDate",reservationDate);						
				bookableRoomPagination.preparePage(model, 
						roomBooking.getAvailableRooms(reservationDate), page,"bookableRooms");	
			}			
		}else{
			model.addAttribute("isDateFormatIncorrect",true);		
		}		

		return "availableRooms";		
	}

	@RequestMapping(value="/room-statuses-form",method = RequestMethod.GET)
	public String displayRoomStatusesForm(HttpServletRequest request,
			HttpServletResponse response,Model model){
		return "roomStatusesForm";		
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

		model.addAttribute("role","client");

		return "reservationListByDayForm";		
	}

	@RequestMapping(value="/reservation-list-by-day-processing",params = "advance",method = RequestMethod.POST)
	public String redirectToDisplayReservationListByDayAndRoomRequest(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String campusId=request.getParameter("campus_id");
		String buildingId=request.getParameter("building_id");
		String roomId=request.getParameter("room_id");
		String reservationDate=request.getParameter("reservation_date");

		return "redirect:/client/reservation-list-by-day?campus_id="
		+URLEncoder.encode(campusId,"utf8")+"&building_id="+URLEncoder.encode(buildingId, "utf8")
		+"&room_id="+URLEncoder.encode(roomId, "utf8")
		+"&reservation_date="+URLEncoder.encode(reservationDate,"utf8");			
	}


	@RequestMapping(value="/new",params = "choose_campus", method = RequestMethod.POST)
	public String chooseBookableCampus(Reservation reservation,BindingResult result,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String reservationDate="";
		String timeFrom="";
		String timeTo="";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");		

		if(reservation.getReservationDate()!=null)		
			reservationDate=dateFormat.format(reservation.getReservationDate());	

		if(reservation.getTimeFrom()!=null)		
			timeFrom=timeFormat.format(reservation.getTimeFrom());

		if(reservation.getTimeTo()!=null)		
			timeTo=timeFormat.format(reservation.getTimeTo());

		String _params="campus_id="+URLEncoder.encode(reservation.getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(reservation.getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(reservation.getRoomId(),"utf8")
				+"&reservation_date="+URLEncoder.encode(reservationDate,"utf8")
				+"&time_from="+URLEncoder.encode(timeFrom,"utf8")
				+"&time_to="+URLEncoder.encode(timeTo,"utf8")
				+"&person_group_id="+URLEncoder.encode(reservation.getPersonGroupId(),"utf8")
				+"&purpose="+URLEncoder.encode(reservation.getPurpose(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/bookable-campuses?source=reservation&_params="+_params;		
	}

	@RequestMapping(value="/reservation-list-by-day-processing",
			params = "choose_campus", method = RequestMethod.POST)
	public String chooseBookableCampus(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String reservationDate=request.getParameter("reservation_date");				
		String _params="reservation_date="+URLEncoder.encode(reservationDate,"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/bookable-campuses?source=res-list-by-day&_params="+_params;		
	}

	@RequestMapping(value="/new",params = "choose_building", method = RequestMethod.POST)
	public String chooseBookableBuilding(Reservation reservation,BindingResult result,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String reservationDate="";
		String timeFrom="";
		String timeTo="";		

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");		

		if(reservation.getReservationDate()!=null)		
			reservationDate=dateFormat.format(reservation.getReservationDate());	

		if(reservation.getTimeFrom()!=null)		
			timeFrom=timeFormat.format(reservation.getTimeFrom());

		if(reservation.getTimeTo()!=null)		
			timeTo=timeFormat.format(reservation.getTimeTo());

		String _params="campus_id="+URLEncoder.encode(reservation.getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(reservation.getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(reservation.getRoomId(),"utf8")
				+"&reservation_date="+URLEncoder.encode(reservationDate,"utf8")
				+"&time_from="+URLEncoder.encode(timeFrom,"utf8")
				+"&time_to="+URLEncoder.encode(timeTo,"utf8")
				+"&person_group_id="+URLEncoder.encode(reservation.getPersonGroupId(),"utf8")
				+"&purpose="+URLEncoder.encode(reservation.getPurpose(),"utf8");	

		_params=URLEncoder.encode(_params,"utf8");			

		return "redirect:/choose/bookable-buildings?source=reservation&campus_id="
		+URLEncoder.encode(reservation.getCampusId(),"utf8")+"&_params="+_params;		
	}

	@RequestMapping(value="/reservation-list-by-day-processing",
			params = "choose_building", method = RequestMethod.POST)
	public String chooseBookableBuilding(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String reservationDate=request.getParameter("reservation_date");
		String campusId=request.getParameter("campus_id");

		String _params="reservation_date="+URLEncoder.encode(reservationDate,"utf8");		
		_params=URLEncoder.encode(_params,"utf8");		

		campusId=URLEncoder.encode(campusId,"utf8");

		return "redirect:/choose/bookable-buildings?source=res-list-by-day&campus_id="
		+campusId+"&_params="+_params;		
	}

	@RequestMapping(value="/new",params = "choose_room", method = RequestMethod.POST)
	public String chooseBookableRoom(Reservation reservation,BindingResult result,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String reservationDate="";
		String timeFrom="";
		String timeTo="";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");		

		if(reservation.getReservationDate()!=null)		
			reservationDate=dateFormat.format(reservation.getReservationDate());	

		if(reservation.getTimeFrom()!=null)		
			timeFrom=timeFormat.format(reservation.getTimeFrom());

		if(reservation.getTimeTo()!=null)		
			timeTo=timeFormat.format(reservation.getTimeTo());


		String _params="campus_id="+URLEncoder.encode(reservation.getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(reservation.getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(reservation.getRoomId(),"utf8")
				+"&reservation_date="+URLEncoder.encode(reservationDate,"utf8")
				+"&time_from="+URLEncoder.encode(timeFrom,"utf8")
				+"&time_to="+URLEncoder.encode(timeTo,"utf8")
				+"&person_group_id="+URLEncoder.encode(reservation.getPersonGroupId(),"utf8")
				+"&purpose="+URLEncoder.encode(reservation.getPurpose(),"utf8");	

		_params=URLEncoder.encode(_params,"utf8");		

		String campusId=reservation.getCampusId();
		campusId=URLEncoder.encode(campusId,"utf8");
		String buildingId=reservation.getBuildingId();
		buildingId=URLEncoder.encode(buildingId,"utf8");

		return "redirect:/choose/bookable-rooms?source=reservation&campus_id="
		+campusId+"&building_id="+buildingId+"&_params="+_params;		
	}

	@RequestMapping(value="/reservation-list-by-day-processing",
			params = "choose_room", method = RequestMethod.POST)
	public String chooseBookableRoom(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String reservationDate=request.getParameter("reservation_date");
		String campusId=request.getParameter("campus_id");		
		String buildingId=request.getParameter("building_id");	

		String _params="reservation_date="+URLEncoder.encode(reservationDate,"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/bookable-rooms?source=res-list-by-day&campus_id="
		+URLEncoder.encode(campusId,"utf8")+"&building_id="
		+URLEncoder.encode(buildingId,"utf8")+"&_params="+_params;		
	}

	@RequestMapping(value="/new",params = "n_choose_person_group", method = RequestMethod.POST)
	public String choosePersonGroupForMakingReservation(Reservation reservation,BindingResult result,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String reservationDate="";
		String timeFrom="";
		String timeTo="";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");		

		if(reservation.getReservationDate()!=null)		
			reservationDate=dateFormat.format(reservation.getReservationDate());	

		if(reservation.getTimeFrom()!=null)		
			timeFrom=timeFormat.format(reservation.getTimeFrom());

		if(reservation.getTimeTo()!=null)		
			timeTo=timeFormat.format(reservation.getTimeTo());

		String _params="campus_id="+URLEncoder.encode(reservation.getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(reservation.getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(reservation.getRoomId(),"utf8")
				+"&reservation_date="+URLEncoder.encode(reservationDate,"utf8")
				+"&time_from="+URLEncoder.encode(timeFrom,"utf8")
				+"&time_to="+URLEncoder.encode(timeTo,"utf8")
				+"&person_group_id="+URLEncoder.encode(reservation.getPersonGroupId(),"utf8")
				+"&purpose="+URLEncoder.encode(reservation.getPurpose(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");		

		return "redirect:/choose/person-groups?source=reservation&action=create&_params="+_params;		
	}

	@RequestMapping(value="/manage",params = "m_choose_person_group", method = RequestMethod.POST)
	public String choosePersonGroupForChangingReservation(Reservation reservation,BindingResult result,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		String reservationDate="";
		String timeFrom="";
		String timeTo="";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");		

		if(reservation.getReservationDate()!=null)		
			reservationDate=dateFormat.format(reservation.getReservationDate());	

		if(reservation.getTimeFrom()!=null)		
			timeFrom=timeFormat.format(reservation.getTimeFrom());

		if(reservation.getTimeTo()!=null)		
			timeTo=timeFormat.format(reservation.getTimeTo());

		String _params="campus_id="+URLEncoder.encode(reservation.getCampusId(),"utf8")
				+"&building_id="+URLEncoder.encode(reservation.getBuildingId(),"utf8")
				+"&room_id="+URLEncoder.encode(reservation.getRoomId(),"utf8")
				+"&reservation_date="+URLEncoder.encode(reservationDate,"utf8")
				+"&time_from="+URLEncoder.encode(timeFrom,"utf8")
				+"&time_to="+URLEncoder.encode(timeTo,"utf8")
				+"&person_group_id="+URLEncoder.encode(reservation.getPersonGroupId(),"utf8")
				+"&purpose="+URLEncoder.encode(reservation.getPurpose(),"utf8");		

		_params=URLEncoder.encode(_params,"utf8");			

		int reservationId=reservation.getReservationId();

		return "redirect:/choose/person-groups?source=reservation&action=manage" +
		"&reservation_id="+reservationId+"&_params="+_params;		
	}		

	@RequestMapping(value="/reservation-children",method = RequestMethod.GET)
	public String displayReservationChildren(
			@RequestParam(value="reservation_id", required=true) int reservationId,
			@RequestParam(value="page", required=false) Integer page,
			HttpServletRequest request,HttpServletResponse response,Model model,Principal principal){

		ReservationPK reservationPK=new ReservationPK();
		reservationPK.setReservationId(reservationId);

		if(booking.isReservation(reservationPK)){
			Reservation reservation=booking.getReservation(reservationPK);

			if(reservation.getUsrId().equals(principal.getName())){				
				reservationPagination.preparePage(model,
						booking.getReservationChildren(reservation), page,"reservations");
			}else{
				model.addAttribute("isReservationPKIncorrect", true);
			}						
		}else{
			model.addAttribute("isReservationPKIncorrect", true);
		}

		model.addAttribute("role","client");

		return "reservationChildren";	
	}

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String displayNewReservationForm(
			@RequestParam(value="campus_id", required=false) String campusId,
			@RequestParam(value="building_id", required=false) String buildingId,
			@RequestParam(value="room_id", required=false) String roomId,
			@RequestParam(value="person_group_id", required=false) String personGroupId,
			@RequestParam(value="reservation_date", required=false) String reservationDate,
			@RequestParam(value="_params", required=false) String _params,
			HttpServletRequest request,
			HttpServletResponse response,Model model) throws UnsupportedEncodingException {

		Reservation reservation=new Reservation();

		if(_params!=null){
			reservation=getDecodedInstance(_params);
		}			
		if((campusId!=null)&&(!campusId.isEmpty())){
			reservation.setCampusId(campusId);			
		}
		if((buildingId!=null)&&(!buildingId.isEmpty())){
			reservation.setBuildingId(buildingId);			
		}
		if((roomId!=null)&&(!roomId.isEmpty())){
			reservation.setRoomId(roomId);			
		}		
		if((personGroupId!=null)&&(!personGroupId.isEmpty())){
			reservation.setPersonGroupId(personGroupId);
		}

		if((reservationDate!=null)&&(ReservationValidator.isDateParseable(reservationDate))){
			reservation.setReservationDate(ReservationValidator.getParsedDate(reservationDate));
		}

		model.addAttribute("reservation",reservation);

		return "newReservationForm";				
	}

	@RequestMapping(value="/new",params = "create", method = RequestMethod.POST)
	public String makeReservation(Reservation reservation, BindingResult bindingResult,
			HttpServletRequest request,HttpServletResponse response,
			Model model,Principal principal) throws ParseException,IllegalArgumentException{

		reservationValidator.validate(reservation, bindingResult);

		if (!bindingResult.hasErrors()) {			
			int status=roomBooking.makeReservation(reservation, principal.getName());

			if(status==Reservation.BOOKED){
				return "redirect:/client/my-reservations/booked";
			}else if(status==Reservation.WAITING){
				return "redirect:/client/my-reservations/waiting";
			}else if(status==Reservation.ABORTED){
				bindingResult.rejectValue("timeTo", "overlap");						
			}		
		}

		return "newReservationForm";	
	}

	@RequestMapping(value="/manage",params = "delete", method = RequestMethod.POST)
	public String cancelReservation(Reservation reservation, BindingResult result,
			HttpServletRequest request,HttpServletResponse response,Principal principal) {		

		ReservationPK reservationPK=new ReservationPK();
		reservationPK.setReservationId(reservation.getReservationId());

		if(booking.getReservation(reservationPK).getUsrId().equals(principal.getName())){
			booking.cancelReservation(reservationPK);		
		}		

		return "redirect:/client/my-reservations/cancelled";
	}	

	@RequestMapping(value="/manage",params = "update", method = RequestMethod.POST)
	public String changeReservation(Reservation reservation, BindingResult result,
			HttpServletRequest request,HttpServletResponse response,Principal principal){		

		if(reservation.getUsrId().equals(principal.getName())){

			reservationValidator.validate(reservation, result);

			if(result.hasErrors()){
				String dbStatusId=reservation.getDbStatusId();			

				request.setAttribute("status",dbStatusId);		
				request.setAttribute("isDatePast",false);

				return "reservationManagement";
			}else{		
				Reservation modified=reservation;		

				int action=roomBooking.changeReservation(modified);

				if(action==Reservation.BOOKED){
					return "redirect:/client/my-reservations/booked";
				}else if(action==Reservation.WAITING){
					return "redirect:/client/my-reservations/waiting";
				}					
			}		
		}

		throw new RuntimeException();
	}

	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public String displayReservationDetails(
			@RequestParam(value="reservation_id", required=true) int reservationId,
			@RequestParam(value="person_group_id", required=false) String personGroupId,
			@RequestParam(value="_params", required=false) String _params,
			HttpServletRequest request,
			HttpServletResponse response,Model model,Principal principal) throws UnsupportedEncodingException {

		ReservationPK reservationPK=new ReservationPK();
		reservationPK.setReservationId(reservationId);

		if(booking.isReservation(reservationPK)){
			Reservation reservation=booking.getReservation(reservationPK);

			if(reservation.getUsrId().equals(principal.getName())){

				Calendar calendar=Calendar.getInstance();		
				calendar.setTime(reservation.getTimeFrom());
				Date reservationDate=calendar.getTime();

				if(ReservationValidator.isGivenDatePast(reservationDate)){
					model.addAttribute("isDatePast",true);
				}else{
					model.addAttribute("isDatePast",false);	
				}		

				String dbStatusId=reservation.getDbStatusId();

				if(dbStatusId.equals("C")){	

					if(_params!=null){

						Reservation decodedReservation = getDecodedInstance(_params);			

						reservation.setReservationDate(decodedReservation.getReservationDate());
						reservation.setTimeFrom(decodedReservation.getTimeFrom());
						reservation.setTimeTo(decodedReservation.getTimeTo());
						reservation.setPurpose(decodedReservation.getPurpose());
					}

					if(personGroupId!=null){
						reservation.setPersonGroupId(personGroupId);			
					}			
				}		

				model.addAttribute("status",dbStatusId);		
				model.addAttribute("reservation", reservation);				
			}else{
				model.addAttribute("isReservationPKIncorrect", true);
			}			
		}else{
			model.addAttribute("isReservationPKIncorrect", true);
		}	

		return "reservationManagement";
	}	
}
