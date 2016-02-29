package com.liuyf.demos.spring.data.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.liuyf.demos.spring.data")
@EnableTransactionManagement
public class Config {

}
