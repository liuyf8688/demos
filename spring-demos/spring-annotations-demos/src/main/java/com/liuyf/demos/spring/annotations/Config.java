package com.liuyf.demos.spring.annotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.liuyf.demos.spring.annotations.model.JdbcUserHolder;

@Configuration
@ComponentScan("com.liuyf.demos.spring.annotations.*")
@PropertySource("classpath:/config.properties")
public class Config {

	@Bean
	public PropertySourcesPlaceholderConfigurer test() {
		return  new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean(initMethod = "init", destroyMethod = "destory")
	public JdbcUserHolder getJdbcUserHolder() {
		return new JdbcUserHolder();
	}
}
