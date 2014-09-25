package edu.university.roombooking.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twmacinta.util.MD5;

import edu.university.roombooking.domain.Person;
import edu.university.roombooking.domain.PersonGroup;
import edu.university.roombooking.domain.PersonGroupPK;
import edu.university.roombooking.domain.PersonGroupPerson;
import edu.university.roombooking.domain.PersonGroupPersonPK;
import edu.university.roombooking.domain.PersonPK;
import edu.university.roombooking.domain.Usr;
import edu.university.roombooking.domain.UsrGroup;
import edu.university.roombooking.domain.UsrGroupAuth;
import edu.university.roombooking.domain.UsrGroupPK;
import edu.university.roombooking.domain.UsrGroupUsr;
import edu.university.roombooking.domain.UsrGroupUsrPK;
import edu.university.roombooking.domain.UsrPK;
import edu.university.roombooking.repository.GenericDAOImpl;
import edu.university.roombooking.repository.PersonGroupPersonDAO;
import edu.university.roombooking.repository.UsrDAO;
import edu.university.roombooking.repository.UsrGroupDAO;
//import edu.university.roombooking.service.abstraction.GroupManagement;



@Service
public class GroupManagement implements UserDetailsService 
//,GroupManagement
{

	@Autowired
	@Qualifier(value="personCRUD")
	private GenericDAOImpl<Person,PersonPK> personDAO;	

	@Autowired
	private UsrDAO usrDAO;	

	@Autowired
	@Qualifier(value="personGroupCRUD")
	private GenericDAOImpl<PersonGroup,PersonGroupPK> personGroupDAO;	

	@Autowired
	private PersonGroupPersonDAO personGroupPersonDAO;

	@Autowired
	private UsrGroupDAO usrGroupDAO;

	@Autowired
	@Qualifier(value="usrGroupUsrCRUD")
	private GenericDAOImpl<UsrGroupUsr,UsrGroupUsrPK> usrGroupUsrDAO;

	/**
	 * Returns encrypted password using hash algorithm.
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static final String getEncryptedPassword(String password) throws Exception{

		MD5 md5 = new MD5();
		md5.Update(password,"UTF-8");		    

		return 	md5.asHex();	
	}

	/**
	 * Returns random and not hashed password.
	 * 
	 * @return
	 */
	public static final String getRandomPassword(){

		return RandomStringUtils.randomAlphanumeric(10);
	}	

	/**
	 * Returns random activation code.
	 * 
	 * @return
	 */
	public static final String getRandomActivationCode(){
		return RandomStringUtils.randomAlphanumeric(15);
	}

	//@Override
	@Transactional(readOnly=true)
	public Person getPerson(PersonPK personPK){
		return personDAO.read(personPK);
	}

	//@Override
	@Transactional(readOnly=true)
	public Usr getUsr(UsrPK usrPK){
		return usrDAO.read(usrPK);
	}	

	//@Override
	@Transactional(readOnly=true)
	public PersonGroup getPersonGroup(PersonGroupPK personGroupPK){
		return personGroupDAO.read(personGroupPK);
	}

	//@Override
	@Transactional(readOnly=true)
	public PersonGroupPerson getAssignedPersonToPersonGroup(PersonGroupPersonPK personGroupPersonPK){
		return personGroupPersonDAO.read(personGroupPersonPK);
	}	

	//@Override
	@Transactional(readOnly=true)
	public UsrGroup getUsrGroup(UsrGroupPK usrGroupPK){
		return usrGroupDAO.read(usrGroupPK);
	}	

	//@Override
	@Transactional(readOnly=true)
	public UsrGroupUsr getAssignedUserToUserGroup(UsrGroupUsrPK usrGroupUsrPK){
		return usrGroupUsrDAO.read(usrGroupUsrPK);
	}

	//@Override
	@Transactional(readOnly=true)
	public boolean isPerson(PersonPK personPK){				
		if(personDAO.read(personPK)==null){
			return false;
		}else return true;	
	}	

	//@Override
	@Transactional(readOnly=true)
	public boolean isUsr(UsrPK usrPK){				
		if(usrDAO.read(usrPK)==null){
			return false;
		}else return true;	
	}		

	//@Override
	@Transactional(readOnly=true)
	public boolean isPersonGroup(PersonGroupPK personGroupPK){				
		if(personGroupDAO.read(personGroupPK)==null){
			return false;
		}else return true;	
	}		

	//@Override
	@Transactional(readOnly=true)
	public boolean isPersonAssignedToPersonGroup(PersonGroupPersonPK personGroupPersonPK){				
		if(personGroupPersonDAO.read(personGroupPersonPK)==null){
			return false;
		}else return true;	
	}	

	//@Override
	@Transactional(readOnly=true)
	public boolean isUsrGroup(UsrGroupPK usrGroupPK){				
		if(usrGroupDAO.read(usrGroupPK)==null){
			return false;
		}else return true;	
	}

	//@Override
	@Transactional(readOnly=true)
	public boolean isUserAssignedToUserGroup(UsrGroupUsrPK usrGroupUsrPK){				
		if(usrGroupUsrDAO.read(usrGroupUsrPK)==null){
			return false;
		}else return true;	
	}

	//@Override
	@Transactional(readOnly=true)
	public List<Person> getAllPersons(){
		return personDAO.getAllRows();
	}

	//@Override
	@Transactional(readOnly=true)
	public List<Usr> getAllUsers(){
		return usrDAO.getAllRows();
	}	

	//@Override
	@Transactional(readOnly=true)
	public List<PersonGroup> getAllPersonGroups(){
		return personGroupDAO.getAllRows();
	}	

	//@Override
	@Transactional(readOnly=true)
	public List<PersonGroupPerson> getAllAssignedPersonsToPersonGroup(){
		return personGroupPersonDAO.getAllRows();
	}

	//@Override
	@Transactional(readOnly=true)
	public List<PersonGroupPerson> getAssignedPersonsToPersonGroup(PersonGroupPK personGroupPK){				
		return personGroupPersonDAO.findAssignedPersonsToPersonGroup(personGroupPK);		
	}

	//@Override
	@Transactional(readOnly=true)
	public List<PersonGroupPerson> getAssignedPersonsToPersonGroup(PersonPK personPK){		
		return getPerson(personPK).getPersonGroupPersons();				
	}

	//@Override
	@Transactional(readOnly=true)
	public List<UsrGroup> getAllUserGroups(){
		return usrGroupDAO.getAllRows();
	}

	//@Override
	@Transactional(readOnly=true)
	public List<UsrGroupUsr> getAssignedUsersToUserGroup(UsrGroupPK usrGroupPK){		
		return getUsrGroup(usrGroupPK).getUsrGroupUsers();						
	}

	//@Override
	@Transactional(readOnly=true)
	public List<UsrGroupUsr> getAssignedUsersToUserGroup(UsrPK usrPK){		
		return getUsr(usrPK).getUsrGroupUsers();						
	}

	//@Override
	@Transactional(readOnly=true)
	public List<UsrGroupUsr> getAllUsersAssignedToUserGroup(){
		return usrGroupUsrDAO.getAllRows();
	}

	//@Override
	@Transactional
	public void addPerson(Person person){
		personDAO.insert(person);
	}

	//@Override
	@Transactional
	public void createUser(Usr usr) throws Exception{		

		usr.setEnabled(true);		

		String password=GroupManagement.getRandomPassword();	
		String plainPassword=password;
		usr.setPassword(GroupManagement.getEncryptedPassword(password));

		usrDAO.insert(usr);	

		JavaMailSenderImpl sender=getEmailSenderTemplate();		

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(usr.getEmail());
		mailMessage.setSubject("Welcome to the RoomBooking application");
		mailMessage.setFrom("RoomBooking");		
		mailMessage.setText("Your account has been created successfully.\n\n\n login: "
				+ usr.getId().getUsrId()+"\n password: "+plainPassword+
				"\n\n\n");

		try{	
			sender.send(mailMessage);
		}catch(MailSendException e){			
			deleteUser(usr.getId());			
		}

	}

	//@Override
	@Transactional
	public void createPersonGroup(PersonGroup personGroup){
		personGroupDAO.insert(personGroup);
	}

	//@Override
	@Transactional
	public void assignPersonToPersonGroup(PersonGroupPerson personGroupPerson){
		personGroupPersonDAO.insert(personGroupPerson);
	}

	//@Override
	@Transactional
	public void createUserGroup(UsrGroup usrGroup){
		usrGroupDAO.insert(usrGroup);
	}	

	//@Override
	@Transactional
	public void assignUserToUserGroup(UsrGroupUsr usrGroupUsr){
		usrGroupUsrDAO.insert(usrGroupUsr);
	}

	//@Override
	@Transactional
	public void deletePerson(PersonPK personPK){	
		personDAO.delete(personDAO.read(personPK));
	}

	//@Override
	@Transactional
	public void deleteUser(UsrPK usrPK){	
		usrDAO.delete(usrDAO.read(usrPK));
	}

	//@Override
	@Transactional
	public void deletePersonGroup(PersonGroupPK personGroupPK){		
		personGroupDAO.delete(personGroupDAO.read(personGroupPK));
	}

	//@Override
	@Transactional
	public void deletePersonFromPersonGroup(PersonGroupPersonPK personGroupPersonPK){		
		personGroupPersonDAO.delete(personGroupPersonDAO.read(personGroupPersonPK));
	}

	//@Override
	@Transactional
	public void deleteUserGroup(UsrGroupPK usrGroupPK){		
		usrGroupDAO.delete(usrGroupDAO.read(usrGroupPK));
	}

	//@Override
	@Transactional
	public void deleteUserFromUserGroup(UsrGroupUsrPK usrGroupUsrPK){			
		usrGroupUsrDAO.delete(usrGroupUsrDAO.read(usrGroupUsrPK));
	}

	//@Override
	@Transactional
	public void updatePerson(Person person){
		personDAO.update(person);
	}

	//@Override
	@Transactional
	public void updateUsr(Usr usr){
		usrDAO.update(usr);
	}

	//@Override
	@Transactional
	public void updatePersonGroup(PersonGroup personGroup){
		personGroupDAO.update(personGroup);
	}

	//@Override
	@Transactional
	public void updateAssignedPersonToPersonGroup(PersonGroupPerson personGroupPerson){
		personGroupPersonDAO.update(personGroupPerson);
	}

	//@Override
	@Transactional
	public void updateUserGroup(UsrGroup usrGroup){
		usrGroupDAO.update(usrGroup);
	}	

	//@Override
	@Transactional
	public void updateAssignedUserToUserGroup(UsrGroupUsr usrGroupUsr){
		usrGroupUsrDAO.update(usrGroupUsr);
	}

	//@Override
	@Transactional(readOnly=true)
	public boolean isPersonUnique(int personId){			
		if(usrDAO.findByPerson(personId)==null){
			return true;
		}else{
			return false;
		}			
	}	

	//@Override
	@Transactional(readOnly=true)
	public boolean isEmailUnique(String email){		
		if(usrDAO.findByEmail(email)==null){
			return true;
		}else{
			return false;
		}		
	}	

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			UsrPK usrPK=new UsrPK();
			usrPK.setUsrId(username);

			Usr domainUser = usrDAO.read(usrPK);

			@SuppressWarnings("unused")
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;

			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

			List<UsrGroupUsr> userGroupUsers=domainUser.getUsrGroupUsers();

			Iterator<UsrGroupUsr> userGroupUserIterator=userGroupUsers.iterator();

			List<UsrGroup> usrGroups=new ArrayList<UsrGroup>();

			while(userGroupUserIterator.hasNext()){									
				usrGroups.add(userGroupUserIterator.next().getUsrGroup());				
			}

			Iterator<UsrGroup> usrGroupIterator=usrGroups.iterator();

			Set<UsrGroupAuth> usrGroupAuthSet=new HashSet<UsrGroupAuth>();

			while(usrGroupIterator.hasNext()){
				List<UsrGroupAuth> usrAuthList=usrGroupIterator.next().getUsrGroupAuths();

				Iterator<UsrGroupAuth> usrAuthListIterator=usrAuthList.iterator();

				while(usrAuthListIterator.hasNext()){
					usrGroupAuthSet.add(usrAuthListIterator.next());
				}				
			}		



			Iterator<UsrGroupAuth> usrGroupAuthSetIterator=usrGroupAuthSet.iterator();

			List<UsrGroupAuth> usrGroupAuths=new ArrayList<UsrGroupAuth>();

			while(usrGroupAuthSetIterator.hasNext()){
				usrGroupAuths.add(usrGroupAuthSetIterator.next());
			}


			Iterator<UsrGroupAuth> iterator=usrGroupAuths.iterator();					


			while(iterator.hasNext()){
				authorities.add(
						new SimpleGrantedAuthority(((UsrGroupAuth) iterator.next()).getId().getAuthorityId().toString()));
			}

			return new User(
					domainUser.getId().getUsrId(), 
					domainUser.getPassword().toLowerCase(),
					domainUser.getIsEnabled(),
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked,
					authorities);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//@Override
	@Transactional(readOnly=true)
	public Usr getUsr(String login){
		UsrPK usrPK=new UsrPK();
		usrPK.setUsrId(login);

		return usrDAO.read(usrPK);		
	}

	//@Override
	@Transactional
	public void updateUser(Usr usr,Person person){
		usrDAO.updateUser(usr,person);
	}

	//@Override
	@Transactional(readOnly=true)
	public List<UsrGroup> getMyUserGroups(UsrPK usrPK){		
		return usrGroupDAO.findByUser(usrPK);	
	}


	private JavaMailSenderImpl getEmailSenderTemplate() throws IOException{		

		Properties properties=new Properties();		
		properties.load(getClass().getClassLoader().getResourceAsStream("mail.properties"));

		JavaMailSenderImpl sender=new JavaMailSenderImpl();

		sender.setHost(properties.getProperty("sender.host"));
		sender.setPort(Integer.parseInt(properties.getProperty("sender.port")));
		sender.setUsername(properties.getProperty("sender.username"));
		sender.setPassword(properties.getProperty("sender.password"));


		Properties props = new Properties();		
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.timeout", "8500");
		sender.setJavaMailProperties(properties);

		return sender;		
	}

	//@Override
	@Transactional
	public void disableUserAccount(UsrPK usrPK) throws IOException{

		Usr usr=getUsr(usrPK);		
		usr.setEnabled(false);

		usrDAO.update(usr);	

		JavaMailSenderImpl sender=getEmailSenderTemplate();

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(usr.getEmail());
		mailMessage.setSubject("Your account has been disabled");				
		mailMessage.setText("Your account has been disabled.\n\nFor more details contact the RoomBooking Support.\n\n\n");

		try{	
			sender.send(mailMessage);
		}catch(MailSendException e){			

		}			
	}

	//@Override
	@Transactional
	public void enableUserAccount(UsrPK usrPK) throws IOException{

		Usr usr=getUsr(usrPK);
		usr.setEnabled(true);

		usrDAO.update(usr);

		JavaMailSenderImpl sender=getEmailSenderTemplate();

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(usr.getEmail());
		mailMessage.setSubject("Your account has been enabled");				
		mailMessage.setText("Your account has been enabled.\n\nFor more details contact the RoomBooking Support.\n\n\n");

		try{	
			sender.send(mailMessage);
		}catch(MailSendException e){			

		}			
	}

	//@Override
	@Transactional(readOnly=true)
	public boolean isUser(String login, String email){

		if(usrDAO.findByLoginAndEmail(login, email)!=null){
			return true;
		}else 
			return false;			
	}

	//@Override
	@Transactional
	public void resetPassword(String login, String email) throws Exception{		

		Usr usr=usrDAO.findByLoginAndEmail(login, email);	

		String password=GroupManagement.getRandomPassword();	
		String plainPassword=password;
		usr.setPassword(GroupManagement.getEncryptedPassword(password));		

		usrDAO.update(usr);

		JavaMailSenderImpl sender=getEmailSenderTemplate();		

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(usr.getEmail());
		mailMessage.setSubject("Your password has been reset");			
		mailMessage.setText("Your password has been reset successfully.\n\n\n login: " + usr.getId().getUsrId()+"\n password: "+plainPassword+
				"\n\n\n");

		try{	
			sender.send(mailMessage);
		}catch(MailSendException e){	

		}	
	}	

}
