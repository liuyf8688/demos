package com.liuyf.demos.javaee.jpa.elementcollection.object.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.liuyf.demos.javaee.jpa.elementcollection.object.dao.ComputerDao;
import com.liuyf.demos.javaee.jpa.elementcollection.object.entity.Computer;
import com.liuyf.demos.spring.test.dbunit.dao.AbstractDaoTest;

public class ComputerDaoTest extends AbstractDaoTest {

	@Autowired
	private ComputerDao computerDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	@DatabaseSetup(value = { "/test-data/computer/computer.xml", "/test-data/computer/computer-parts.xml" })
	public void testList() {
		List<Computer> computers = computerDao.list();
		assertEquals(4, computers.size());
	}
	
	@Test
	@DatabaseSetup(value = { 
			"/test-data/computer/computer.xml",
			"/test-data/computer/computer-parts.xml",
			"/test-data/computer/computer-phones.xml"
			})
	public void testGetById() {
		Computer computer = computerDao.getById(1L);
		assertNotNull(computer);
		assertEquals(3, computer.getParts().size());
		assertEquals(2, computer.getPhones().size());
	}

}
