package com.liuyf.demos.spring.boot.amqp.sender.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.liuyf.demos.spring.boot.amqp.sender")
public class GlobalConfig {

	@Bean
	public ConnectionFactory getConnectionFactory() {
		
		CachingConnectionFactory factory = new CachingConnectionFactory("192.168.1.200");
		factory.setUsername("liuyf");
		factory.setPassword("123456");
		factory.setChannelCacheSize(50);
		
		return factory;
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
		
		return new RabbitTemplate(getConnectionFactory());
	}
}
