package com.liuyf.demos.spring.annotations.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.liuyf.demos.spring.annotations.Config;
import com.liuyf.demos.spring.annotations.model.JdbcUserHolder;

public class SpringAnnotationTest {

	private AnnotationConfigApplicationContext context = null;
	@Before
	public void setUp() {
		context = new AnnotationConfigApplicationContext();
		context.register(Config.class);
		context.refresh();
		context.registerShutdownHook();
	}
	
	@After
	public void tearDown() {
		context.close();
	}
	
	@Test
	public void loadDataFromPropertyFlieWithAnnotation() {
		JdbcUserHolder holder = context.getBean(JdbcUserHolder.class);
		assertEquals("liuyf", holder.getUserName());
	}
	
	
	@Test
	public void loadDataFromPropertyFlieWithAnnotationExpectedSpecifyingValueIsMySQL1() {
		JdbcUserHolder holder = context.getBean(JdbcUserHolder.class);
		assertEquals("MySQL1", holder.getCurrentSQLType());
	}
	
	@Test
	public void loadDataFromPropertyFlieWithAnnotationExpectedInitArrays() {
		JdbcUserHolder holder = context.getBean(JdbcUserHolder.class);
		assertEquals("MySQL1", holder.getSupportSQLTypes().get(0));
		assertEquals("SQLServer", holder.getSupportSQLTypes().get(1));
	}

	@Test
	public void loadDataFromPropertyFlieWithAnnotationExpectedInitArraysUsingDefaultValue() {
		JdbcUserHolder holder = context.getBean(JdbcUserHolder.class);
		assertEquals("PostgrSQL", holder.getNoSupportSQLTypes().get(0));
		assertEquals("HyperSQL", holder.getNoSupportSQLTypes().get(1));
	}
	
	@Test
	public void loadDataFromPropertyFlieWithAnnotationExpectedTimeoutIs1800() {
		JdbcUserHolder holder = context.getBean(JdbcUserHolder.class);
		assertEquals(1800, holder.getTimeout());
	}

}
