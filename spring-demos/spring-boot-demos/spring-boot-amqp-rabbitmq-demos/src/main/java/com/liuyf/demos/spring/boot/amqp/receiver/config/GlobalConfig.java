package com.liuyf.demos.spring.boot.amqp.receiver.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.liuyf.demos.spring.boot.amqp.receiver")
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
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
	    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	    factory.setConnectionFactory(getConnectionFactory());
	    return factory; 
	}
	
	@Bean
	@Scope("prototype")
	public SimpleMessageListenerContainer messageListenerContainer() {
		
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(getConnectionFactory());
//		container.setQueueNames("cointiger-queue");
//		container.setMessageListener(messageListener());
		
		return container;
	}
	
	@Bean
	public MessageListener messageListener() {
		
		return new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				System.out.println(message.getBody());
			}
		};
	}
}
