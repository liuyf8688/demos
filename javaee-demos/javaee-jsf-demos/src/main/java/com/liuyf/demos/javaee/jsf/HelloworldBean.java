package com.liuyf.demos.javaee.jsf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HelloworldBean implements Serializable {

	private static final long serialVersionUID = -8921154968945681898L;

	private String name = "";
	
	@ManagedProperty(value = "#{demoService}")
	private Service service;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	public String getReverseName() {
		return service.reverse(name);
	}
}
