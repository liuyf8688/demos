package org.liuyf.demos.hibernate.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Person {

	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
			)
	private Long id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "person_receive_addresses",
				joinColumns = @JoinColumn(name = "person_id"),
				inverseJoinColumns = @JoinColumn(name = "address_id"))
	private Set<Address> receiveAddresses;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Address> getReceiveAddresses() {
		return receiveAddresses;
	}
	public void setReceiveAddresses(Set<Address> receiveAddresses) {
		this.receiveAddresses = receiveAddresses;
	}
	
}
