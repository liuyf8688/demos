package com.liuyf.demos.cache.ehcache2.pojo;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -4388752173573248268L;

	private Long id;
	
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "[ id=" + id + ", name=" + name +", age=" + age + "]";
	}
	
}
