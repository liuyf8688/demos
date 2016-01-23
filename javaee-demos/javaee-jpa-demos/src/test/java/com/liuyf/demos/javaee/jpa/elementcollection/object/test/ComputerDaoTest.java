package com.liuyf.demos.javaee.jpa.elementcollection.object.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
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
	@DatabaseSetup(value = "/test-data/computer/computer.xml")
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
	
	@Test
	@DatabaseSetup(value = {
			"/test-data/computer/computer.xml",
			"/test-data/computer/computer-parts.xml",
			"/test-data/computer/computer-phones.xml"
			})
	@ExpectedDatabase(
			value = "/test-data/computer/expected/computer-ignored-created-column.xml",
			assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testSaveIgnoredCreatedColumn() {
		Computer computer = new Computer();
		computer.setBrand("SAMSUNG");
		computer.setModel("s0109");
		//
		computer.setPhones(Arrays.asList("13800138005"));
		//
		computer.setCreated(new Date());
		
		computerDao.save(computer);
	}
	
	@Test
	@DatabaseSetup(value = {
			"/test-data/computer/computer.xml",
			"/test-data/computer/computer-parts.xml",
			"/test-data/computer/computer-phones.xml"
			})
	@ExpectedDatabase(value = "/test-data/computer/expected/computer-without-model-expected.xml",
			assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testSaveWithoutModelExpected() {
		Computer computer = new Computer();
		computer.setBrand("SAMSUNG");
		computer.setPhones(Arrays.asList("13800138005"));
		computer.setCreated(new Date());
		
		computerDao.save(computer);
	}

}
