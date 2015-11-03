package com.liuyf.demos.spring.web.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:/application.properties")
public class GlobalConfig {

	@PostConstruct
	public void init() {
		System.out.println("=========================: " + getPropertySourcesBean());
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesBean() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
