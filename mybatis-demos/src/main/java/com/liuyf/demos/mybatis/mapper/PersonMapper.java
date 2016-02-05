package com.liuyf.demos.mybatis.mapper;

import java.util.List;

import com.liuyf.demos.mybatis.pojos.Person;

public interface PersonMapper {

	Person findById(Long id);
	
	List<Person> query(Long id);
	
	Long save(Person person);
	
}
