package org.liuyf.demos.spring.boot.jms.hornetq;

import org.liuyf.demos.spring.boot.jms.hornetq.sender.JmsSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class);
		for (int i = 0; i < 10; i ++) {
			ctx.getBean(JmsSender.class).send("" + i);
		}
	}

}
