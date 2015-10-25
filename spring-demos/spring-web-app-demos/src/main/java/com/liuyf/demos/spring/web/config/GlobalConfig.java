package com.liuyf.demos.spring.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:/application.properties")
public class GlobalConfig {

	@Bean
	public PropertySourcesPlaceholderConfigurer getPropertySourcesBean() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
