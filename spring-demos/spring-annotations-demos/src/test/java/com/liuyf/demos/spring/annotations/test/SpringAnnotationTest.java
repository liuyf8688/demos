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

}
