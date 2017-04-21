package com.liuyf.demos.simple.restful.model;

public class UserModel {
	
	public enum USER_TYPE {
		
		STUDENT(1), TEARCHER(2);
		
		private int type;
		
		private USER_TYPE(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
		
	}
	
	private int id;
	
	private String username;
	
	private int age;
	
	public UserModel() {
		super();
	}
	
	public UserModel(int id, String username, int age) {
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
