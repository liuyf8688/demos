package com.liuyf.demos.spring.akka.integration.example.config;

import akka.actor.ActorSystem;
import com.liuyf.demos.spring.akka.integration.example.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.liuyf.demos.spring.akka.integration")
public class GlobalConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {

        ActorSystem system = ActorSystem.create("spring-akka-integration-demos");
        SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);
        return system;
    }
}
