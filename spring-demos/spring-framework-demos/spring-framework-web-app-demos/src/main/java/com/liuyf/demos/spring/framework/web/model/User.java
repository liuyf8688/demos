package com.liuyf.demos.spring.framework.web.model;

public class User {
	
	public User() {
		super();
	}
	
	public User(String username, int age) {
		super();
		this.username = username;
		this.age = age;
	}

	private String username;
	
	private int age;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
