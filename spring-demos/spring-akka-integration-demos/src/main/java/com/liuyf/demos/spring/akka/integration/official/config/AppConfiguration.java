package com.liuyf.demos.spring.akka.integration.official.config;

import akka.actor.ActorSystem;
import com.liuyf.demos.spring.akka.integration.official.extesion.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tony on 2017/7/4.
 */
@Configuration
public class AppConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {

        ActorSystem system = ActorSystem.create("akka-java-spring");
        SpringExtension.springExtProvider.get(system).initialize(applicationContext);

        return system;
    }
}
