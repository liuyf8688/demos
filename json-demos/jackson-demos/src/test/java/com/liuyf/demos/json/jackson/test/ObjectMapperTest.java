package com.liuyf.demos.json.jackson.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuyf.demos.json.jackson.model.User;
import com.liuyf.demos.json.jackson.utils.CustomObjectMapper;

public class ObjectMapperTest {

	private ObjectMapper objectMapper = null;
	@Before
	public void setUp() {
		objectMapper = new CustomObjectMapper();
	}
	
	@Test
	public void test() throws JsonProcessingException {
		String expected = "{\"givenName\":\"\",\"middelName\":\"\",\"surname\":\"\",\"age\":\"\"}";
		Assert.assertEquals(expected, objectMapper.writeValueAsString(new User()));
	}
	
	@Test
	public void testExpectedAgeIsEmpty() throws JsonProcessingException {
		String expected = "{\"givenName\":\"Yanfeng\",\"middelName\":\"8688\",\"surname\":\"Liu\",\"age\":\"\"}";
		User user = new User();
		user.setGivenName("Yanfeng");
		user.setMiddelName("8688");
		user.setSurname("Liu");
		Assert.assertEquals(expected, objectMapper.writeValueAsString(user));
	}
}
