package com.liuyf.demos.spring.annotations.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
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
	
}