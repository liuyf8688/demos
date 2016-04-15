package com.liuyf.demos.spring.framework.i18n;

import java.util.Locale;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestInternationalizationUsingXml {

	private static MessageSource messageSource = null;
	
	@BeforeClass
	public static void setUp() {
		messageSource = new ClassPathXmlApplicationContext("config.xml");
	}
	
	@Test
	public void testGetMessageWithoutDefaultValue() {
		String message = messageSource.getMessage("message", null, null);
		Assert.assertEquals("Hello Tony!", message);
		
		String argumentRequired = messageSource.getMessage("argument.required", new Object[] { "Username" }, null);
		Assert.assertEquals("The 'Username' argument is required.", argumentRequired);
	}
	
	@Test
	public void testGetMessageWithLocale_zhCN() {
		String message = messageSource.getMessage("message", null, Locale.CHINA);
		Assert.assertEquals("Tony你好！", message);
		
		String argumentRequired = messageSource.getMessage("argument.required", new Object[] { "Username" }, Locale.CHINA);
		Assert.assertEquals("'Username'参数必填。", argumentRequired);
	}
	
	
}
