package edu.university.roombooking.service.abstraction;

import java.util.List;

import edu.university.roombooking.domain.Person;
import edu.university.roombooking.domain.PersonGroup;
import edu.university.roombooking.domain.PersonGroupPK;
import edu.university.roombooking.domain.PersonGroupPerson;
import edu.university.roombooking.domain.PersonGroupPersonPK;
import edu.university.roombooking.domain.PersonPK;
import edu.university.roombooking.domain.Usr;
import edu.university.roombooking.domain.UsrGroup;
import edu.university.roombooking.domain.UsrGroupPK;
import edu.university.roombooking.domain.UsrGroupUsr;
import edu.university.roombooking.domain.UsrGroupUsrPK;
import edu.university.roombooking.domain.UsrPK;



public abstract interface GroupManagementInterface 
//GroupManagement
{

	public abstract Person getPerson(PersonPK personPK);

	public abstract Usr getUsr(UsrPK usrPK);

	public abstract Usr getUsr(String login);

	public abstract void updateUser(Usr usr,Person person);

	public abstract PersonGroup getPersonGroup(PersonGroupPK personGroupPK);

	public abstract UsrGroup getUsrGroup(UsrGroupPK usrGroupPK);

	public abstract List<Person> getAllPersons();

	public abstract List<Usr> getAllUsers();

	public abstract List<PersonGroup> getAllPersonGroups();

	public abstract List<UsrGroup> getAllUserGroups();

	public abstract void addPerson(Person person);

	public abstract void createUser(Usr usr) throws Exception;

	public abstract void createPersonGroup(PersonGroup personGroup);

	public abstract void createUserGroup(UsrGroup usrGroup);

	public abstract void deletePerson(PersonPK personPK);

	public abstract void deleteUser(UsrPK usrPK);

	public abstract void deletePersonGroup(PersonGroupPK personGroupPK);

	public abstract void deleteUserGroup(UsrGroupPK usrGroupPK);

	public abstract void updatePerson(Person person);

	public abstract void updateUsr(Usr usr);

	public abstract void updatePersonGroup(PersonGroup personGroup);

	public abstract void updateUserGroup(UsrGroup usrGroup);

	public abstract boolean isEmailUnique(String email);

	public abstract PersonGroupPerson getAssignedPersonToPersonGroup(
			PersonGroupPersonPK personGroupPersonPK);

	public abstract UsrGroupUsr getAssignedUserToUserGroup(UsrGroupUsrPK usrGroupUsrPK);

	public abstract boolean isPerson(PersonPK personPK);

	public abstract boolean isUsr(UsrPK usrPK);

	public abstract boolean isPersonGroup(PersonGroupPK personGroupPK);

	public abstract boolean isPersonAssignedToPersonGroup(PersonGroupPersonPK personGroupPersonPK);

	public abstract boolean isUsrGroup(UsrGroupPK usrGroupPK);

	public abstract List<PersonGroupPerson> getAllAssignedPersonsToPersonGroup();

	public abstract List<PersonGroupPerson> getAssignedPersonsToPersonGroup(PersonGroupPK personGroupPK);

	public abstract List<PersonGroupPerson> getAssignedPersonsToPersonGroup(PersonPK personPK);

	public abstract List<UsrGroupUsr> getAssignedUsersToUserGroup(UsrGroupPK usrGroupPK);

	public abstract List<UsrGroupUsr> getAllUsersAssignedToUserGroup();

	public abstract void assignPersonToPersonGroup(PersonGroupPerson personGroupPerson);

	public abstract void assignUserToUserGroup(UsrGroupUsr usrGroupUsr);

	public abstract void deletePersonFromPersonGroup(PersonGroupPersonPK personGroupPersonPK);

	public abstract void deleteUserFromUserGroup(UsrGroupUsrPK usrGroupUsrPK);

	public abstract void updateAssignedPersonToPersonGroup(PersonGroupPerson personGroupPerson);

	public abstract void updateAssignedUserToUserGroup(UsrGroupUsr usrGroupUsr);

	public abstract boolean isPersonUnique(int personId);	

	public abstract List<UsrGroupUsr> getAssignedUsersToUserGroup(UsrPK usrPK);

	public abstract boolean isUserAssignedToUserGroup(UsrGroupUsrPK usrGroupUsrPK);

	public abstract void disableUserAccount(UsrPK usrPK);

	public abstract void enableUserAccount(UsrPK usrPK);

	public abstract boolean isUser(String login, String email);	

	public void resetPassword(String login, String email) throws Exception;

	public abstract List<UsrGroup> getMyUserGroups(UsrPK usrPK);	
}
