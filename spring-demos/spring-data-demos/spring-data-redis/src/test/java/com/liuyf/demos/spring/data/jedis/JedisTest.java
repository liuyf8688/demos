package com.liuyf.demos.spring.data.jedis;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class JedisTest {

	private static RedisTemplate<String, String> template;
	private static ConfigurableApplicationContext context;
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void init() {
		context = new ClassPathXmlApplicationContext("config-jedis.xml");
		template = context.getBean(RedisTemplate.class);
	}
	
	@Test
	public void test() {
		template.opsForList().leftPush("TonyLiu", "TonyLiu");
		Assert.assertTrue(template.hasKey("TonyLiu"));
	}
	
	@AfterClass
	public static void tearDown() {
		if (context != null) {
			context.close();
		}
	}
	
}
