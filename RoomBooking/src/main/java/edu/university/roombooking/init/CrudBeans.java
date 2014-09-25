package edu.university.roombooking.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.university.roombooking.domain.Person;
import edu.university.roombooking.domain.PersonGroup;
import edu.university.roombooking.domain.PersonGroupPK;
import edu.university.roombooking.domain.PersonGroupPerson;
import edu.university.roombooking.domain.PersonGroupPersonPK;
import edu.university.roombooking.domain.PersonPK;
import edu.university.roombooking.domain.UsrGroupUsr;
import edu.university.roombooking.domain.UsrGroupUsrPK;
import edu.university.roombooking.repository.GenericDAOImpl;

@Configuration
public class CrudBeans
//extends WebMvcConfigurerAdapter
{

	//	CRUDs

	@Bean
	public GenericDAOImpl<Person,PersonPK> personCRUD(){		
		return new GenericDAOImpl<Person,PersonPK>(Person.class);
	}

	@Bean
	public GenericDAOImpl<PersonGroup,PersonGroupPK> personGroupCRUD(){		
		return new GenericDAOImpl<PersonGroup,PersonGroupPK>(PersonGroup.class);
	}

	@Bean
	public GenericDAOImpl<PersonGroupPerson,PersonGroupPersonPK> personGroupPersonCRUD(){		
		return new GenericDAOImpl<PersonGroupPerson,PersonGroupPersonPK>(PersonGroupPerson.class);
	}

	@Bean
	public GenericDAOImpl<UsrGroupUsr,UsrGroupUsrPK> usrGroupUsrCRUD(){		
		return new GenericDAOImpl<UsrGroupUsr,UsrGroupUsrPK>(UsrGroupUsr.class);
	}
}