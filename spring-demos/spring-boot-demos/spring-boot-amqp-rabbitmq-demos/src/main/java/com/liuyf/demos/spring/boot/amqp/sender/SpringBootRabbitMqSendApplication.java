package com.liuyf.demos.spring.boot.amqp.sender;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootRabbitMqSendApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootRabbitMqSendApplication.class, args);
		
		RabbitTemplate template = applicationContext.getBean(RabbitTemplate.class);
		template.setExchange("cointiger-exchange");
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				
				while (true) {
					
					int len = RandomUtils.nextInt(0, 100);
					for (int i = 0; i < len; i ++) {
						
						try {
						
							template.convertAndSend(LocalDateTime.now());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(100L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		for (int i = 0; i < 1; i ++) {
			
			new Thread(runnable).start();
		}
		
//		applicationContext.close();
	}
}
