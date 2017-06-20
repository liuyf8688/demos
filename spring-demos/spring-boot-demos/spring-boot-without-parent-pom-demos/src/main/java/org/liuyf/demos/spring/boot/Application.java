package org.liuyf.demos.spring.boot;

import org.liuyf.demos.spring.boot.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@RestController
@SpringBootApplication
public class Application {

    
    @RequestMapping(value = "/")
    public String root() {
        
        return "Spring boot ...";
    }
    
	public static void main(String[] args) {
		
		System.setProperty("spring.profiles.active", "production");
		
		SpringApplication application = new SpringApplication(Application.class);
		application.addInitializers(new ApplicationContextInitializer<ConfigurableApplicationContext>() {

			@Override
			public void initialize(ConfigurableApplicationContext ctx) {
				System.out.println("##########################################>>>>>: " + ctx.getApplicationName());
			}
		});
		
		ConfigurableApplicationContext ctx = application.run(args);
		System.out.println("++++++++++ project.verion ++++++++: " + ctx.getBean(Version.class).getVersion());
		
		
		Config config = ctx.getBean(Config.class);
		
		System.out.println("endpointsHypermediaEnabled: " + config.isEndpointsHypermediaEnabled());
		
		// servers
		for (String server : config.getServers()) {
			System.out.println("++++++++++ server ++++++++++: " + server);
		}
		// candidate servers
		for (String server : config.getCandidateServers()) {
			System.out.println("++++++++++ candidate server ++++++++++: " + server);
		}
		
	}

}
