package edu.university.roombooking.domain;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name="authority")
@Cacheable(false)
public class Authority implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AuthorityPK id;	

	@OneToMany(mappedBy="authority", fetch = LAZY)	
	@OrderBy
	private List<UsrGroupAuth> usrGroupAuths;

	public Authority(){		
	}

	public AuthorityPK getId() {
		return id;
	}

	public void setId(AuthorityPK id) {
		this.id = id;
	}

	public List<UsrGroupAuth> getUsrGroupAuths() {
		return usrGroupAuths;
	}

	public void setUsrGroupAuths(List<UsrGroupAuth> usrGroupAuths) {
		this.usrGroupAuths = usrGroupAuths;
	}
}
