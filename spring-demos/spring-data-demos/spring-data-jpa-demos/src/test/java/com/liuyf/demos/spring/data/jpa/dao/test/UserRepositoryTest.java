package com.liuyf.demos.spring.data.jpa.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.liuyf.demos.spring.data.jpa.UserRepository;
import com.liuyf.demos.spring.data.jpa.pojo.UserPojo;
import com.liuyf.demos.spring.test.dbunit.dao.AbstractDaoTest;

public class UserRepositoryTest extends AbstractDaoTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	@DatabaseSetup("/test-data/user/user.xml")
	public void testGetById() {
		UserPojo userPojo = userRepository.findOne(1L);
		Assert.assertEquals("Dell", userPojo.getName());
	}
}
