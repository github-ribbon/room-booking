package edu.university.roombooking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
//import org.springframework.transaction.annotation.Transactional;

import edu.university.roombooking.domain.BookableRoom;
import edu.university.roombooking.domain.BookableRoomPK;
import edu.university.roombooking.domain.Building;
import edu.university.roombooking.domain.BuildingPK;
import edu.university.roombooking.domain.Campus;
import edu.university.roombooking.domain.CampusPK;
import edu.university.roombooking.domain.Room;
import edu.university.roombooking.domain.RoomAttribute;
import edu.university.roombooking.domain.RoomAttributePK;
import edu.university.roombooking.domain.RoomAttributeType;
import edu.university.roombooking.domain.RoomAttributeTypePK;
import edu.university.roombooking.domain.RoomManager;
import edu.university.roombooking.domain.RoomManagerPK;
import edu.university.roombooking.domain.RoomPK;
import edu.university.roombooking.domain.UsrGroupPK;
import edu.university.roombooking.util.test.BuildingTestUtil;
import edu.university.roombooking.util.test.CampusTestUtil;
import edu.university.roombooking.util.test.RoomAttributeTestUtil;
import edu.university.roombooking.util.test.RoomAttributeTypeTestUtil;
import edu.university.roombooking.util.test.RoomManagerTestUtil;
import edu.university.roombooking.util.test.RoomTestUtil;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml"})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
public class ObjectManagementIT {

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ObjectManagement objectManagement;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		/* Restores original database state before each test */
		Resource resource = ctx.getResource("classpath:statements.sql");
		JdbcTestUtils.executeSqlScript(jdbcTemplate, resource, true);  	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCampus() {		
		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId("Warszawa");

		assertNotNull(objectManagement.getCampus(campusPK));
	}

	@Test
	public void testGetBuilding() {
		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId("Warszawa");
		buildingPK.setBuildingId("KEN5");		

		assertNotNull(objectManagement.getBuilding(buildingPK));
	}

	@Test
	public void testGetRoom() {
		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId("Warszawa");
		roomPK.setBuildingId("KEN5");		
		roomPK.setRoomId("37");	

		assertNotNull(objectManagement.getRoom(roomPK));
	}

	@Test
	public void testIsCampus() {
		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId("Warszawa");

		assertTrue(objectManagement.isCampus(campusPK));
	}

	@Test
	public void testIsBuilding() {
		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId("Warszawa");
		buildingPK.setBuildingId("KEN5");		

		assertTrue(objectManagement.isBuilding(buildingPK));
	}

	@Test
	public void testIsRoom() {
		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId("Warszawa");
		roomPK.setBuildingId("KEN5");		
		roomPK.setRoomId("37");	

		assertTrue(objectManagement.isRoom(roomPK));
	}

	@Test
	public void testIsRoomAttributeType() {
		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId("internet");

		assertTrue(objectManagement.isRoomAttributeType(roomAttributeTypePK));
	}

	@Test
	public void testIsRoomAttribute() {
		RoomAttributePK roomAttributePK=new RoomAttributePK();
		roomAttributePK.setRoomAttributeTypeId("kolor");
		roomAttributePK.setCampusId("Warszawa");
		roomAttributePK.setBuildingId("KEN3");
		roomAttributePK.setRoomId("7");

		assertTrue(objectManagement.isRoomAttribute(roomAttributePK));
	}

	@Test
	public void testGetRoomAttribute() {
		RoomAttributePK roomAttributePK=new RoomAttributePK();
		roomAttributePK.setRoomAttributeTypeId("kolor");
		roomAttributePK.setCampusId("Warszawa");
		roomAttributePK.setBuildingId("KEN3");
		roomAttributePK.setRoomId("7");

		assertNotNull(objectManagement.getRoomAttribute(roomAttributePK));
	}

	@Test
	public void testGetRoomAttributeType() {
		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId("internet");

		assertNotNull(objectManagement.getRoomAttributeType(roomAttributeTypePK));
	}

	@Test
	public void testGetBookableRoom() {
		BookableRoomPK bookableRoomPK=new BookableRoomPK();
		bookableRoomPK.setCampusId("Warszawa");
		bookableRoomPK.setBuildingId("KEN7");		
		bookableRoomPK.setRoomId("S1");	

		assertNotNull(objectManagement.getBookableRoom(bookableRoomPK));
	}

	@Test
	public void testGetRoomManager() {
		RoomManagerPK roomManagerPK=new RoomManagerPK();
		roomManagerPK.setCampusId("Warszawa");
		roomManagerPK.setBuildingId("KEN3");
		roomManagerPK.setRoomId("A5");
		roomManagerPK.setUsrGroupId("potwierdzający w kampusie Warszawa");

		assertNotNull(objectManagement.getRoomManager(roomManagerPK));
	}

	@Test
	public void testIsBookableRoom() {
		BookableRoomPK bookableRoomPK=new BookableRoomPK();
		bookableRoomPK.setCampusId("Warszawa");
		bookableRoomPK.setBuildingId("KEN7");		
		bookableRoomPK.setRoomId("S1");	

		assertTrue(objectManagement.isBookableRoom(bookableRoomPK));
	}

	@Test
	public void testIsRoomManager() {
		RoomManagerPK roomManagerPK=new RoomManagerPK();
		roomManagerPK.setCampusId("Warszawa");
		roomManagerPK.setBuildingId("KEN3");
		roomManagerPK.setRoomId("A5");
		roomManagerPK.setUsrGroupId("potwierdzający w kampusie Warszawa");

		assertTrue(objectManagement.isRoomManager(roomManagerPK));
	}

	@Test
	public void testIsUsrGroup() {
		UsrGroupPK usrGroupPK=new UsrGroupPK();
		usrGroupPK.setUsrGroupId("potwierdzający w kampusie Radom");

		assertTrue(objectManagement.isUsrGroup(usrGroupPK));
	}

	@Test
	public void testGetRoomManagersByBookableRoom() {

		BookableRoomPK bookableRoomPK=new BookableRoomPK();
		bookableRoomPK.setCampusId("Warszawa");
		bookableRoomPK.setBuildingId("KEN3");
		bookableRoomPK.setRoomId("57");

		assertEquals(
				Arrays.asList(
						new RoomManagerPK("Warszawa","KEN3","57","potwierdzający drugiego stopnia"),
						new RoomManagerPK("Warszawa","KEN3","57","potwierdzający pierwszego stopnia"),
						new RoomManagerPK("Warszawa","KEN3","57","potwierdzający w kampusie Warszawa")),								
						RoomManagerTestUtil.getPrimaryKeysAsList(
								objectManagement.getRoomManagers(bookableRoomPK)));

	}

	@Test
	public void testGetRoomManagersByUsrGroup() {

		UsrGroupPK usrGroupPK=new UsrGroupPK();
		usrGroupPK.setUsrGroupId("potwierdzający w kampusie Płock");

		assertEquals(
				RoomManagerTestUtil.createSet(
						new RoomManagerPK("Płock","Wesoła16K","12","potwierdzający w kampusie Płock"),
						new RoomManagerPK("Płock","Wesoła16K","27","potwierdzający w kampusie Płock"),
						new RoomManagerPK("Płock","Wesoła16K","Lab","potwierdzający w kampusie Płock")),								
						RoomManagerTestUtil.getPrimaryKeysAsSet(
								objectManagement.getRoomManagers(usrGroupPK)));

	}

	@Test
	public void testGetBuildingsByCampus() {

		assertEquals(
				Arrays.asList(
						new BuildingPK("Warszawa","KEN3"),
						new BuildingPK("Warszawa","KEN5"),
						new BuildingPK("Warszawa","KEN7")),								
						BuildingTestUtil.getPrimaryKeysAsList(
								objectManagement.getBuildingsByCampus(
										objectManagement.getCampus(new CampusPK("Warszawa")))));


	}

	@Test
	public void testGetRoomsByBuilding() {

		assertEquals(
				Arrays.asList(
						new RoomPK("Warszawa","KEN5","14F"),
						new RoomPK("Warszawa","KEN5","26E"),
						new RoomPK("Warszawa","KEN5","37")),								
						RoomTestUtil.getPrimaryKeysAsList(
								objectManagement.getRooms(
										objectManagement.getBuilding(new BuildingPK("Warszawa","KEN5")))));		

	}

	@Test
	public void testGetRoomsByRoomAttributeType() {

		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId("projektor");

		List<Object[]> roomAttributes=objectManagement.getRooms(roomAttributeTypePK);

		Iterator<Object[]> iterator=roomAttributes.iterator();

		List<Room> rooms=new ArrayList<Room>();

		while(iterator.hasNext()){
			Object[] arr=iterator.next();			
			rooms.add((Room) arr[0]);			
		}

		assertTrue(rooms.size()==18);

		assertTrue(RoomTestUtil.getPrimaryKeysAsList(rooms).containsAll(
				Arrays.asList(new RoomPK("Płock","Wesoła16K","12"),
						new RoomPK("Warszawa","KEN3","A1"),
						new RoomPK("Warszawa","KEN3","A4"))));		
	}

	@Test
	public void testGetRoomAttributes() {

		assertEquals(
				RoomAttributeTestUtil.createSet(
						new RoomAttributePK("Warszawa","KEN3","7","kolor"),
						new RoomAttributePK("Warszawa","KEN3","7","nasłonecznienie"),
						new RoomAttributePK("Warszawa","KEN3","7","powierzchnia"),
						new RoomAttributePK("Warszawa","KEN3","7","klimatyzacja"),
						new RoomAttributePK("Warszawa","KEN3","7","internet")),								
						RoomAttributeTestUtil.getPrimaryKeysAsSet(
								objectManagement.getRoomAttributes(
										objectManagement.getRoom(new RoomPK("Warszawa","KEN3","7")))));					

	}

	@Test
	public void testGetAllCampuses() {

		assertEquals(
				Arrays.asList(
						new CampusPK("Płock"), new CampusPK("Radom"),
						new CampusPK("Warszawa")),
						CampusTestUtil.getPrimaryKeysAsList(
								objectManagement.getAllCampuses()));
	}

	@Test
	public void testGetAllBuildings() {

		assertEquals(BuildingTestUtil.getPrimaryKeysAsSet(
				objectManagement.getAllBuildings()),				
				BuildingTestUtil.createSet(
						new BuildingPK("Warszawa","KEN3"), new BuildingPK("Warszawa", "KEN5"),
						new BuildingPK("Warszawa","KEN7"), new BuildingPK("Radom","Słon36"),
						new BuildingPK("Radom","Słon40A"), new BuildingPK("Płock","Wesoła16K"))						
				);
	}

	@Test
	public void testGetAllRooms() {

		List<Room> rooms=objectManagement.getAllRooms();

		assertTrue(rooms.size()==42);				
		assertTrue(RoomTestUtil.getPrimaryKeysAsList(rooms).containsAll(Arrays.asList(new RoomPK("Warszawa","KEN3","7"),new RoomPK("Warszawa","KEN3","2"),
				new RoomPK("Warszawa","KEN3","23"),new RoomPK("Warszawa","KEN3","A1"),
				new RoomPK("Warszawa","KEN3","A2"),new RoomPK("Warszawa","KEN3","A3"),
				new RoomPK("Warszawa","KEN3","A4"),new RoomPK("Warszawa","KEN3","A5"),
				new RoomPK("Warszawa","KEN5","26E"))));

	}

	@Test
	public void testGetAllRoomAttributeTypes() {

		assertEquals(RoomAttributeTypeTestUtil.getPrimaryKeysAsSet(
				objectManagement.getAllRoomAttributeTypes()),				
				RoomAttributeTypeTestUtil.createSet(
						new RoomAttributeTypePK("projektor"), new RoomAttributeTypePK("nasłonecznienie"),
						new RoomAttributeTypePK("miejsca siedzące"), new RoomAttributeTypePK("procesor"),
						new RoomAttributeTypePK("powierzchnia"), new RoomAttributeTypePK("tablica"),
						new RoomAttributeTypePK("zegar"), new RoomAttributeTypePK("klimatyzacja"),
						new RoomAttributeTypePK("internet"), new RoomAttributeTypePK("kolor"))										
				);		
	}

	@Test
	public void testAddCampus() {
		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId("CMPS");

		Campus campus=new Campus();
		campus.setId(campusPK);
		campus.setName("campus name");
		campus.setDescription("description");

		objectManagement.addCampus(campus);

		assertNotNull(objectManagement.getCampus(campusPK));	
	}

	@Test
	public void testAddBuilding() {
		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId("Radom");
		buildingPK.setBuildingId("BLD5");

		Building building=new Building();
		building.setId(buildingPK);
		building.setName("building name");
		building.setDescription("description");

		objectManagement.addBuilding(building);

		assertNotNull(objectManagement.getBuilding(buildingPK));
	}

	@Test
	public void testAddRoom() {
		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId("Radom");
		roomPK.setBuildingId("Słon40A");
		roomPK.setRoomId("78A");

		Room room=new Room();
		room.setId(roomPK);
		room.setDescription("description");

		objectManagement.addRoom(room);

		assertNotNull(objectManagement.getRoom(roomPK));
	}

	@Test
	public void testAssignRoomAttributeToRoom() {
		RoomAttributePK roomAttributePK=new RoomAttributePK();
		roomAttributePK.setCampusId("Warszawa");
		roomAttributePK.setBuildingId("KEN3");
		roomAttributePK.setRoomId("2");
		roomAttributePK.setRoomAttributeTypeId("projektor");

		RoomAttribute roomAttribute=new RoomAttribute();
		roomAttribute.setId(roomAttributePK);
		roomAttribute.setValue("value");

		objectManagement.assignRoomAttributeToRoom(roomAttribute);

		assertNotNull(objectManagement.getRoomAttribute(roomAttributePK));		
	}

	@Test
	public void testAddRoomAttributeType() {
		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId("attr");

		RoomAttributeType roomAttributeType=new RoomAttributeType();
		roomAttributeType.setId(roomAttributeTypePK);		
		roomAttributeType.setDescription("description");

		objectManagement.addRoomAttributeType(roomAttributeType);

		assertNotNull(objectManagement.getRoomAttributeType(roomAttributeTypePK));		
	}

	@Test
	public void testCreateBookableRoom() {
		BookableRoomPK bookableRoomPK=new BookableRoomPK();
		bookableRoomPK.setCampusId("Warszawa");
		bookableRoomPK.setBuildingId("KEN3");
		bookableRoomPK.setRoomId("7");

		BookableRoom bookableRoom=new BookableRoom();
		bookableRoom.setId(bookableRoomPK);
		bookableRoom.setIsRobot(true);

		objectManagement.createBookableRoom(bookableRoom);

		assertNotNull(objectManagement.getBookableRoom(bookableRoomPK));		
	}

	@Test
	public void testCreateRoomManager() {
		RoomManagerPK roomManagerPK=new RoomManagerPK();
		roomManagerPK.setCampusId("Warszawa");
		roomManagerPK.setBuildingId("KEN3");
		roomManagerPK.setRoomId("Lab4IT");
		roomManagerPK.setUsrGroupId("potwierdzający");

		RoomManager roomManager=new RoomManager();
		roomManager.setId(roomManagerPK);

		objectManagement.createRoomManager(roomManager);

		assertNotNull(objectManagement.getRoomManager(roomManagerPK));
	}

	@Test
	public void testModifyCampus() {
		CampusPK campusPK=new CampusPK();
		campusPK.setCampusId("Warszawa");

		Campus campus=objectManagement.getCampus(campusPK);		
		String actual=campus.getName();		
		campus.setName("updated");		

		objectManagement.modifyCampus(campus);

		assertFalse(actual.equals(objectManagement.getCampus(campusPK).getName()));		
	}

	@Test
	public void testModifyBuilding() {
		BuildingPK buildingPK=new BuildingPK();
		buildingPK.setCampusId("Warszawa");
		buildingPK.setBuildingId("KEN3");

		Building building=objectManagement.getBuilding(buildingPK);
		String actual=building.getName();
		building.setName("updated");

		objectManagement.modifyBuilding(building);

		assertFalse(actual.equals(objectManagement.getBuilding(buildingPK).getName()));		
	}

	@Test
	public void testModifyRoom() {
		RoomPK roomPK=new RoomPK();
		roomPK.setCampusId("Warszawa");
		roomPK.setBuildingId("KEN3");
		roomPK.setRoomId("23");

		Room room=objectManagement.getRoom(roomPK);
		String actual=room.getDescription();		
		room.setDescription("updated");

		objectManagement.modifyRoom(room);

		assertFalse(actual.equals(objectManagement.getRoom(roomPK).getDescription()));
	}

	@Test
	public void testUpdateBookableRoom() {
		BookableRoomPK bookableRoomPK=new BookableRoomPK();
		bookableRoomPK.setCampusId("Warszawa");
		bookableRoomPK.setBuildingId("KEN5");
		bookableRoomPK.setRoomId("26E");

		BookableRoom bookableRoom=objectManagement.getBookableRoom(bookableRoomPK);
		boolean actual=bookableRoom.getIsRobot();
		bookableRoom.setIsRobot(true);

		objectManagement.updateBookableRoom(bookableRoom);

		assertFalse(actual==objectManagement.getBookableRoom(bookableRoomPK).getIsRobot());
	}

	@Test
	public void testModifyAssignedRoomAttribute() {
		RoomAttributePK roomAttributePK=new RoomAttributePK();
		roomAttributePK.setCampusId("Warszawa");
		roomAttributePK.setBuildingId("KEN3");
		roomAttributePK.setRoomId("7");
		roomAttributePK.setRoomAttributeTypeId("kolor");

		RoomAttribute roomAttribute=objectManagement.getRoomAttribute(roomAttributePK);
		String value=roomAttribute.getValue();		
		roomAttribute.setValue("zielony");

		objectManagement.modifyAssignedRoomAttribute(roomAttribute);

		assertFalse(value.equals(roomAttribute.getValue()));		
	}

	@Test
	public void testModifyRoomAttributeType() {
		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId("kolor");

		RoomAttributeType roomAttributeType=objectManagement.getRoomAttributeType(roomAttributeTypePK);
		String description=roomAttributeType.getDescription();		
		roomAttributeType.setDescription("test");

		objectManagement.modifyRoomAttributeType(roomAttributeType);

		assertFalse(description.equals(roomAttributeType.getDescription()));		
	}

	@Test
	public void testDeleteCampus() {
		CampusPK campusPK=new CampusPK("test");
		Campus campus=new Campus();
		campus.setId(campusPK);
		campus.setName("name");

		objectManagement.addCampus(campus);
		objectManagement.deleteCampus(campusPK);

		assertNull(objectManagement.getCampus(campusPK));		
	}

	@Test
	public void testDeleteBuilding() {
		BuildingPK buildingPK=new BuildingPK("Warszawa", "test");
		Building building=new Building();
		building.setId(buildingPK);
		building.setName("name");

		objectManagement.addBuilding(building);
		objectManagement.deleteBuilding(buildingPK);

		assertNull(objectManagement.getBuilding(buildingPK));		
	}

	@Test
	public void testDeleteRoom() {
		RoomPK roomPK=new RoomPK("Warszawa", "KEN3","test");
		Room room=new Room();
		room.setId(roomPK);

		objectManagement.addRoom(room);
		objectManagement.deleteRoom(roomPK);

		assertNull(objectManagement.getRoom(roomPK));	
	}

	@Test
	public void testDeleteAssignedRoomAttribute() {
		RoomAttributePK roomAttributePK=new RoomAttributePK();
		roomAttributePK.setCampusId("Warszawa");
		roomAttributePK.setBuildingId("KEN3");
		roomAttributePK.setRoomId("7");
		roomAttributePK.setRoomAttributeTypeId("kolor");

		objectManagement.deleteAssignedRoomAttribute(roomAttributePK);

		assertNull(objectManagement.getRoomAttribute(roomAttributePK));
	}

	@Test
	public void testDeleteRoomAttributeType() {
		RoomAttributeTypePK roomAttributeTypePK=new RoomAttributeTypePK();
		roomAttributeTypePK.setRoomAttributeTypeId("test");

		RoomAttributeType roomAttributeType=new RoomAttributeType();
		roomAttributeType.setId(roomAttributeTypePK);

		objectManagement.addRoomAttributeType(roomAttributeType);		
		objectManagement.deleteRoomAttributeType(roomAttributeTypePK);

		assertNull(objectManagement.getRoomAttributeType(roomAttributeTypePK));		
	}

	@Test
	public void testDeleteBookableRoom() {
		BookableRoomPK bookableRoomPK=new BookableRoomPK();
		bookableRoomPK.setCampusId("Warszawa");
		bookableRoomPK.setBuildingId("KEN3");
		bookableRoomPK.setRoomId("2");		

		BookableRoom bookableRoom=new BookableRoom();
		bookableRoom.setId(bookableRoomPK);
		bookableRoom.setIsRobot(true);

		objectManagement.createBookableRoom(bookableRoom);
		objectManagement.deleteBookableRoom(bookableRoomPK);

		assertNull(objectManagement.getBookableRoom(bookableRoomPK));	
	}

	@Test
	public void testDeleteRoomManager() {
		RoomManagerPK roomManagerPK=new RoomManagerPK();
		roomManagerPK.setCampusId("Warszawa");
		roomManagerPK.setBuildingId("KEN3");
		roomManagerPK.setRoomId("Lab1IT");
		roomManagerPK.setUsrGroupId("potwierdzający w kampusie Warszawa");

		objectManagement.deleteRoomManager(roomManagerPK);

		assertNull(objectManagement.getRoomManager(roomManagerPK));		
	}
}
