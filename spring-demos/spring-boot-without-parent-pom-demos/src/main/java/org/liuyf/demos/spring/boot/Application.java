package org.liuyf.demos.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		SpringApplication application = new SpringApplication(Application.class);
		application.addInitializers(new ApplicationContextInitializer<ConfigurableApplicationContext>() {

			@Override
			public void initialize(ConfigurableApplicationContext ctx) {
				System.out.println("##########################################>>>>>: " + ctx.getApplicationName());
			}
		});
		application.run(args);
	}

}
