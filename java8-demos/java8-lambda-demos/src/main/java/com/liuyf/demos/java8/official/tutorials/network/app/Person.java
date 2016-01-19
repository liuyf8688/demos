package com.liuyf.demos.java8.official.tutorials.network.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

	public enum Sex {
		MALE, FEMALE
	}
	
	private String name;
	private LocalDate birthday;
	private Sex gender;
	private String emailAddress;
	
	public Person() { }
	
	public Person(String name, LocalDate birthday, Sex gender, String emailAddress) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.emailAddress = emailAddress;
	}
	
	public void printPerson() {
		System.out.println("[ "
						+ "name : " + name + ", " 
						+ "birthday : " + birthday.format(DateTimeFormatter.ISO_DATE) + ", "
						+ "gender : " + gender + ", "
						+ "emailAddress : " + emailAddress + ", "
						+ "]");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public Sex getGender() {
		return gender;
	}
	public void setGender(Sex gender) {
		this.gender = gender;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
}
