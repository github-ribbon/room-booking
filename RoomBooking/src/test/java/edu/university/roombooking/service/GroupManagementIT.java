package edu.university.roombooking.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

import edu.university.roombooking.domain.Person;
import edu.university.roombooking.domain.PersonPK;
import edu.university.roombooking.domain.UsrPK;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml"})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
public class GroupManagementIT {

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private GroupManagement groupManagement;

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
	public void testAddPerson(){
		Person person=new Person();
		person.setGivenName("name");
		person.setFamilyName("surname");

		groupManagement.addPerson(person);

		assertTrue(person.getPersonId()>0);		
	}

	@Test
	public void testIsUser(){
		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId("ag");

		assertTrue(groupManagement.isUsr(usrPK));
		usrPK.setUsrId("test");
		assertFalse(groupManagement.isUsr(usrPK));		
	}

	@Test(expected=Exception.class)
	public void testDeletePerson(){
		PersonPK personPK=new PersonPK();
		personPK.setPersonId(900);

		groupManagement.deletePerson(personPK);	
	}
}
