package com.liuyf.demos.spring.framework.annotations.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class JdbcUserHolder {

	@Value(value = "${jdbc.username}")
	private String userName;
	
	@Value(value = "${jdbc.password}")
	private String password;
	
	@Value("#{ '${timeout:1800 }' }")
	private int timeout;
	
	@Value(value = "#{ '${prefix.currentSQLType:MySQL }' }")
	private String currentSQLType;
	
	@Value(value = "#{ T(java.util.Arrays).asList('${supportSQLTypes:MySQL,SQLServer}') }")
	private List<String> supportSQLTypes;
	
	@Value(value = "#{ T(java.util.Arrays).asList('${noSupportSQLTypes:PostgrSQL,HyperSQL}') }")
	private List<String> noSupportSQLTypes;

	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getCurrentSQLType() {
		return currentSQLType;
	}

	public List<String> getSupportSQLTypes() {
		return supportSQLTypes;
	}
	
	public List<String> getNoSupportSQLTypes() {
		return noSupportSQLTypes;
	}
	
	public int getTimeout() {
		return timeout;
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