package com.liuyf.demos.simple.restful.pojo;

public class UserPojo {

	private int id;
	
	private String username;
	
	private int age;
	
	public UserPojo() {
		super();
	}
	
	public UserPojo(int id, String username, int age) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
