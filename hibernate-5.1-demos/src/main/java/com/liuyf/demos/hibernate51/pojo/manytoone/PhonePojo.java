package com.liuyf.demos.hibernate51.pojo.manytoone;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class PhonePojo implements Serializable {

	private static final long serialVersionUID = -337195638279333342L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String number;
	@ManyToOne
	@JoinColumn(name = "person_id",
			foreignKey = @ForeignKey(name = "PERSON_ID_FK"))
	private PersonPojo person;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public PersonPojo getPerson() {
		return person;
	}
	public void setPerson(PersonPojo person) {
		this.person = person;
	}
	
}
