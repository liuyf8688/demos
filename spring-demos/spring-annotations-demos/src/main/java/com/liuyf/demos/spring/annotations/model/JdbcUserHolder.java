package com.liuyf.demos.spring.annotations.model;

import org.springframework.beans.factory.annotation.Value;

public class JdbcUserHolder {

	@Value(value = "${jdbc.username}")
	private String userName;
	
	@Value(value = "${jdbc.password}")
	private String password;

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public void init() {
		System.out.println("setup username : " + getUserName() + "  =====>>>  " + getPassword());
	}
	
	public void destory() {
		userName = null;
		password = null;
		
		System.out.println("remove the userName and password.");
	}
	
}