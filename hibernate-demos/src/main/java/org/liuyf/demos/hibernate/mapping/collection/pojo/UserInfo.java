package org.liuyf.demos.hibernate.mapping.collection.pojo;

import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class UserInfo {

	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@ElementCollection
	@CollectionTable(name = "nicknames", joinColumns = @JoinColumn(name = "user_id"))
	private Collection<String> nicknames;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Collection<String> getNicknames() {
		return nicknames;
	}

	public void setNicknames(Collection<String> nicknames) {
		this.nicknames = nicknames;
	}
	
	
}
