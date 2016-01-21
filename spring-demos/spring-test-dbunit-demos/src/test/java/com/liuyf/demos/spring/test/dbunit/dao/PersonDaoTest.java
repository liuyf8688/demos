package com.liuyf.demos.spring.test.dbunit.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.liuyf.demos.spring.test.dbunit.entity.Person;

public class PersonDaoTest extends AbstractDaoTest {

	@Autowired
	private PersonDao personDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	@DatabaseSetup("/test-data/person/person.xml")
	public void testList() {
		List<Person> persons = personDao.list("hil");
		assertEquals(1, persons.size());
		assertEquals("Phillip", persons.get(0).getFirstName());
	}

}
